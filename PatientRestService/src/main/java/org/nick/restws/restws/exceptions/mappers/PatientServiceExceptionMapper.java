package org.nick.restws.restws.exceptions.mappers;

import org.nick.restws.restws.exceptions.PatientServiceException;
import org.springframework.stereotype.Component;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Component//we have to put the @component so that the ExceptionMapper will be triggered and map the PatientServiceException with the toResponse function
public class PatientServiceExceptionMapper implements ExceptionMapper<PatientServiceException> {

    private static final long serialVersionUID = 1L;

    @Override
    public Response toResponse(PatientServiceException e) {
        System.out.println(e.getMessage());
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"Status\":\"error\"");
        sb.append(",");
        sb.append("\"message\":\"Id was not found\"");
        sb.append("}");
        return Response.serverError().entity(sb.toString()).type(MediaType.APPLICATION_JSON).build();
        //return Response.status(404).build();
    }
}
