package com.adventurealley.adventurexp.service;

import com.adventurealley.adventurexp.login.Role;
import com.adventurealley.adventurexp.login.LoginResponse;
import com.adventurealley.adventurexp.user.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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

    @Test
    void getAllUserHappyFlow() {
        // Preconditions
        User user = User.create("admin", "12345", Role.ADMIN);
        User user2 = User.create("employee", "12345", Role.EMPLOYEE);
        when(userRepository.findAll()).thenReturn(List.of(user, user2));

        // Execution
        List<UserResponse> users = userService.getAllUsers();

        // Postconditions
        assertEquals(2, users.size());
        assertEquals("admin", users.getFirst().username());
        assertEquals(Role.ADMIN, users.getFirst().role());
        assertEquals("employee", users.getLast().username());
        assertEquals(Role.EMPLOYEE, users.getLast().role());
    }

    @Test
    void createUserHappyFlow() {
        // Preconditions
        UserRequest request = new UserRequest("adam01", "marcusPro", Role.ADMIN);
        User savedUser = User.create(request.username(), request.password(), request.role());
        savedUser.setId(1L);
        when(userRepository.existsByUsername(request.username())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Execution
        UserResponse response = userService.create(request);

        // Postconditions
        assertEquals(1L, response.id());
        assertEquals("adam01", response.username());
        assertEquals(Role.ADMIN, response.role());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUserExceptionFlow() {
        // Test for username already taken

        // Preconditions
        UserRequest request = new UserRequest("adam01", "marcusPro", Role.ADMIN);
        when(userRepository.existsByUsername(request.username())).thenReturn(true);

        // Execution
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> userService.create(request));

        //Postconditions
        assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());

    }

    @Test
    void updateUserHappyFlow(){
        // Preconditions
        User user = User.create("admin", "1234", Role.ADMIN);
        user.setId(1L);
        UserRequest request = new UserRequest("admin", "4321", Role.EMPLOYEE);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Execution
        UserResponse response = userService.update(1L, request);

        // Postconditions
        assertEquals(1L, response.id());
        assertEquals("admin", response.username());
        assertEquals(Role.EMPLOYEE, response.role());
        assertEquals("4321", user.getPassword());
    }

    @Test
    void updateUserOldPassword(){
        // Test for keeping old password

        // Preconditions
        User user = User.create("admin", "1234", Role.ADMIN);
        user.setId(1L);
        UserRequest request = new UserRequest("admin", null, Role.EMPLOYEE);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Execution
        userService.update(1L, request);

        assertEquals("1234", user.getPassword());
    }

    @Test
    void updateUserExceptionFlow(){
        // Test for unknown id

        // Preconditions
        UserRequest request = new UserRequest("admin", "1234", Role.ADMIN);
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Execution
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> userService.update(99L, request));

        // Postconditions
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void updateUserUsernameTakenExceptionFlow(){
        // Test for username taken

        // Preconditions
        User user = User.create("employee", "1234", Role.EMPLOYEE);
        user.setId(1L);
        UserRequest request = new UserRequest("admin", "", Role.EMPLOYEE);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.existsByUsername("admin")).thenReturn(true);

        // Execution
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> userService.update(1L, request));

        // Postconditions
        assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
    }
}