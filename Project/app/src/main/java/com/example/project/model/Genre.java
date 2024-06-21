package com.example.project.model;

public class Genre {
    private String idgenre;
    private String name;

    public Genre(String idgenre, String name) {
        this.idgenre = idgenre;
        this.name = name;
    }


    public String getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(String idgenre) {
        this.idgenre = idgenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
