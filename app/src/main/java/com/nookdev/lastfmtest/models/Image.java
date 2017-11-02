package com.nookdev.lastfmtest.models;


import com.google.gson.annotations.SerializedName;

public class Image {
    public static final String SIZE_LARGE = "large";
    @SerializedName("size")
    String size;

    @SerializedName("#text")
    String url;

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
