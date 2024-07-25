package com.hackaton.user.infrastructure.gateways.utils.dtos;

import com.hackaton.user.Enums.Operacao;
import com.hackaton.user.entities.Doctor;

public record RabbitDoctorDTO(Doctor doctor, Operacao operacao) {
}
