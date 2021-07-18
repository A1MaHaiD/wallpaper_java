package com.example.wallpaperjava.ui.collections;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wallpaperjava.R;
import com.example.wallpaperjava.adapters.PhotosAdapter;
import com.example.wallpaperjava.databinding.FragmentCollectionBinding;
import com.example.wallpaperjava.models.Collection;
import com.example.wallpaperjava.models.Photo;
import com.example.wallpaperjava.utils.GlideApp;
import com.example.wallpaperjava.websevrices.ApiInterface;
import com.example.wallpaperjava.websevrices.ServiceGenerator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CollectionFragment extends Fragment {

    private String TAG = CollectionFragment.class.getSimpleName();
    @BindView(R.id.tv_username)
    TextView username;
    @BindView(R.id.tv_description_fragment_collection)
    TextView description;
    @BindView(R.id.civ_user_avatar)
    ImageView userAvatar;
    @BindView(R.id.tv_title_fragment_collection)
    TextView title;

    @BindView(R.id.pb_fragment_collection)
    ProgressBar progressBar;
    @BindView(R.id.rv_fragment_collection)
    RecyclerView recyclerView;

    private List<Photo> photos = new ArrayList<>();
    private PhotosAdapter photosAdapter;

    private Unbinder unbinder;
    private FragmentCollectionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCollectionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        unbinder = ButterKnife.bind(this, root);

        //@recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        photosAdapter = new PhotosAdapter(getActivity(), photos);
        recyclerView.setAdapter(photosAdapter);

        Bundle bundle = getArguments();
        assert bundle != null;
        int collectionId = bundle.getInt("collectionId");
        showProgressBar(true);
        getInformationOfCollection(collectionId);
        getPhotosOfCollection(collectionId);

        return root;
    }

    private void getInformationOfCollection(int collectionId) {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        //@maybe wrong
        Call<Collection> call = apiInterface.getInformationOfCollection(collectionId);
        call.enqueue(new Callback<Collection>() {
            @Override
            public void onResponse(@NotNull Call<Collection> call, @NotNull Response<Collection> response) {
                if (response.isSuccessful()) {
                    Collection collection = response.body();
                    assert collection != null;
                    title.setText(collection.getTitle());
                    description.setText(collection.getDescription());
                    username.setText(collection.getUser().getUsername());
                    GlideApp
                            .with(getActivity())
                            .load(collection.getUser().getProfileImage().getSmall())
                            .into(userAvatar);
                } else {
                    Log.d(TAG, "Fail " +response.message());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Collection> call, @NotNull Throwable t) {
                Log.d(TAG, "Fail " + t.getMessage());
            }
        });
    }

    private void getPhotosOfCollection(int collectionId) {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class);
        Call<List<Photo>> call = apiInterface.getPhotosOfCollection(collectionId);
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(@NotNull Call<List<Photo>> call, @NotNull Response<List<Photo>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    photos.addAll(response.body());
                    photosAdapter.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "Fail " +response.message());
                }
                showProgressBar(false);
            }

            @Override
            public void onFailure(@NotNull Call<List<Photo>> call, @NotNull Throwable t) {
                Log.d(TAG, "Fail " + t.getMessage());
                showProgressBar(false);
            }
        });
    }

    private void showProgressBar(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        binding = null;
    }
}
