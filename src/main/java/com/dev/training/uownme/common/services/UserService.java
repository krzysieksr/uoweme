package com.dev.training.uownme.common.services;

import com.dev.training.uownme.common.domain.User;
import com.dev.training.uownme.common.dto.UserDto;

public interface UserService {

    User registerNewUserAccount(final UserDto accountDto);

    User findUserByUserName(String username);
}
