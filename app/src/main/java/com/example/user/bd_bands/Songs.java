package com.example.user.bd_bands;

/**
 * Created by User on 19-Aug-17.
 */

public class Songs {
    String name;
    String album_name;
    int id;

    public Songs(String name, String album_name) {
        this.name = name;
        this.album_name = album_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
