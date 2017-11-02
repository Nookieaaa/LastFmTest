package com.nookdev.lastfmtest.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ArtistsResponse {
    @SerializedName("topartists")
    ArtistsResponseItems items;

    public List<Artist> getArtists() {
        if (items != null)
            return items.getArtists();
        else
            return new ArrayList<>();
    }

    class ArtistsResponseItems {
        @SerializedName("artist")
        List<Artist> artists;

        public List<Artist> getArtists() {
            return artists == null ? new ArrayList<>() : artists;
        }
    }
}
