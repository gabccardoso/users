package com.hackaton.user.application.useCases;


import com.hackaton.user.application.gateways.PacientGateway;
import com.hackaton.user.entities.Pacient;

import java.util.List;

public class PacientInteractor {

    private PacientGateway pacientGateway;

    public PacientInteractor(PacientGateway pacientGateway) {
        this.pacientGateway = pacientGateway;
    }

    public List<Pacient> getPacients (){
        return pacientGateway.getPacients();
    }

    public Pacient createPacient (Pacient pacient){
        return pacientGateway.createPacient(pacient);
    }

    public Pacient updatePacient (Pacient pacient, Long id){
        return pacientGateway.updatePacient(pacient, id);
    }

    public void deletePacient (Long pacientId){
        pacientGateway.deletePacient(pacientId);
    }
}
