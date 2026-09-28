package com.adventurealley.adventurexp.service;

import com.adventurealley.adventurexp.login.Role;
import com.adventurealley.adventurexp.login.LoginResponse;
import com.adventurealley.adventurexp.user.User;
import com.adventurealley.adventurexp.user.UserRepository;
import com.adventurealley.adventurexp.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void loginHappyFlow() {
        // precondition
        User user = User.create("admin", "12345", Role.ADMIN);
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));

        // Execution
        LoginResponse response = userService.login("admin", "12345");

        // Post Condition
        assertEquals("admin", response.username());
        assertEquals(Role.ADMIN, response.role());
    }

    @Test
    void loginWrongPasswordExceptionFlow() {
        User user = User.create("admin", "12345", Role.ADMIN);
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));

        // Execution
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> userService.login("admin", "54321"));

        // Post Conditions
        assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
    }
}