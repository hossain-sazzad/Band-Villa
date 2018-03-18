package com.example.user.bd_bands;



/**
 * Created by User on 19-Aug-17.
 */

public class Bands {

    String name;

    String genre;

    String country;

    int formationyear;

    String lineup;

    String image;
    int id;

    public Bands(String name,String country, String genre,String lineup, String image, int formationyear) {
        this.name = name;
        this.genre = genre;
        this.lineup=lineup;
        this.country=country;
        this.image=image;
        this.formationyear=formationyear;


    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public void setFormationyear(int formationyear) {
        this.formationyear = formationyear;
    }

    public void setLineup(String lineup) {
        this.lineup = lineup;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getFormationyear() {

        return formationyear;
    }

    public String getLineup() {
        return lineup;
    }

    public String getImage() {
        return image;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getCountry() {

        return country;
    }

    public String getName() {

        return name;

    }

    public void setName(String name) {
        this.name = name;
    }
}
