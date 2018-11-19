package org.nick.java.passengerservice.service.impl;

import org.nick.java.passengerservice.model.Passenger;
import org.nick.java.passengerservice.service.PassengerService;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PassengerServiceImpl implements PassengerService {

    List<Passenger> passengers = new ArrayList<>();
    int currentId = 123;

    @Override
    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public List<Passenger> getPassengers(int start, int size) {
        if(start == 0 && size == 0 ){
            return getPassengers();
        }
        return passengers.subList(start, passengers.size()-1);
    }

    @Override
    public Passenger addPassenger(Passenger passenger) {
        passenger.setId(currentId++);
        passengers.add(passenger);
        return passenger;
    }

    @Override
    public void addPassengerWithFormParam(String firstName, String lastName) {
        Passenger passenger = new Passenger(currentId++,firstName);
        passengers.add(passenger);
        System.out.println("passenger created");
    }

    @Override
    public void addPassengerWithHeaderParam(String agent, String firstName) {
        Passenger passenger = new Passenger(firstName);
        passengers.add(passenger);
        System.out.println(agent);
    }

    @Override
    public void addPassengerWithHeaderParams(HttpHeaders httpHeaders, String firstName) {

        MultivaluedMap<String, String> allHeaders = httpHeaders.getRequestHeaders();

        Set<String> headerKeys = allHeaders.keySet();
        System.out.println("========== HEADERS ==========");
        for(String headerKey : headerKeys){
            System.out.print(headerKey +": ");
            System.out.println(httpHeaders.getHeaderString(headerKey));
        }

        System.out.println("============= COOKIES ==============");
        Map<String, Cookie> cookies = httpHeaders.getCookies();
        Set<String> cookieKeys = cookies.keySet();
        for(String cookieKey : cookieKeys){
            System.out.print(cookieKey +": ");
            System.out.println(cookies.get(cookieKey).getValue());
        }
    }


}
