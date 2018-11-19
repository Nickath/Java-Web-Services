package org.nick.java.passengerservice.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passenger")
public class Passenger {

    private int id;
    private String name;

    public Passenger(){

    }

    public Passenger(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Passenger(String firstName) {
        this.name = firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
