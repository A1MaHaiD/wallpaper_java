package com.example.wallpaperjava.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.utils.SquareImage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionsVH {
    @BindView(R.id.tv_title_item_collections)
    TextView title;
    @BindView(R.id.tv_total_photos_item_collections)
    TextView totalPhotos;
    @BindView(R.id.si_item_collections)
    SquareImage collectionPhoto;

    public CollectionsVH(@NonNull View itemView){
        ButterKnife.bind(this, itemView);
    }
}
