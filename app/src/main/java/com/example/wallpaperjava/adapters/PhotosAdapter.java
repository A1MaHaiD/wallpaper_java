package com.example.wallpaperjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.models.Photo;
import com.example.wallpaperjava.utils.GlideApp;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosVH> {
    private Context context;
    private List<Photo> photos;

    public PhotosAdapter(Context context, List<Photo> photos){
        this.context = context;
        this.photos =  photos;
    }
    @NonNull
    @NotNull
    @Override
    public PhotosVH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo,parent,false);
        return new PhotosVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PhotosVH holder, int position) {
        Photo photo = photos.get(position);
        holder.username.setText(photo.getUser().getUsername());
        GlideApp
                .with(context)
                .load(photo.getUrl().getRegular())
                .placeholder(R.drawable.placeholder)
                .override(600,600)
                .into(holder.photo);
        GlideApp
                .with(context)
                .load(photo.getUser().getProfileImage().getSmall())
                .into(holder.userAvatar);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }
}
