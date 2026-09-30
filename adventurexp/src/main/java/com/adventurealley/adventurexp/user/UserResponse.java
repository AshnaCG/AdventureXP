package com.adventurealley.adventurexp.user;

import com.adventurealley.adventurexp.login.Role;

public record UserResponse(Long id, Role role, String username) {
}
