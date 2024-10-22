package com.quiz.Service.User;

import com.quiz.Model.DTO.UserDTO;
import com.quiz.Model.DTO.UserLoginDTO;
import com.quiz.Model.Entity.UserEntity;
import com.quiz.Model.Response.UserResponse;
import org.springframework.data.domain.Page;

public interface IUserService {
    UserResponse save(UserDTO userDTO);
    void changePassword(UserLoginDTO userLoginDTO);
    Page<UserResponse> getAllUsers(Integer page, Integer limit);
    UserResponse getUserById(String id);
    UserEntity getUserEntityById(String id);
}
