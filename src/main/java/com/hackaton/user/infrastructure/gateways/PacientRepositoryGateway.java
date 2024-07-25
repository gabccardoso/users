package com.hackaton.user.infrastructure.gateways;


import com.hackaton.user.Enums.Operacao;
import com.hackaton.user.application.gateways.PacientGateway;
import com.hackaton.user.entities.Pacient;
import com.hackaton.user.infrastructure.gateways.utils.RabbitSendUtil;
import com.hackaton.user.infrastructure.persistence.PacientEntity;
import com.hackaton.user.infrastructure.persistence.PacientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class PacientRepositoryGateway implements PacientGateway {

    private final PacientRepository pacientRepository;
    private final PacientEntityMapper pacientEntityMapper;
    private final RabbitSendUtil rabbitSendUtil;

    public PacientRepositoryGateway(PacientRepository pacientRepository, PacientEntityMapper pacientEntityMapper, RabbitSendUtil rabbitSendUtil) {
        this.pacientRepository = pacientRepository;
        this.pacientEntityMapper = pacientEntityMapper;
        this.rabbitSendUtil = rabbitSendUtil;
    }

    @Override
    public List<Pacient> getPacients() {
        List<PacientEntity> pacientEntity = pacientRepository.findAll();
        return pacientEntityMapper.toDomainList(pacientEntity);
    }

    @Override
    public Pacient createPacient(Pacient pacient) {
        PacientEntity pacientEntity = pacientRepository.save(pacientEntityMapper.toEntity(pacient));
        rabbitSendUtil.SendToPacientList(pacient, Operacao.CRIAR);
        return pacientEntityMapper.toDomain(pacientEntity);
    }

    @Override
    public Pacient updatePacient(Pacient pacient, Long id) {
        PacientEntity pacientEntity = pacientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Pacient with id " + id + " not found"));
        String encodedPassword = passwordEnconded(pacient.password());
        pacientEntity.setEmail(pacient.email());
        pacientEntity.setName(pacient.name());
        pacientEntity.setPhone(pacient.phone());
        pacientEntity.setPassword(encodedPassword);
        pacientRepository.save(pacientEntity);
        rabbitSendUtil.SendToPacientList(pacient, Operacao.ATUALIZAR);
        return pacientEntityMapper.toDomain(pacientEntity);
    }

    @Override
    public void deletePacient(Long pacientId) {
        PacientEntity pacientEntity = pacientRepository.findById(pacientId).orElseThrow(
                () -> new EntityNotFoundException("Pacient with id " + pacientId + " not found"));
        rabbitSendUtil.SendToPacientList(pacientEntityMapper.toDomain(pacientEntity), Operacao.EXCLUIR);
        pacientRepository.deleteById(pacientId);
    }

    private String passwordEnconded(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
