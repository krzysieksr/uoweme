package com.dev.training.uownme.common.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @NotNull
    @Size(min = 6, max = 32)
    private String userName;

    @NotNull
    @Size(min = 8)
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String surname;

    @NotNull
    @Size(min = 1, message = "{NotNull.userDto.mail}")
    @Email(message = "{Email.userDto.mail}")
    private String mail;


}
