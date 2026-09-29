package adventure.estera.adventurexp.model;

import adventure.estera.adventurexp.enums.Role;

public record LoginResponse(String username, Role role) {
}
