package com.example.moviego;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.model.Articles;
import com.example.moviego.model.DataNews;
import com.example.moviego.model.NewsResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentAct extends Fragment {
    ArrayList<DataNews.Articles> newsList = new ArrayList<>();


    public ContentAct() {
        // Required empty public constructor
    }

    public static ContentAct newIns() { return new ContentAct(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RetrofitCl.clearClient();


        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NewsAdapter newsAdapter = new NewsAdapter(requireContext(), newsList);
        RecyclerView recyclerView = view.findViewById(R.id.bebas);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(newsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        setHasOptionsMenu(true);

        API moviedetails = RetrofitCl.getRetrofit(Const.apinews).create(API.class);
        final Call<DataNews> cMovie = moviedetails.News("id", "entertainment", Const.apiken);
        cMovie.enqueue(new Callback<DataNews>() {
            @Override
            public void onResponse(Call<DataNews> call, Response<DataNews> response) {
                if (response.isSuccessful()) {
                    newsList.addAll(response.body().getArticles());
                    newsAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<DataNews> call, Throwable t) {
                Log.d("ROWITS", "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.rate, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menurate){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getContext(), LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
