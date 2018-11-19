package org.nick.java.passengerservice.service;

import org.nick.java.passengerservice.model.Passenger;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;

@Path("/passengerservice")
@Produces("application/xml")
@Consumes("application/xml,application/x-www-form-urlencoded") //x-www-form-urlencoded is for form-attributes
public interface PassengerService {

    @Path("/passengers")
    @GET
    List<Passenger> getPassengers() ;

    @Path("/passengers")
    @GET
    List<Passenger> getPassengers(@QueryParam("start") int start, @QueryParam("size") int size) ;

    @Path("/passengers")
    @POST
    Passenger addPassenger(Passenger passenger);

    @Path("/addPassengerWithFormParams")
    @POST
    void addPassengerWithFormParam(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName);

    @Path("/addPassengerWithHeaderParam")
    @POST
    void addPassengerWithHeaderParam(@HeaderParam("agent") String agent, @FormParam("firstName") String firstName);

    @Path("/addPassengerWithHeaderParams")
    @POST
    void addPassengerWithHeaderParams(@Context HttpHeaders httpHeaders, String firstName);
}
