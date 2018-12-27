package com.dev.training.uownme.common;

import com.dev.training.uownme.common.domain.User;
import com.dev.training.uownme.common.dto.UserDto;
import com.dev.training.uownme.common.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto user = (UserDto) o;

        if (userRepository.findByUserName(user.getUserName()) != null) {
            errors.rejectValue("userName", "Duplicate.userForm.userName");
        }

        if (userRepository.findByMail(user.getMail()) != null) {
            errors.rejectValue("mail", "Duplicate.userForm.mail");
        }

        //TODO
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

    }
}
