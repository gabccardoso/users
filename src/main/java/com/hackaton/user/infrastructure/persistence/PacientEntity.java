package com.hackaton.user.infrastructure.persistence;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "PACIENT")
@Entity
public class PacientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private LocalDate dateOfBirth;
    private String password;

    public PacientEntity(String name, String cpf, String phone, String email, LocalDate dateOfBirth, String password) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public PacientEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}