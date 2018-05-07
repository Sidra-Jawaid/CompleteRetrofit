package com.example.sidrajawaid.retrofitfinal;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //GET METHOD
    @GET("/posts/2")
    Call<Posts> getPost();


    // POST METHOD
    @POST("/posts")
    @FormUrlEncoded
    Call<Posts> savePost(
            @Field("title") String title,
            @Field("body") String body,
            @Field("userId") long id);


    //PATCH METHOD
    @PATCH("/posts/1")
    @FormUrlEncoded
    Call<Posts> PatchPost(
            @Field("body") String body);

    //DELETE METHOD
    @DELETE("/posts/{id}")
    Call<Posts> deletePost(@Path("id") long id);
}
