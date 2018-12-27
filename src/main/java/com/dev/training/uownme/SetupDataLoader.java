package com.dev.training.uownme;

import com.dev.training.uownme.common.domain.Role;
import com.dev.training.uownme.common.domain.User;
import com.dev.training.uownme.common.repositories.RoleRepository;
import com.dev.training.uownme.common.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private boolean alreadySetup = false;

    public SetupDataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) {
            return;
        }
        Role role_user = createRoleIfNotFound("ROLE_USER");
        Role role_admin = createRoleIfNotFound("ROLE_ADMIN");

        createUserIfNotFound("szymekp", "prezes", "Szymon", "Sadkowski",
                "szymon@gmail.com", new ArrayList<Role>(Collections.singletonList(role_user)));
        createUserIfNotFound("krzysieksr", "1234", "Krzysztof", "Sroczyk",
                "krzysiek@gmail.com", new ArrayList<Role>(Collections.singletonList(role_user)));

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        //TODO
//        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    User createUserIfNotFound(final String userName, final String password, final String firstName,
                              final String surname, final String mail, final Collection<Role> roles) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            user = new User();
            user.setUserName(userName);
            user.setPassword(passwordEncoder.encode(password));
            user.setFirstName(firstName);
            user.setSurname(surname);
            user.setMail(mail);
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }
}
