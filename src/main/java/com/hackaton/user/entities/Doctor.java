package com.hackaton.user.entities;

import java.time.LocalDate;

public record Doctor(String name, LocalDate dateOfBirth, String crm,
                     String phone, String email, String password, String endereco){}


