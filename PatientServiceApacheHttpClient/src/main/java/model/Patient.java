package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The model class, which will be used to store our data
 */
@XmlRootElement(name="patient")
public class Patient {

    private long id;
    private String name;

    public Patient() {

    }

    public Patient(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Patient(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
