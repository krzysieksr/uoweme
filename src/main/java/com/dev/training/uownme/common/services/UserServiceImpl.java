package com.dev.training.uownme.common.services;

import com.dev.training.uownme.common.converters.UserDtoToUserConverter;
import com.dev.training.uownme.common.domain.User;
import com.dev.training.uownme.common.dto.UserDto;
import com.dev.training.uownme.common.repositories.RoleRepository;
import com.dev.training.uownme.common.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDtoToUserConverter userDtoToUserConverter;


    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           UserDtoToUserConverter userDtoToUserConverter) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDtoToUserConverter = userDtoToUserConverter;
    }

    @Override
    @Transactional
    public User registerNewUserAccount(UserDto accountDto) {
        final User user = userDtoToUserConverter.convert(accountDto);
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        //TODO
//        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUserName(username);
    }

}
