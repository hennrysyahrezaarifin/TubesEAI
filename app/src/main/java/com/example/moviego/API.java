package com.example.moviego;

import com.example.moviego.model.DataMovie;
import com.example.moviego.model.DataNews;
import com.example.moviego.model.MovieResponse;
import com.example.moviego.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("top-headlines")
    Call<DataNews> News (@Query("country") String _id,
                         @Query("category") String category,
                         @Query("apiKey") String apiKey
                     );

    @GET("movie/popular")
    Call<DataMovie> Movie (@Query("page") String page);

}
