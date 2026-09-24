package adventure.estera.adventurexp;

import adventure.estera.adventurexp.enums.Role;

public record LoginResponse(String username, Role role) {
}
