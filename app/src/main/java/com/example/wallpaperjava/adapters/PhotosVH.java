package com.example.wallpaperjava.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.utils.SquareImage;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotosVH extends RecyclerView.ViewHolder {
    @BindView(R.id.civ_user_avatar)
    CircleImageView userAvatar;
    @BindView(R.id.tv_username)
    TextView username;
    @BindView(R.id.si_item_photo)
    SquareImage photo;
    public PhotosVH(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
