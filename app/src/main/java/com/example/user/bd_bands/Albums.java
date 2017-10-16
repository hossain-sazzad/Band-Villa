package com.example.user.bd_bands;

/**
 * Created by User on 19-Aug-17.
 */

public class Albums {
    String name;
    int id;
    String band_name;

    public Albums(String name,  String band_name) {
        this.name = name;

        this.band_name = band_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBand_name() {
        return band_name;
    }

    public void setBand_name(String band_name) {
        this.band_name = band_name;
    }
}
