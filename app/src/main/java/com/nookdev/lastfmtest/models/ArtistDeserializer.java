package com.nookdev.lastfmtest.models;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;


public class ArtistDeserializer implements JsonDeserializer<Artist> {
    @Override
    public Artist deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Artist artist = new Gson().fromJson(json, Artist.class);

        List<Image> images = artist.getImages();
        if (images != null) {
            for (Image image : images) {
                if (image.getSize().equalsIgnoreCase(Image.SIZE_LARGE)) {
                    artist.setImageUrl(image.getUrl());
                    break;
                }
            }
        }
        return artist;
    }
}
