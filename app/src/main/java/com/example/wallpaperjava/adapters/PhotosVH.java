package com.example.wallpaperjava.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.models.Photo;
import com.example.wallpaperjava.ui.FullscreenPhotoActivity;
import com.example.wallpaperjava.utils.SquareImage;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotosVH extends RecyclerView.ViewHolder {
    private List<Photo> photos;
    private Context context;

    @BindView(R.id.civ_user_avatar)
    CircleImageView userAvatar;
    @BindView(R.id.tv_username)
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
