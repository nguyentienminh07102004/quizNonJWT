package com.quiz.API;

import com.quiz.ExceptionHandlerCustomer.DataInvalidException;
import com.quiz.Model.DTO.UserDTO;
import com.quiz.Model.DTO.UserLoginDTO;
import com.quiz.Model.Response.APIResponse;
import com.quiz.Model.Response.UserResponse;
import com.quiz.Service.User.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${api.prefix}/users")
public class UserAPI {
    private final IUserService userService;

    @PostMapping(value = "/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public APIResponse register(@Valid @RequestBody UserDTO user, BindingResult bindingResult) {
        if(bindingResult != null && bindingResult.hasErrors()) {
            throw new DataInvalidException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        UserResponse userResponse = userService.save(user);
        return APIResponse.builder()
                .message("SUCCESS")
                .response(userResponse)
                .build();
    }

    @PutMapping(value = "/change-password")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse changePassword(@Valid @RequestBody UserLoginDTO userLoginDTO, BindingResult bindingResult) {
        if(bindingResult != null && bindingResult.hasErrors()) {
            throw new DataInvalidException(bindingResult.getFieldError().getDefaultMessage());
        }
        userService.changePassword(userLoginDTO);
        return APIResponse.builder()
                .message("Change password success!")
                .build();
    }

    @PutMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse updateUser(@RequestBody UserDTO user) {
        UserResponse userResponse = userService.save(user);
        return APIResponse.builder()
                .response(userResponse)
                .message("UPDATE SUCCESS")
                .build();
    }
}
