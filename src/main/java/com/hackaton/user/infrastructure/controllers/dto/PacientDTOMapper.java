package com.hackaton.user.infrastructure.controllers.dto;

import com.hackaton.user.entities.Pacient;

import java.util.ArrayList;
import java.util.List;

public class PacientDTOMapper {

    public PacientDTO toPacientDTO (Pacient pacient){
        return new PacientDTO(pacient.name(), pacient.dateOfBirth(), pacient.cpf(),
                pacient.phone(), pacient.email(), null, pacient.endereco());
    }

    public List<PacientDTO> toPacientDTOList (List<Pacient> pacientList){
        List<PacientDTO> pacientDTOList = new ArrayList<>();
        for(Pacient pacient : pacientList){
            pacientDTOList.add(new PacientDTO(pacient.name(), pacient.dateOfBirth(), pacient.cpf(),
                    pacient.phone(), pacient.email(), null, pacient.endereco()));
        }
        return pacientDTOList;
    }

    public Pacient toPacient (PacientDTO pacientDTO){
        return new Pacient(pacientDTO.name(), pacientDTO.dateOfBirth(), pacientDTO.cpf(),
                pacientDTO.phone(), pacientDTO.email(), pacientDTO.password(), pacientDTO.endereco());
    }

    public List<Pacient> toPacientList (List<PacientDTO> pacientDTOList){
        List<Pacient> pacientList = new ArrayList<>();
        for(PacientDTO pacient : pacientDTOList){
            pacientList.add(new Pacient(pacient.name(), pacient.dateOfBirth(), pacient.cpf(),
                    pacient.phone(), pacient.email(), pacient.password(), pacient.endereco()));
        }
        return pacientList;
    }
}
