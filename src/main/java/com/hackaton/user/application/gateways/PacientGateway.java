package com.hackaton.user.application.gateways;


import com.hackaton.user.entities.Pacient;

import java.util.List;

public interface PacientGateway {

    List<Pacient> getPacients();

    Pacient createPacient(Pacient pacient);

    Pacient updatePacient(Pacient pacient, Long id);

    void deletePacient(Long pacientId);

}
