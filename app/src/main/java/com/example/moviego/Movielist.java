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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviego.model.MovieResponse;
import com.example.moviego.model.DataMovie;
import com.example.moviego.model.Results;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Movielist extends Fragment {
    ArrayList<Results> movie = new ArrayList<>();

    public Movielist() {
        // Required empty public constructor
    }

    public static Movielist newIns() { return new Movielist(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RetrofitCl.clearClient();

        final MovieAdapter adapter = new MovieAdapter(requireContext(), movie);
        RecyclerView recyclerView = view.findViewById(R.id.details);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));
        recyclerView.setAdapter(adapter);
        setHasOptionsMenu(true);
        API moviedetails = RetrofitCl.getClient(Const.apimovie).create(API.class);
        final Call<DataMovie> cMovie = moviedetails.Movie("1");
        cMovie.enqueue(new Callback<DataMovie>() {
            @Override
            public void onResponse(Call<DataMovie> call, Response<DataMovie> response) {
                Log.d("ROWITS", "onResponse: "+response.body());
                if (response.isSuccessful()){
                    movie.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                }
            }

            // Git add .

            @Override
            public void onFailure(Call<DataMovie> call, Throwable t) {
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
