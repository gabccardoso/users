package com.hackaton.user.infrastructure.gateways.utils.dtos;

import com.hackaton.user.Enums.Operacao;
import com.hackaton.user.entities.Pacient;

public record RabbitPacientDTO(Pacient pacient, Operacao operacao) {
}
