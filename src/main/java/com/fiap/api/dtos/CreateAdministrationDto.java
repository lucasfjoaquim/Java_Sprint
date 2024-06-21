package com.fiap.api.dtos;

import com.fiap.api.enums.AdministrationFunctions;

public record CreateAdministrationDto(String name, Double salary, String phone, AdministrationFunctions functional) {
}
