package com.example.wallpaperjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.models.Collection;
import com.example.wallpaperjava.utils.GlideApp;
import com.example.wallpaperjava.utils.SquareImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionsAdapter extends BaseAdapter {
    private Context context;
    private List<Collection> collections;

    public CollectionsAdapter(Context context, List<Collection> collections) {
        this.context = context;
        this.collections = collections;
    }

    @Override
    public int getCount() {
        return collections.size();
    }

    @Override
    public Object getItem(int i) {
        return collections.get(i);
    }

    @Override
    public long getItemId(int i) {
        return collections.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        final CollectionsVH collectionsVH;
        if (view == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_collection, parent, false);
            collectionsVH = new CollectionsVH(view);
            view.setTag(collectionsVH);
        } else {
            collectionsVH = (CollectionsVH) view.getTag();
        }

        ButterKnife.bind(this, view);
        Collection collection = collections.get(i);
        if (collection.getTitle() != null) {
            collectionsVH.title.setText(collection.getTitle());
        }
        collectionsVH.totalPhotos.setText(String.valueOf(collection.getTotalPhotos()) + " photos");
        GlideApp
                .with(context)
                .load(collection.getCoverPhoto().getUrl().getRegular())
                .into(collectionsVH.collectionPhoto);
        return view;
    }

    static class CollectionsVH {
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
}
