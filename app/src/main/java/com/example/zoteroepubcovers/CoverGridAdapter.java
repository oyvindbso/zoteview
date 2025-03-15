package com.example.zoteroepubcovers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class CoverGridAdapter extends RecyclerView.Adapter<CoverGridAdapter.CoverViewHolder> {

    private final Context context;
    private final List<EpubCoverItem> coverItems;
    private final CoverClickListener listener;

    public interface CoverClickListener {
        void onCoverClick(EpubCoverItem item);
    }

    public CoverGridAdapter(Context context, List<EpubCoverItem> coverItems, CoverClickListener listener) {
        this.context = context;
        this.coverItems = coverItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item_cover, parent, false);
        return new CoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoverViewHolder holder, int position) {
        EpubCoverItem item = coverItems.get(position);
        
        // Load cover image
        if (item.getCoverPath() != null) {
            Glide.with(context)
                    .load(new File(item.getCoverPath()))
                    .placeholder(R.drawable.placeholder_cover)
                    .error(R.drawable.placeholder_cover)
                    .centerCrop()
                    .into(holder.coverImage);
        } else {
            Glide.with(context)
                    .load(R.drawable.placeholder_cover)
                    .centerCrop()
                    .into(holder.coverImage);
        }
        
        // Set title
        holder.titleText.setText(item.getTitle());
        
        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCoverClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coverItems.size();
    }

    static class CoverViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImage;
        TextView titleText;

        public CoverViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.imageCover);
            titleText = itemView.findViewById(R.id.textTitle);
        }
    }
}
