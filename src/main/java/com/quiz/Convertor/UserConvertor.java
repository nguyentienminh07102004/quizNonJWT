package com.quiz.Convertor;

import ch.qos.logback.classic.pattern.DateConverter;
import com.quiz.ExceptionHandlerCustomer.DataInvalidException;
import com.quiz.Model.DTO.UserDTO;
import com.quiz.Model.Entity.RoleEntity;
import com.quiz.Model.Entity.UserEntity;
import com.quiz.Model.Response.UserResponse;
import com.quiz.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserConvertor {
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity dtoToEntity(UserDTO userDTO) {
        Converter<String, Date> converter = mappingContext -> {
            String date = mappingContext.getSource();
            if(date != null && !date.isEmpty()) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date dateOfBirth = null;
                try {
                    dateOfBirth = new Date(format.parse(date).getTime());
                    if(dateOfBirth.after(new Date(System.currentTimeMillis()))) {
                        throw new DataInvalidException("Ngày tháng năm sinh không hợp lệ!!");
                    }
                    return dateOfBirth;
                } catch (ParseException e) {
                    throw new DataInvalidException("Ngày tháng năm sinh không hợp lệ!!");
                }
            }
            return null;
        };
        modelMapper.addConverter(converter, String.class, Date.class);
        UserEntity user = modelMapper.map(userDTO, UserEntity.class);
        if(userDTO.getRoles() ==  null || userDTO.getRoles().isEmpty()) {
            RoleEntity role = roleRepository.findByCode("USER");
            user.setRoles(List.of(role));
        } else {
            List<RoleEntity> roles = userDTO.getRoles().stream()
                    .map(roleRepository::findByCode)
                    .toList();
            user.setRoles(roles);
        }
        // set mặc định mới đăng ký là status là ACTIVE
        user.setStatus("ACTIVE");
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }

    public UserResponse entityToResponse(UserEntity userEntity) {
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        List<String> roles = userEntity.getRoles().stream()
                .map(RoleEntity::getCode)
                .toList();
        String fullName = String.join(" ", userEntity.getFirstname(), userEntity.getLastname());
        userResponse.setFullName(fullName);
        userResponse.setRoles(roles);
        return userResponse;
    }
}
