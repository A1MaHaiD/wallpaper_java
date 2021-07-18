package com.example.wallpaperjava.ui.collections;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.wallpaperjava.databinding.FragmentCollectionsBinding;

public class CollectionsFragment extends Fragment {

//    private CollectionsViewModel collectionsViewModel;
    private FragmentCollectionsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        collectionsViewModel =
//                new ViewModelProvider(this).get(CollectionsViewModel.class);

        binding = FragmentCollectionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textCollections;
//        collectionsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}