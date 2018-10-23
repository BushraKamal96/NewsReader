package com.example.acer.newsreader.model;

import java.util.List;

/**
 * Created by acer on 10/16/2018.
 */

public class RootObject {
    public String status ;
    public List<Source> sources ;

    public RootObject() {
    }

    public RootObject(String status, List<Source> sources) {
        this.status = status;
        this.sources = sources;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
