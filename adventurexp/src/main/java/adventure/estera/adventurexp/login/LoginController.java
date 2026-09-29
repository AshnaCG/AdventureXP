package adventure.estera.adventurexp.controller;

import adventure.estera.adventurexp.model.LoginRequest;
import adventure.estera.adventurexp.model.LoginResponse;
import adventure.estera.adventurexp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adventure")
public class LoginController {
    private final UserService userService;
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = userService.login(loginRequest.username(), loginRequest.password());
        return ResponseEntity.ok(loginResponse);
    }
}
