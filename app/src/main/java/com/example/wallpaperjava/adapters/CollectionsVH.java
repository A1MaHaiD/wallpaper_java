package com.example.wallpaperjava.adapters;

import android.view.View;
import android.widget.TextView;

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

    public CollectionsVH(View view){
        ButterKnife.bind(this, view);
    }
}
