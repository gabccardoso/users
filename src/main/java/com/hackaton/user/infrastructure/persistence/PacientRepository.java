package com.hackaton.user.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientRepository extends JpaRepository<PacientEntity, Long> {
    PacientEntity findByCpfOrEmail(String cpf, String email);
}
