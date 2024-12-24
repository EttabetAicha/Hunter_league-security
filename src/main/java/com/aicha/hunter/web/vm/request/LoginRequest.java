package com.aicha.hunter.web.vm.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotNull(message = "you must enter the email of the user")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotNull(message = "you must enter the password of the user")
    private String password;


}
