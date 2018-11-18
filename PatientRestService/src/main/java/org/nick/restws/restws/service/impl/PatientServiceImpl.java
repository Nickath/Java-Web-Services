package org.nick.restws.restws.service.impl;
import org.nick.restws.restws.exceptions.PatientServiceException;
import org.nick.restws.restws.model.Patient;
import org.nick.restws.restws.service.PatientService;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.*;

@Service
public class PatientServiceImpl implements PatientService {

    Map<Long, Patient> patients = new HashMap<>();
    long currentId = 123;

    public PatientServiceImpl(){
        init();
    }

    public void init(){
        Patient patient = new Patient();
        patient.setId(currentId);
        patient.setName("John");
        patients.put(patient.getId(), patient);
    }

    @Override
    public List<Patient> getPatients() {
        List results = new ArrayList<>(patients.values());
        return  results;
    }

    @Override
    public Patient getPatient(Long id) {
        if(patients.get(id) == null){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
            //alternative way
            //throw new NotFoundException();
        }
        return patients.get(id);
    }

    @Override
    public Response createPatient(Patient patient) {
        patient.setId(++currentId);
        patients.put(patient.getId(),patient);
        return Response.ok(patient).build();
    }

    @Override
    public Response updatePatient(Patient patient) {
        Response response = null;
        Patient patientOld = patients.get(patient.getId());
        if(patientOld != null){
            patients.put(patient.getId(), patient);
            response = Response.ok(patient).build();
        }
        else{
          // response = Response.notModified(String.valueOf(patient)).build();
           throw new PatientServiceException();
        }
        return response;
    }

    @Override
    public Response updatePatientId(Long id, Patient patient) {
        Response response = null;
        Patient patientOld = patients.get(id);
        if(patientOld != null){
            if(patient.getId() == 0){
                patient.setId(id);
            }
            else{
                patient.setId(id);
            }
            patients.put(id, patient);
            response = Response.ok(patient).build();
        }
        else{
            //response = Response.notModified().build();
            throw new PatientServiceException();
        }
        return response;
    }


    @Override
    public Response deletePatient(Long id) {
        Response response;
        Patient patient = patients.get(id);
        if(patient != null){
            patients.remove(patient.getId());
            response = Response.ok().build();
        }
        else{
            response = Response.notModified().build();
        }
        return response;
    }



}
