package com.hackaton.user.infrastructure.gateways;


import com.hackaton.user.entities.Pacient;
import com.hackaton.user.infrastructure.persistence.PacientEntity;

import java.util.ArrayList;
import java.util.List;

public class PacientEntityMapper {

    PacientEntity toEntity(Pacient pacient){
        return new PacientEntity(pacient.name(), pacient.cpf(), pacient.phone(), pacient.email(),
                pacient.dateOfBirth(), pacient.password());
    }

    Pacient toDomain(PacientEntity pacient){
        return new Pacient(pacient.getName(), pacient.getDateOfBirth(), pacient.getCpf(), pacient.getPhone(),
                pacient.getEmail(), pacient.getPassword());
    }

    List<Pacient> toDomainList(List<PacientEntity> pacientEntityList){
        List<Pacient> pacientList = new ArrayList<>();
        for(PacientEntity pacient:pacientEntityList){
            pacientList.add(new Pacient(pacient.getName(), pacient.getDateOfBirth(), pacient.getCpf(), pacient.getPhone(),
                    pacient.getEmail(), pacient.getPassword()));
        }
        return pacientList;
    }

}
