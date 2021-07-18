package com.example.wallpaperjava.ui.collections;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.adapters.CollectionsAdapter;
import com.example.wallpaperjava.databinding.FragmentCollectionsBinding;

import com.example.wallpaperjava.models.Collection;
import com.example.wallpaperjava.utils.Functions;
import com.example.wallpaperjava.websevrices.ApiInterface;
import com.example.wallpaperjava.websevrices.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CollectionsFragment extends Fragment {
    private final String TAG = CollectionsFragment.class.getSimpleName();
    @BindView(R.id.pb_fragment_collections)
    ProgressBar progressBar;
    @BindView(R.id.gv_fragment_collections)
    GridView gridView;

    private CollectionsAdapter collectionsAdapter;
    private List<Collection> collections = new ArrayList<>();
    private Unbinder unbinder;
    private FragmentCollectionsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCollectionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        unbinder = ButterKnife.bind(this, root);

        collectionsAdapter = new CollectionsAdapter(getActivity(), collections);
        gridView.setAdapter(collectionsAdapter);

        showProgressBar(true);
        getCollections();

        return root;
    }

    @OnItemClick(R.id.gv_fragment_collections)
    public void setGridView(int position){
        Collection collection = collections.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("collectionId", collection.getId());
        CollectionFragment collectionFragment = new CollectionFragment();
        collectionFragment.setArguments(bundle);
        Functions.changeMainFragmentWithBack(getActivity(), collectionFragment);
    }

    private void getCollections() {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Collection>> call = apiInterface.getCollections();
        call.enqueue(new Callback<List<Collection>>() {
            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                if (response.isSuccessful()) {
                    collections.addAll(response.body());
                    collectionsAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "Fail " + response.message());
                }
                showProgressBar(false);
            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {
                Log.e(TAG, "Fail " + t.getMessage());
                showProgressBar(false);
            }
        });
    }

    private void showProgressBar(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
        }
        progressBar.setVisibility(View.GONE);
        gridView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        binding = null;
    }
}