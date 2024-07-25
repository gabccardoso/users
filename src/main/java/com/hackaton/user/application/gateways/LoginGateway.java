package com.hackaton.user.application.gateways;

import com.hackaton.user.infrastructure.controllers.dto.LoginDTO;

public interface LoginGateway {
    String loginPacient (LoginDTO loginDTO);
    String loginDoctor (LoginDTO loginDTO);
}
