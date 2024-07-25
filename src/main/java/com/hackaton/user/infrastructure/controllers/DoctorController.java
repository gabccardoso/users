package com.hackaton.user.infrastructure.controllers;

import com.hackaton.user.application.useCases.DoctorInteractor;
import com.hackaton.user.application.useCases.PacientInteractor;
import com.hackaton.user.entities.Doctor;
import com.hackaton.user.entities.Pacient;
import com.hackaton.user.infrastructure.controllers.dto.DoctorDTO;
import com.hackaton.user.infrastructure.controllers.dto.DoctorDTOMapper;
import com.hackaton.user.infrastructure.controllers.dto.PacientDTO;
import com.hackaton.user.infrastructure.controllers.dto.PacientDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {

    private final DoctorInteractor doctorInteractor;
    private final DoctorDTOMapper doctorDTOMapper;

    public DoctorController(DoctorInteractor doctorInteractor, DoctorDTOMapper doctorDTOMapper) {
        this.doctorInteractor = doctorInteractor;
        this.doctorDTOMapper = doctorDTOMapper;
    }


    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getDoctors(){
        List<Doctor> doctors = doctorInteractor.getDoctors();
        return ResponseEntity.status(HttpStatus.OK).body(doctorDTOMapper.toDTOList(doctors));
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO){
        Doctor doctor = doctorInteractor.createDoctor(doctorDTOMapper.toDomain(doctorDTO));
        return ResponseEntity.status(HttpStatus.OK).body(doctorDTOMapper.toDTO(doctor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctorDTO, @PathVariable Long id){
        Doctor doctor = doctorInteractor.updateDoctor(doctorDTOMapper.toDomain(doctorDTO), id);
        return ResponseEntity.status(HttpStatus.OK).body(doctorDTOMapper.toDTO(doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id){
        doctorInteractor.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
