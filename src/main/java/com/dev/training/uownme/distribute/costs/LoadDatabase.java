package com.dev.training.uownme.distribute.costs;

import com.dev.training.uownme.common.domain.User;
import com.dev.training.uownme.common.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Slf4j
public class LoadDatabase {
    private final PasswordEncoder passwordEncoder;

    public LoadDatabase(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            log.info("Preloading " + userRepository.save(
                    new User("szymekp", passwordEncoder.encode("prezes"),
                            "Szymon", "Sadkowski", "szymon@gmail.com")));
            log.info("Preloading " + userRepository.save(
                    new User("krzysieksr", passwordEncoder.encode("1234"),
                            "Krzysztof", "Sroczyk", "krzysiek@gmail.com")));
        };
    }
}
