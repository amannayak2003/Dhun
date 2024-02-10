package com.example.dhun;

import java.io.Serializable;

public class audiomodel implements Serializable {
    String tittle;
    String path;
    String duration;

    public audiomodel(String tittle, String path, String duration) {
        this.tittle = tittle;
        this.path = path;
        this.duration = duration;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
