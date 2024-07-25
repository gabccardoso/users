package com.hackaton.user.infrastructure.gateways;


import com.hackaton.user.Enums.Operacao;
import com.hackaton.user.application.gateways.DoctorGateway;
import com.hackaton.user.entities.Doctor;
import com.hackaton.user.infrastructure.gateways.utils.RabbitSendUtil;
import com.hackaton.user.infrastructure.persistence.DoctorEntity;
import com.hackaton.user.infrastructure.persistence.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

public class DoctorRepositoryGateway implements DoctorGateway {

    private final DoctorRepository doctorRepository;
    private final DoctorEntityMapper doctorEntityMapper;
    private final RabbitSendUtil rabbitSendUtil;

    public DoctorRepositoryGateway(DoctorRepository doctorRepository, DoctorEntityMapper doctorEntityMapper, RabbitSendUtil rabbitSendUtil) {
        this.doctorRepository = doctorRepository;
        this.doctorEntityMapper = doctorEntityMapper;
        this.rabbitSendUtil = rabbitSendUtil;
    }

    @Override
    public List<Doctor> getDoctors() {
        List<DoctorEntity> doctorEntityList = doctorRepository.findAll();
        return doctorEntityMapper.toDomainList(doctorEntityList);
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        DoctorEntity doctorEntity = doctorRepository.save(doctorEntityMapper.toEntity(doctor));
        rabbitSendUtil.SendToDoctorList(doctor, Operacao.CRIAR);
        return doctorEntityMapper.toDomain(doctorEntity);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor, Long doctorId) {
        DoctorEntity doctorEntity = doctorRepository.findById(doctorId).orElseThrow(() -> new
                EntityNotFoundException("Doctor with id " + doctorId + " not found"));
        String encodedPassword = passwordEnconded(doctor.password());
        doctorEntity.setCrm(doctor.crm());
        doctorEntity.setEmail(doctor.email());
        doctorEntity.setName(doctor.name());
        doctorEntity.setPhone(doctor.phone());
        doctorEntity.setPassword(encodedPassword);
        doctorRepository.save(doctorEntity);
        rabbitSendUtil.SendToDoctorList(doctor, Operacao.ATUALIZAR);
        return doctorEntityMapper.toDomain(doctorEntity);

    }

    @Override
    public void deleteDoctor(Long doctorId) {
        DoctorEntity doctorEntity = doctorRepository.findById(doctorId).orElseThrow(() -> new
                EntityNotFoundException("Doctor with id " + doctorId + " not found"));
        rabbitSendUtil.SendToDoctorList(doctorEntityMapper.toDomain(doctorEntity), Operacao.EXCLUIR);
        doctorRepository.deleteById(doctorId);
    }

    private String passwordEnconded(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
