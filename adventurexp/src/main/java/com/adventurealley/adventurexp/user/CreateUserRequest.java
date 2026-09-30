package com.adventurealley.adventurexp.user;

import com.adventurealley.adventurexp.login.Role;

public record CreateUserRequest(String username, String password, Role role) {
}
