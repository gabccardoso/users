package com.hackaton.user.infrastructure.controllers.dto;

import java.time.LocalDate;

public record DoctorDTO(String name, LocalDate dateOfBirth, String crm,
                        String phone, String email, String password, String endereco){}


