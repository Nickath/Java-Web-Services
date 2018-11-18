package patientservicerestwsclient.restwsclient;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import patientservicerestwsclient.restwsclient.model.Patient;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@SpringBootApplication
public class RestwsclientApplication {

    private static final String PATIENT_URL = "http://localhost:8080/restws/services/patientservice";
    public static void main(String[] args) {
        //SpringApplication.run(RestwsclientApplication.class, args);
        //create the javax-rs client
        Client client = ClientBuilder.newClient();
        //create the webtarget to hit
        WebTarget webTarget = client.target("http://localhost:8080/restws/services/patientservice/patients/123");

        //create the invocation builder from the target
        Invocation.Builder request = webTarget.request();

        //get the response
        Response reponse = request.get();

        //if we want to map the response to a POJO
        Patient patient = request.get(Patient.class);


        //pass dynamically parameters to the targer URI
        webTarget = client.target(PATIENT_URL).path("/patients").path("/{id}");
        webTarget.resolveTemplate("id", 123);

        System.out.println(reponse.getStatus());
        System.out.println(patient.getId());



    //   getPatientDynamically(123);
        updatePatientDynamically(123);


        Patient newPatient = new Patient("Bob");
        createPatient(newPatient);

        deletePatientDynamically(123);

    }

    private static void getPatientDynamically(long id){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(PATIENT_URL).path("/patients").path("/{id}").resolveTemplate("id", id);
        Invocation.Builder request = webTarget.request();
        Patient patient = request.get(Patient.class); //to map the response to a POJO
        Response response = request.get(); //to simply get the response
        System.out.println(patient.getId());
        response.close(); // it is a good practice to close the response, this will make sure that any of the socket resources will not be held
        //up and they will be cleaned right
        client.close();


    }

    private static void updatePatientDynamically(long id){
        Patient patient = new Patient(123,"Nick");
        Client client = ClientBuilder.newClient();
        WebTarget putTarget = client.target(PATIENT_URL).path("/patients").path("/{id}").resolveTemplate("id", id);
        Invocation.Builder request = putTarget.request();
      // Response updateResponseJson = request.put(Entity.entity(patient, MediaType.APPLICATION_JSON));
      //  System.out.println(updateResponseJson.getStatus());
        Response updateResponseXml = request.put(Entity.entity(patient, MediaType.APPLICATION_XML));
        System.out.println(updateResponseXml.getStatus());
        updateResponseXml.close(); // it is a good practice to close the response, this will make sure that any of the socket resources will not be held
        //up and they will be cleaned right
        client.close();
    }

    private static void createPatient(Patient newPatient){
        Client client = ClientBuilder.newClient();
        WebTarget postTarget = client.target(PATIENT_URL).path("/patients");
        Patient createdPatient = postTarget.request().post(Entity.entity(newPatient, MediaType.APPLICATION_XML), Patient.class);
        System.out.println("Patient successfully created with id = "+ createdPatient.getId() +" and name = "+ createdPatient.getName());
        client.close();
    }


    private static void deletePatientDynamically(long id){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(PATIENT_URL).path("/patients").path("/{id}").resolveTemplate("id", id);
        Invocation.Builder request = webTarget.request();
        Response response = request.delete(); //to simply get the response
        response.close(); // it is a good practice to close the response, this will make sure that any of the socket resources will not be held
        //up and they will be cleaned right
        client.close();


    }
}
