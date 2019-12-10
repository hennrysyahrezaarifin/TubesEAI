package com.example.moviego;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCl {
private static Retrofit retrofit = null;
public  static Retrofit getClient(String url) {
    if (retrofit == null || !retrofit.baseUrl().equals(url)) {
        retrofit = new Retrofit.Builder()
                .client(okHttpClient())
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    return retrofit;
}

    public  static Retrofit getRetrofit(String url) {
        if (retrofit == null || !retrofit.baseUrl().equals(url)) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

private static OkHttpClient okHttpClient () {
   return new OkHttpClient.Builder().addInterceptor(new CustomInterceptor()).build();
}

    public static void clearClient() {
        retrofit = null;
    }

private static class CustomInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl y = request.url();
        HttpUrl z  = y.newBuilder().addQueryParameter("api_key", Const.apititle).build();
        Request.Builder x = request.newBuilder().url(z);
        Response response = chain.proceed(x.build());
        return response;
    }
}

}
