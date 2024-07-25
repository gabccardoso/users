package com.hackaton.user.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    DoctorEntity findByCrm(String crm);
}
