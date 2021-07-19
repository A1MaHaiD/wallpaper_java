package com.example.wallpaperjava.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.wallpaperjava.R;
import com.example.wallpaperjava.databinding.ActivityFullscreenPhotoBinding;
import com.example.wallpaperjava.models.Photo;
import com.example.wallpaperjava.utils.Functions;
import com.example.wallpaperjava.utils.GlideApp;
import com.example.wallpaperjava.utils.RealmController;
import com.example.wallpaperjava.websevrices.ApiInterface;
import com.example.wallpaperjava.websevrices.ServiceGenerator;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.jetbrains.annotations.NotNull;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FullscreenPhotoActivity extends AppCompatActivity {
    @BindView(R.id.iv_activity_fullscreen_photo)
    ImageView fullscreenPhoto;
    @BindView(R.id.civ_activity_fullscreen_photo)
    CircleImageView userAvatar;
    @BindView(R.id.tv_activity_fullscreen_photo_username)
    TextView username;
    @BindView(R.id.fam_activity_fullscreen_photo)
    FloatingActionMenu fabMenu;
    @BindView(R.id.fab_activity_fullscreen_photo_favorite)
    FloatingActionButton fabFavorite;
    @BindView(R.id.fab_activity_fullscreen_photo_wallpaper)
    FloatingActionButton fabWallpaper;

    @BindDrawable(R.drawable.ic_check_favorite)
    Drawable icFavorite;
    @BindDrawable(R.drawable.ic_check_favorited)
    Drawable icFavorited;

    private Bitmap photoBitmap;
    private Unbinder unbinder;
    private Photo photo;
    private ActivityFullscreenPhotoBinding binding;

    private RealmController realmController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullscreenPhotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        unbinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        String photoId = intent.getStringExtra("photoId");
        getPhoto(photoId);

        realmController = new RealmController();
        if (realmController.isPhotoExist(photoId)){
            fabFavorite.setImageDrawable(icFavorite);
        }
    }

    private void getPhoto(String id) {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<Photo> call = apiInterface.getPhoto(id);
        call.enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                if (response.isSuccessful()) {
                    photo = response.body();
                    updateUI(photo);
                }
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {

            }
        });
    }

    private void updateUI(Photo photo) {
        try {
            username.setText(photo.getUser().getUsername());
            GlideApp
                    .with(FullscreenPhotoActivity.this)
                    .load(photo.getUser().getProfileImage().getSmall())
                    .into(userAvatar);
            GlideApp
                    .with(FullscreenPhotoActivity.this)
                    .asBitmap()
                    .load(photo.getUrl().getRegular())
                    .centerCrop()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource,
                                                    Transition<? super Bitmap> transition) {
                            fullscreenPhoto.setImageBitmap(resource);
                            photoBitmap = resource;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @OnClick(R.id.fab_activity_fullscreen_photo_favorite)
    public void setFabFavorite() {
        if (realmController.isPhotoExist(photo.getId())){
            realmController.deletePhoto(photo);
            fabFavorite.setImageDrawable(icFavorite);
            Toast.makeText(FullscreenPhotoActivity.this,
                    "Remove Favorite",
                    Toast.LENGTH_LONG).show();
        } else {
            realmController.savePhoto(photo);
            fabFavorite.setImageDrawable(icFavorite);
            Toast.makeText(FullscreenPhotoActivity.this,
                    "Favorite",
                    Toast.LENGTH_LONG).show();
        }
        fabMenu.close(true);
    }

    @OnClick(R.id.fab_activity_fullscreen_photo_wallpaper)
    public void setFabWallpaper() {
        if (photoBitmap != null) {
            if (Functions.setWallpaper(FullscreenPhotoActivity.this, photoBitmap)) {
                Toast.makeText(FullscreenPhotoActivity.this,
                        "Set Wallpaper Successfully",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(FullscreenPhotoActivity.this,
                        "Set Wallpaper Fail",
                        Toast.LENGTH_LONG).show();
            }
        }

        fabMenu.close(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
