package com.example.wallpaperjava.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.adapters.PhotosAdapter;
import com.example.wallpaperjava.databinding.FragmentFavoriteBinding;
import com.example.wallpaperjava.models.Photo;
import com.example.wallpaperjava.utils.RealmController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FavoriteFragment extends Fragment {

    @BindView(R.id.rv_fragment_favorite)
    RecyclerView recyclerView;
    @BindView(R.id.tv_fragment_favorite_notification)
    TextView notification;

    private PhotosAdapter photosAdapter;
    private List<Photo> photos = new ArrayList<>();
    private Unbinder unbinder;

    private FragmentFavoriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        unbinder = ButterKnife.bind(this,root);

        //@recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        photosAdapter = new PhotosAdapter(getActivity(), photos);
        recyclerView.setAdapter(photosAdapter);
        getPhotos();

        return root;
    }

    private void getPhotos(){
        RealmController realmController = new RealmController();
        photos.addAll(realmController.getPhotos());
        if (photos.size() == 0){
            notification.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            photosAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        binding = null;
    }
}