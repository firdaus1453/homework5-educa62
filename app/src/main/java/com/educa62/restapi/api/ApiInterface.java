package com.educa62.restapi.api;

import java.util.List;

import com.educa62.restapi.model.Users;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/users")
    Call<List<Users>> getListUser();

    @GET("/users")
    Call<List<Users>> getDetailUser(
            @Query("id") String id
    );

}



