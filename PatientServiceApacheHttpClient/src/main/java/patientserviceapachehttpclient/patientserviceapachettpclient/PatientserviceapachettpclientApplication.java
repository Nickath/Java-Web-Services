package patientserviceapachehttpclient.patientserviceapachettpclient;

import com.google.gson.Gson;
import model.Patient;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class PatientserviceapachettpclientApplication {

    private static final String PATIENT_URL = "http://localhost:8080/restws/services/patientservice/patients";
    public static final String USER_AGENT = "Mozilla/5.0";
    public static void main(String[] args) {
        //SpringApplication.run(PatientserviceapachettpclientApplication.class, args);

        CloseableHttpClient httpClient  = HttpClientBuilder.create().build();
        getPatientDynamically(httpClient, 123);
        getPatientDynamicallyToObject(httpClient, 123);
        Patient patient = new Patient(123,"fsdfsd");
        updatePatientDynamically(httpClient,123,patient);
        createPatient(httpClient, new Patient("fsfddsffdsfsfsfdsf"));
        deletePatientDynamically(httpClient,123);



    }



    private static Patient getPatientDynamicallyToObject(HttpClient client, long id){
        Patient patient = null;
        String url = PATIENT_URL+"/"+id;
        HttpGet httpGetRequest = new HttpGet(url);
        httpGetRequest = (HttpGet) toJsonFormat(httpGetRequest); //convert it to JSON if you want, else the response will be XML formatted
        httpGetRequest.addHeader("models.User-Agent", USER_AGENT);
        try {
            HttpResponse response = client.execute(httpGetRequest);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            System.out.println("=============== RESULT ===============");
            System.out.println(result);
            patient = new Gson().fromJson(result.toString(), Patient.class);
            System.out.println(patient.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patient;
    }


    private static void getPatientDynamically(HttpClient client, long id){
        String url = PATIENT_URL+"/"+id;
        HttpGet httpGetRequest = new HttpGet(url);
        httpGetRequest = (HttpGet) toJsonFormat(httpGetRequest); //convert it to JSON if you want, else the response will be XML formatted
        httpGetRequest.addHeader("models.User-Agent", USER_AGENT);
        try {
            HttpResponse response = client.execute(httpGetRequest);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            System.out.println("=============== RESULT ===============");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    private static void updatePatientDynamically(HttpClient client, long id, Patient patient){
        String url = PATIENT_URL+"/"+id;
        Gson gson  = new Gson();
        try {
            StringEntity updateString = new StringEntity(gson.toJson(patient));
            HttpPut httpPutRequest = new HttpPut(url);
            httpPutRequest.setEntity(updateString);
            httpPutRequest.setHeader("Content-type","application/json");
            httpPutRequest.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(httpPutRequest);
            System.out.println(response.getStatusLine().toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private static void createPatient(HttpClient client, Patient patient){
        String url = PATIENT_URL+"/";
        Gson gson = new Gson();
        StringEntity postString = new StringEntity(gson.toJson(patient),"utf-8");
        postString.setContentEncoding("UTF-8");
        postString.setContentType("application/json");
        HttpPost post = new HttpPost(url);
        post.setEntity(postString);
        try {
            HttpResponse httpResponse = client.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void deletePatientDynamically(HttpClient client, long id){
        String url = PATIENT_URL+"/"+id;
        HttpDelete httpDeleteRequest = new HttpDelete(url);
        httpDeleteRequest = (HttpDelete) toJsonFormat(httpDeleteRequest); //convert it to JSON if you want, else the response will be XML formatted
        httpDeleteRequest.addHeader("models.User-Agent", USER_AGENT);
        try {
            HttpResponse response = client.execute(httpDeleteRequest);
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            System.out.println("=============== RESULT ===============");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static HttpRequest toJsonFormat(HttpRequestBase httpRequest){
        httpRequest.setHeader("Content-Type", "application/json");
        httpRequest.setHeader("Accept", "application/json");
        return httpRequest;
    }
}
