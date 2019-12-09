//package com.example.moviego;
//
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.moviego.model.MovieResponse;
//import com.example.moviego.model.Results;
//
//import java.util.ArrayList;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class film_details extends Fragment {
//    ArrayList<Results> movie = new ArrayList<>();
//
//    public film_details() {
//        // Required empty public constructor
//    }
//
//    public static film_details newIns() { return new film_details(); }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_film_details, container, false);
//
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        RetrofitCl.clearClient();
//
//        final MovieAdapter adapter = new MovieAdapter(requireContext(), movie);
//        RecyclerView recyclerView = view.findViewById(R.id.details);
//        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));
//        recyclerView.setAdapter(adapter);
//
//        API moviedetails = RetrofitCl.getClient(Const.apimovie).create(API.class);
//        final Call<MovieResponse> cMovie = moviedetails.Movie("1");
//        cMovie.enqueue(new Callback<MovieResponse>() {
//            @Override
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                Log.d("ROWITS", "onResponse: "+response.body());
//                if (response.isSuccessful()){
//                    movie.addAll(response.body().getResults());
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable t) {
//                Log.d("ROWITS", "onFailure: "+t.getLocalizedMessage());
//            }
//        });
//    }
//}
