package com.nookdev.lastfmtest.screens.albums;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;


public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder> {
    private final Picasso picasso;

    public AlbumsAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {

        public AlbumViewHolder(View itemView) {
            super(itemView);
        }
    }
}
