package com.hackaton.user.application.gateways;


import com.hackaton.user.entities.Doctor;
import com.hackaton.user.entities.Pacient;

import java.util.List;

public interface DoctorGateway {

    List<Doctor> getDoctors();

    Doctor createDoctor(Doctor doctor);

    Doctor updateDoctor(Doctor doctor, Long doctorId);

    void deleteDoctor(Long doctorId);

}
