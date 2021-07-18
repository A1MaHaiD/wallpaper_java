package com.example.wallpaperjava.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.models.Collection;
import com.example.wallpaperjava.utils.GlideApp;

import java.util.List;

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
    public Object getItem(int position) {
        return collections.get(position);
    }

    @Override
    public long getItemId(int position) {
        return collections.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CollectionsVH collectionsVH;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_collection, parent, false);
            collectionsVH = new CollectionsVH(convertView);
            convertView.setTag(collectionsVH);
        } else {
            collectionsVH = (CollectionsVH) convertView.getTag();
        }

        ButterKnife.bind(this, convertView);
        Collection collection = collections.get(position);
        if (collection.getTitle() != null) {
            collectionsVH.title.setText(collection.getTitle());
        }
        collectionsVH.totalPhotos.setText(String.valueOf(collection.getTotalPhotos()) + " photos");
        GlideApp
                .with(context)
                .load(collection.getCoverPhoto().getUrl().getRegular())
                .into(collectionsVH.collectionPhoto);
        return convertView;
    }
}
