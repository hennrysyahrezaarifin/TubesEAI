package com.example.moviego;

import com.example.moviego.model.DataMovie;
import com.example.moviego.model.DataNews;
import com.example.moviego.model.MovieResponse;
import com.example.moviego.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("everything")
    Call<DataNews> News (
                         @Query("apiKey") String apiKey,
                         @Query("q") String query
                     );

    @GET("movie/now_playing")
    Call<DataMovie> Movie (@Query("page") String page);

}
