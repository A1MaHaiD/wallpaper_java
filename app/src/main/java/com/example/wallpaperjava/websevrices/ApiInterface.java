package com.example.wallpaperjava.websevrices;

import com.example.wallpaperjava.models.Collection;
import com.example.wallpaperjava.models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("photos")
    Call<List<Photo>> getPhotos();

    @GET("collections")
    Call<List<Collection>> getCollection();

}
