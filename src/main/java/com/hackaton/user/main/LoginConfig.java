package com.hackaton.user.main;

import com.hackaton.user.application.gateways.LoginGateway;
import com.hackaton.user.application.useCases.LoginInteractor;
import com.hackaton.user.infrastructure.gateways.LoginRepositoryGateway;
import com.hackaton.user.infrastructure.gateways.utils.SessionUtil;
import com.hackaton.user.infrastructure.persistence.DoctorRepository;
import com.hackaton.user.infrastructure.persistence.PacientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoginConfig {

    @Bean
    LoginInteractor loginInteractor(LoginGateway loginGateway) {
        return new LoginInteractor(loginGateway);
    }

    @Bean
    LoginGateway loginGateway(PacientRepository pacientRepository, DoctorRepository doctorRepository,
                              SessionUtil sessionUtil) {
        return new LoginRepositoryGateway(pacientRepository, doctorRepository, sessionUtil);
    }

    @Bean
    SessionUtil sessionUtil() {
        return new SessionUtil();
    }
}
