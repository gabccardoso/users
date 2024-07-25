package com.hackaton.user.infrastructure.controllers;

import com.hackaton.user.application.useCases.LoginInteractor;
import com.hackaton.user.infrastructure.controllers.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class LoginController {

    private final LoginInteractor loginInteractor;

    public LoginController(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    @PostMapping(value = "/pacient")
    public ResponseEntity<String> loginPacient(@RequestBody LoginDTO loginDTO){
        String sessionKey = loginInteractor.loginPacient(loginDTO);
        return ResponseEntity.ok(sessionKey);
    }

    @PostMapping(value = "/doctor")
    public ResponseEntity<String> loginDoctor(@RequestBody LoginDTO loginDTO){
        String sessionKey = loginInteractor.loginDoctor(loginDTO);
        return ResponseEntity.ok(sessionKey);
    }

}
