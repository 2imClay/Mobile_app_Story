package com.example.project.model;

import java.io.Serializable;

public class Genre implements Serializable {
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
