package com.example.wallpaperjava.ui.photos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperjava.databinding.FragmentPhotosBinding;

public class PhotosFragment extends Fragment {

    private FragmentPhotosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentPhotosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //added progressBar && recyclerView
        final RecyclerView recyclerView = binding.rvFragmentPhotos;
        final ProgressBar progressBar = binding.pbFragmentPhotos;


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}