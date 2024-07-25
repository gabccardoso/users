package com.hackaton.user.infrastructure.gateways;

import com.hackaton.user.application.gateways.LoginGateway;
import com.hackaton.user.infrastructure.controllers.dto.LoginDTO;
import com.hackaton.user.infrastructure.gateways.utils.SessionUtil;
import com.hackaton.user.infrastructure.persistence.DoctorEntity;
import com.hackaton.user.infrastructure.persistence.DoctorRepository;
import com.hackaton.user.infrastructure.persistence.PacientEntity;
import com.hackaton.user.infrastructure.persistence.PacientRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

public class LoginRepositoryGateway implements LoginGateway {

    private final PacientRepository pacientRepository;
    private final DoctorRepository doctorRepository;
    private final SessionUtil sessionUtil;

    private static final long EXPIRATION_TIME = 1000 * 60 * 15;

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private String port;

    public LoginRepositoryGateway(PacientRepository pacientRepository, DoctorRepository doctorRepository, SessionUtil sessionUtil) {
        this.pacientRepository = pacientRepository;
        this.doctorRepository = doctorRepository;
        this.sessionUtil = sessionUtil;
    }

    @Override
    public String loginPacient(LoginDTO loginDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(loginDTO.password());
        PacientEntity pacient = pacientRepository.findByCpfOrEmail(loginDTO.identity(), loginDTO.email());
        if(pacient.getPassword().matches(encodedPassword)){
            return sessionUtil.storeSessionPacient(pacient);
        } else{
            throw new IllegalArgumentException("Usuário e/ou senha incorretos");
        }
    }

    @Override
    public String loginDoctor(LoginDTO loginDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(loginDTO.password());
        DoctorEntity doctorEntity = doctorRepository.findByCrm(loginDTO.identity());
        if(doctorEntity.getPassword().matches(encodedPassword)){
            return sessionUtil.storeSessionDoctor(doctorEntity);
        } else{
            throw new IllegalArgumentException("Usuário e/ou senha incorretos");
        }
    }



}
