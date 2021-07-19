package com.example.wallpaperjava.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.models.Photo;
import com.example.wallpaperjava.ui.FullscreenPhotoActivity;
import com.example.wallpaperjava.utils.GlideApp;
import com.example.wallpaperjava.utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosVH> {
    private Context context;
    private List<Photo> photos;

    public PhotosAdapter(Context context, List<Photo> photos){
        this.context = context;
        this.photos =  photos;
    }
    @NonNull
    @Override
    public PhotosVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo,parent,false);
        return new PhotosVH(itemView);
    }

    @Override
    public void onBindViewHolder(PhotosVH holder, int position) {
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
    public class PhotosVH extends RecyclerView.ViewHolder {

        @BindView(R.id.civ_item_photo_user_avatar)
        CircleImageView userAvatar;
        @BindView(R.id.tv_item_photo_username)
        TextView username;
        @BindView(R.id.si_item_photo)
        SquareImage photo;
        @BindView(R.id.item_photo_layout)
        FrameLayout frameLayout;

        public PhotosVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.item_photo_layout)
        public void setFrameLayout(){
            int position =  getAdapterPosition();
            //@maybe wrong Photos photos
            String photoId = photos.get(position).getId();
            //@maybe wrong Context context
            Intent intent = new Intent(context, FullscreenPhotoActivity.class);
            intent.putExtra("photoId", photoId);
            context.startActivity(intent);
        }
    }
}

