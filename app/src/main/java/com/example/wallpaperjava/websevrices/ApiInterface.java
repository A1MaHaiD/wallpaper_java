package com.example.wallpaperjava.websevrices;

import com.example.wallpaperjava.models.Collection;
import com.example.wallpaperjava.models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("photos")
    Call<List<Photo>> getPhotos();

    @GET("collections")
    Call<List<Collection>> getCollections();

    @GET("collections/{id}")
    Call<Collection> getInformationOfCollection(@Path("id") int id);

    @GET("collections/{}/photos")
    Call<List<Photo>> getPhotosOfCollection(@Path("id") int id);

    @GET("photo/{id}")
    Call<Photo> getPhoto(@Path("id") String id);
}
