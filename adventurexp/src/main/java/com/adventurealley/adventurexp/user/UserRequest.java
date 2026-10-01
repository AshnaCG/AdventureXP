package com.adventurealley.adventurexp.user;

import com.adventurealley.adventurexp.login.Role;

public record UserRequest(String username, String password, Role role) {
}
