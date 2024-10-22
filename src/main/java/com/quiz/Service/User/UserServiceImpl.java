package com.quiz.Service.User;

import com.quiz.Convertor.UserConvertor;
import com.quiz.ExceptionHandlerCustomer.DataInvalidException;
import com.quiz.Model.DTO.UserDTO;
import com.quiz.Model.DTO.UserLoginDTO;
import com.quiz.Model.Entity.UserEntity;
import com.quiz.Model.Response.UserResponse;
import com.quiz.Repository.UserRepository;
import com.quiz.Utils.Pagination;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserConvertor userConvertor;
    private final PasswordEncoder passwordEncoder;
    private final Pagination pagination;
    @Override
    public UserResponse save(UserDTO userDTO) {
        if(userDTO.getId() != null) {
            // update check id
            userRepository.findById(userDTO.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found!"));
        }
        else {
            // save check email exists
            Boolean isExists = userRepository.existsByEmail(userDTO.getEmail());
            if (isExists) {
                throw new DataInvalidException("Email is exists!");
            }
        }
        if(!userDTO.getPassword().equals(userDTO.getRePassword())) {
            throw new DataInvalidException("Password or repeat password is invalid!");
        }
        UserEntity user = userConvertor.dtoToEntity(userDTO);
        UserEntity userEntity = userRepository.save(user);
        return userConvertor.entityToResponse(userEntity);
    }

    @Override
    @Transactional
    public void changePassword(UserLoginDTO userLoginDTO) {
        UserEntity user = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new DataInvalidException("Email is not exists!"));
        // Check lại password có giống password cũ không
        if(passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new DataInvalidException("This is old password!");
        }
        // xoá các token cũ của user
        // Lưu lại user
        user.setPassword(passwordEncoder.encode(userLoginDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Page<UserResponse> getAllUsers(Integer page, Integer limit) {
        Pageable pageable = pagination.pageUtil(page, limit, 1, 4);
        Page<UserEntity> list = userRepository.findAll(pageable);
        List<UserResponse> listResponse = list.getContent().stream()
            .map(userConvertor::entityToResponse)
            .toList();
        Page<UserResponse> userResponses = new PageImpl<>(listResponse, pageable, list.getTotalElements());
        return userResponses;
    }

    @Override
    public UserResponse getUserById(String id) {
        return userConvertor.entityToResponse(getUserEntityById(id));
    }

    @Override
    public UserEntity getUserEntityById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new DataInvalidException("Email is not exists!"));
    }
}
