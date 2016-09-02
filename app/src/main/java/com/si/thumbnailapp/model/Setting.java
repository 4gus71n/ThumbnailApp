package com.si.thumbnailapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class Setting {

    @SerializedName("HighlightColor")
    private String highlightColor;

    public String getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }
}
