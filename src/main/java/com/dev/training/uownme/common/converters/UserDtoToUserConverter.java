package com.dev.training.uownme.common.converters;

import com.dev.training.uownme.common.domain.User;
import com.dev.training.uownme.common.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class
UserDtoToUserConverter implements Converter<UserDto, User> {

    private final PasswordEncoder passwordEncoder;

    public UserDtoToUserConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User convert(UserDto accountDto) {
        User user = new User();
        if (accountDto != null) {
            user.setUserName(accountDto.getUserName());
            user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
            user.setFirstName(accountDto.getFirstName());
            user.setSurname(accountDto.getSurname());
            user.setMail(accountDto.getMail());
        }
        return user;
    }
}
