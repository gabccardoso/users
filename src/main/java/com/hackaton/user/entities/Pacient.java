package com.hackaton.user.entities;

import java.time.LocalDate;

public record Pacient(String name, LocalDate dateOfBirth, String cpf,
                          String phone, String email, String password, String endereco){}


