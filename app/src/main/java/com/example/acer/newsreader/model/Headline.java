package com.example.acer.newsreader.model;

import java.util.List;

/**
 * Created by acer on 10/20/2018.
 */

public class Headline {
    public String status ;
    public List<Article> articles ;


    public Headline() {
    }

    public Headline(String status, List<Article> articles) {
        this.status = status;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
