package com.example.wallpaperjava.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.wallpaperjava.R;
import com.example.wallpaperjava.databinding.ActivityFullscreenPhotoBinding;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


public class FullscreenPhotoActivity extends AppCompatActivity {
    @BindView(R.id.iv_activity_fullscreen_photo)
    ImageView photo;
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

    private Unbinder unbinder;
    private ActivityFullscreenPhotoBinding binding;

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
    }

    private void getPhoto(String id){}

    @OnClick(R.id.fab_activity_fullscreen_photo_favorite)
    public void setFabFavorite(){
        fabMenu.close(true);
    }
    @OnClick(R.id.fab_activity_fullscreen_photo_wallpaper)
    public void setFabWallpaper(){
        fabMenu.close(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
