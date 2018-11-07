package org.nick.restws.restws.service;

import org.nick.restws.restws.model.Patient;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Consumes("application/xml,application/json") //the endpoint accepts both xml and json data
@Produces("application/xml,application/json") //the function under this endpoint can return both xml and json data
@Path("/patientservice")
public interface PatientService {

    @Path("/patients")
    @GET
    List<Patient> getPatients();

    @Path("/patients/{id}")
    @GET
    Patient getPatient(@PathParam(value = "id") Long id);

    @Path("/patients")
    @POST
    Response createPatient(Patient patient);

    @Path("/patients")
    @PUT
    Response updatePatient(Patient patient);

    @Path("/patients/{id}")
    @DELETE
    Response deletePatient(@PathParam(value = "id") Long id);
}
