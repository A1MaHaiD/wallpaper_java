package com.example.wallpaperjava.ui.photos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.adapters.PhotosAdapter;
import com.example.wallpaperjava.databinding.FragmentPhotosBinding;
import com.example.wallpaperjava.models.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PhotosFragment extends Fragment {
    private final String TAG = PhotosFragment.class.getSimpleName();
    @BindView(R.id.pb_fragment_photos)
    ProgressBar progressBar;
    @BindView(R.id.rv_fragment_photos)
    RecyclerView recyclerView;

    private PhotosAdapter photosAdapter;
    private List<Photo> photos  = new ArrayList<>();

    private Unbinder unbinder;
    private FragmentPhotosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPhotosBinding.inflate(inflater, container, false);
        //@maybe something wrong
        unbinder = ButterKnife.bind(this, binding.getRoot());
        View root = binding.getRoot();
//        //@maybe something wrong
//        //added progressBar && recyclerView
//        final RecyclerView recyclerView = binding.rvFragmentPhotos;
//        final ProgressBar progressBar = binding.pbFragmentPhotos;
        //@recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        photosAdapter = new PhotosAdapter(getActivity(),photos);
        recyclerView.setAdapter(photosAdapter);
        return root;
    }

    //todo need add @getPhoto function

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        binding = null;
    }
}