package com.hackaton.user.application.useCases;

import com.hackaton.user.application.gateways.LoginGateway;
import com.hackaton.user.infrastructure.controllers.dto.LoginDTO;

public class LoginInteractor {

    private final LoginGateway loginGateway;

    public LoginInteractor(LoginGateway loginGateway) {
        this.loginGateway = loginGateway;
    }

    public String loginPacient(LoginDTO loginDTO){
        return loginGateway.loginPacient(loginDTO);
    }
    public String loginDoctor(LoginDTO loginDTO){
        return loginGateway.loginDoctor(loginDTO);
    }
}
