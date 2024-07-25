package com.hackaton.user.application.useCases;


import com.hackaton.user.application.gateways.DoctorGateway;
import com.hackaton.user.entities.Doctor;

import java.util.List;

public class DoctorInteractor {

    private DoctorGateway doctorGateway;

    public DoctorInteractor(DoctorGateway doctorGateway) {
        this.doctorGateway = doctorGateway;
    }

    public List<Doctor> getDoctors (){
        return doctorGateway.getDoctors();
    }

    public Doctor createDoctor (Doctor doctor){
        return doctorGateway.createDoctor(doctor);
    }

    public Doctor updateDoctor (Doctor doctor, Long id){
        return doctorGateway.updateDoctor(doctor, id);
    }

    public void deleteDoctor (Long doctorId){
        doctorGateway.deleteDoctor(doctorId);
    }
}
