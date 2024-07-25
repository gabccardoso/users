package com.hackaton.user.infrastructure.controllers.dto;

import com.hackaton.user.entities.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorDTOMapper {
    public Doctor toDomain(DoctorDTO doctorDTO){
        return new Doctor(doctorDTO.name(), doctorDTO.dateOfBirth(), doctorDTO.crm(), doctorDTO.phone(),
                doctorDTO.email(), doctorDTO.password(), doctorDTO.endereco());
    }

    public DoctorDTO toDTO(Doctor doctor){
        return new DoctorDTO(doctor.name(), doctor.dateOfBirth(), doctor.crm(), doctor.phone(),
                doctor.email(), doctor.password(), doctor.endereco());
    }

    public List<Doctor> toDomainList(List<DoctorDTO> doctorDTOList){
        List<Doctor> doctorList = new ArrayList<>();
        for(DoctorDTO doctorDTO : doctorDTOList){
            doctorList.add(new Doctor(doctorDTO.name(), doctorDTO.dateOfBirth(), doctorDTO.crm(), doctorDTO.phone(),
                    doctorDTO.email(), doctorDTO.password(), doctorDTO.endereco()));
        }
        return doctorList;
    }

    public List<DoctorDTO> toDTOList(List<Doctor> doctorList){
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        for(Doctor doctor : doctorList){
            doctorDTOList.add(new DoctorDTO(doctor.name(), doctor.dateOfBirth(), doctor.crm(), doctor.phone(),
                    doctor.email(), doctor.password(), doctor.endereco()));
        }
        return doctorDTOList;
    }
}


