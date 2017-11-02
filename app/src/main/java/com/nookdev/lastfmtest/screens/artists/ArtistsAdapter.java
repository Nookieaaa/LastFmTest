package com.nookdev.lastfmtest.screens.artists;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nookdev.lastfmtest.R;
import com.nookdev.lastfmtest.models.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistViewHolder> {
    private final Picasso picasso;
    private OnArtistSelected artistSelectedCallback;

    private List<Artist> data;

    public ArtistsAdapter(Picasso picasso, OnArtistSelected artistSelectedCallback) {
        this.picasso = picasso;
        this.artistSelectedCallback = artistSelectedCallback;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.artists_list_item, parent, false);
        return new ArtistViewHolder(v);
    }

    public void setData(List<Artist> newData) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(getCallback(newData), true);
        result.dispatchUpdatesTo(this);
        data = newData;

    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        holder.bindModel(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private DiffUtil.Callback getCallback(List<Artist> newData) {
        return new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return data == null ? 0 : data.size();
            }

            @Override
            public int getNewListSize() {
                return newData.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                Artist oldItem = data.get(oldItemPosition);
                Artist newItem = newData.get(newItemPosition);
                return oldItem.getMbid().equals(newItem.getMbid());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                Artist oldItem = data.get(oldItemPosition);
                Artist newItem = newData.get(newItemPosition);
                return newItem.equals(oldItem);
            }
        };
    }

    public interface OnArtistSelected {
        void onArtistSelected(Artist artist);
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameView;
        TextView listenersView;
        View root;

        String listenersTemplate;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_artist_image);
            nameView = itemView.findViewById(R.id.tv_artist_name);
            listenersView = itemView.findViewById(R.id.tv_artist_listeners);
            listenersTemplate = itemView.getContext().getString(R.string.listeners_template);
            root = itemView.findViewById(R.id.artist_list_item_root);
            root.setOnClickListener(v -> {
                if (artistSelectedCallback != null)
                    artistSelectedCallback.onArtistSelected(data.get(getAdapterPosition()));
            });
        }

        public void bindModel(Artist artist) {
            nameView.setText(artist.getName());
            listenersView.setText(String.format(listenersTemplate, artist.getListeners()));
            picasso.load(artist.getImageUrl())
                    .fit()
                    .into(imageView);
        }
    }
}
