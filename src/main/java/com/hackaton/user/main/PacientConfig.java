package com.hackaton.user.main;

import com.hackaton.user.application.gateways.PacientGateway;
import com.hackaton.user.application.useCases.PacientInteractor;
import com.hackaton.user.infrastructure.controllers.dto.PacientDTOMapper;
import com.hackaton.user.infrastructure.gateways.PacientEntityMapper;
import com.hackaton.user.infrastructure.gateways.PacientRepositoryGateway;
import com.hackaton.user.infrastructure.gateways.utils.RabbitSendUtil;
import com.hackaton.user.infrastructure.persistence.PacientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PacientConfig {

    @Bean
    PacientInteractor pacientInteractor (PacientGateway pacientGateway){
        return new PacientInteractor(pacientGateway);
    }

    @Bean
    PacientGateway pacientGateway (PacientRepository pacientRepository, PacientEntityMapper pacientEntityMapper,
                                   RabbitSendUtil rabbitSendUtil){
        return new PacientRepositoryGateway(pacientRepository, pacientEntityMapper, rabbitSendUtil);
    }

    @Bean
    PacientEntityMapper pacientEntityMapper(){
        return new PacientEntityMapper();
    }

    @Bean
    PacientDTOMapper pacientDTOMapper(){
        return new PacientDTOMapper();
    }
}
