package com.fiap.api.dtos;

import com.fiap.api.enums.Role;

public record DataCreatUser(String login, String password, Role role) {
}
