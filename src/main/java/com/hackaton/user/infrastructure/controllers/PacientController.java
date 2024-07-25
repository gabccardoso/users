package com.hackaton.user.infrastructure.controllers;

import com.hackaton.user.application.useCases.PacientInteractor;
import com.hackaton.user.entities.Pacient;
import com.hackaton.user.infrastructure.controllers.dto.PacientDTO;
import com.hackaton.user.infrastructure.controllers.dto.PacientDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pacient")
public class PacientController {

    private final PacientInteractor pacientInteractor;
    private final PacientDTOMapper pacientDTOMapper;

    public PacientController(PacientInteractor pacientInteractor, PacientDTOMapper pacientDTOMapper) {
        this.pacientInteractor = pacientInteractor;
        this.pacientDTOMapper = pacientDTOMapper;
    }


    @GetMapping
    public ResponseEntity<List<PacientDTO>> getPacients(){
        List<Pacient> pacient = pacientInteractor.getPacients();
        return ResponseEntity.status(HttpStatus.OK).body(pacientDTOMapper.toPacientDTOList(pacient));
    }

    @PostMapping
    public ResponseEntity<PacientDTO> createPacient(@RequestBody PacientDTO pacientDTO){
        Pacient pacient = pacientInteractor.createPacient(pacientDTOMapper.toPacient(pacientDTO));
        return ResponseEntity.status(HttpStatus.OK).body(pacientDTOMapper.toPacientDTO(pacient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacientDTO> updatePacient(@RequestBody PacientDTO pacientDTO, @PathVariable Long id){
        Pacient pacient = pacientInteractor.updatePacient(pacientDTOMapper.toPacient(pacientDTO), id);
        return ResponseEntity.status(HttpStatus.OK).body(pacientDTOMapper.toPacientDTO(pacient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePacient(@PathVariable Long id){
        pacientInteractor.deletePacient(id);
        return ResponseEntity.noContent().build();
    }
}
