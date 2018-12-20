package com.dev.training.uownme.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @NotNull
    @Size(min = 1)
    private String userName;

    @NotNull
    @Size(min = 1)
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String surname;

    @NotNull
    private String mail;


}
