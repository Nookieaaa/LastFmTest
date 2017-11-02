package com.nookdev.lastfmtest.models;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.parceler.Parcel;

import java.util.List;


@Parcel
@Entity
public class Artist {
    @Id(autoincrement = true)
    long id;

    @SerializedName("name")
    String name;

    @SerializedName("listeners")
    long listeners;

    @SerializedName("mbid")
    @Property(nameInDb = "MBID")
    String mbid;

    @org.parceler.Transient
    @Transient
    @SerializedName("image")
    List<Image> images;

    String imageUrl;

    @Generated(hash = 1251411334)
    public Artist(long id, String name, long listeners, String mbid,
                  String imageUrl) {
        this.id = id;
        this.name = name;
        this.listeners = listeners;
        this.mbid = mbid;
        this.imageUrl = imageUrl;
    }

    @Generated(hash = 19829037)
    public Artist() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getListeners() {
        return this.listeners;
    }

    public void setListeners(long listeners) {
        this.listeners = listeners;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public List<Image> getImages() {
        return images;
    }

    public String getMbid() {
        return this.mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }
}
