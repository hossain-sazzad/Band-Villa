package com.example.user.bd_bands;

/**
 * Created by User on 19-Aug-17.
 */

public class Bands {
    String name;
    String type;
    int id;

    public Bands(String name, String type) {
        this.name = name;
        this.type = type;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
