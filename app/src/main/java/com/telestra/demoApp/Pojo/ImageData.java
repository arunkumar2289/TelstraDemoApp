package com.telestra.demoApp.Pojo;

import java.util.List;

/**
 * Created for JSON data from Server.
 */
public class ImageData {

   private String title; //Title from server


    private List<ImageDesc> rows;


    public List<ImageDesc> getRows() {
        return rows;
    }

    public void setRows(List<ImageDesc> rows) {
        this.rows = rows;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
