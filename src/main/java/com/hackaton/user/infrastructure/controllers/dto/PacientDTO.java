package com.hackaton.user.infrastructure.controllers.dto;

import java.time.LocalDate;

public record PacientDTO (String name, LocalDate dateOfBirth, String cpf,
                          String phone, String email, String password, String endereco){}
