package com.adventurealley.adventurexp.login;

import com.adventurealley.adventurexp.user.User;
import com.adventurealley.adventurexp.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {
    private final UserRepository userRepository;
    public InitData(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return;
        }
        User employee = User.create("Employee", "1234", Role.EMPLOYEE);
        User admin = User.create("Admin", "1234", Role.ADMIN);

        userRepository.save(employee);
        userRepository.save(admin);
    }
}
