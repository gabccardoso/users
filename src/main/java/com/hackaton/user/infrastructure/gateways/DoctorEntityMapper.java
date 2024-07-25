package com.hackaton.user.infrastructure.gateways;


import com.hackaton.user.entities.Doctor;
import com.hackaton.user.entities.Pacient;
import com.hackaton.user.infrastructure.persistence.DoctorEntity;
import com.hackaton.user.infrastructure.persistence.PacientEntity;

import java.util.ArrayList;
import java.util.List;

public class DoctorEntityMapper {

    DoctorEntity toEntity(Doctor doctor){
        return new DoctorEntity(doctor.name(), doctor.crm(), doctor.phone(), doctor.email(),
                doctor.dateOfBirth(), doctor.password());
    }

    Doctor toDomain(DoctorEntity doctorEntity){
        return new Doctor(doctorEntity.getName(), doctorEntity.getDateOfBirth(), doctorEntity.getCrm(),
                doctorEntity.getPhone(), doctorEntity.getEmail(), doctorEntity.getPassword(), null);
    }

    List<Doctor> toDomainList(List<DoctorEntity> doctorEntityList){
        List<Doctor> doctorList = new ArrayList<>();
        for(DoctorEntity doctorEntity : doctorEntityList){
            doctorList.add(new Doctor(doctorEntity.getName(), doctorEntity.getDateOfBirth(), doctorEntity.getCrm(),
                    doctorEntity.getPhone(), doctorEntity.getEmail(), doctorEntity.getPassword(), null));
        }
        return doctorList;
    }

}
