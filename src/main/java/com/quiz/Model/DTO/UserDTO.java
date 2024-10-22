package com.quiz.Model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String dateOfBirth;
    private String address;
    @NotNull(message = "email is invalid")
    @NotBlank(message = "email is invalid")
    @Email(message = "email is invalid")
    private String email;
    @NotNull(message = "password is invalid")
    @NotBlank(message = "password is invalid")
    @Size(min = 8, message = "password is invalid")
    private String password;
    @Size(min = 8, message = "repeat password is invalid")
    private String rePassword;
    private List<String> roles;
}
