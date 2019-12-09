package com.example.moviego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviego.model.DataMovie;
import com.example.moviego.model.Results;

public class Detail extends AppCompatActivity {
    private static final String TAG = "Detail";
    ImageView gambarmovie;
    TextView judulmovie, tahunmovie, durasimovie, deskripsimovie;
    Results movie;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bindview();

        if(getIntent() != null){
            Log.d(TAG, "onCreate: "+getIntent().getParcelableExtra("movie"));
            movie = getIntent().getParcelableExtra("movie");
            Glide.with(this).load("https://image.tmdb.org/t/p/w200"+getIntent().getStringExtra("gambar"))
                    .into(gambarmovie);

            Log.d(TAG, "onCreate: "+movie.getPosterPath());

            judulmovie.setText(movie.getTitle());
            tahunmovie.setText(movie.getReleaseDate());
            durasimovie.setText("");
            deskripsimovie.setText(movie.getOverview());

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.rate, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menurate){
            startActivity(new Intent(this,Commentactivity.class)
                    .putExtra("movie",movie)
                    .putExtra("gambar", getIntent().getStringExtra("gambar")));
        }
        return super.onOptionsItemSelected(item);
    }

    private void bindview() {
        gambarmovie = findViewById(R.id.gambarmovie);
        judulmovie = findViewById(R.id.judulmovie);
        tahunmovie = findViewById(R.id.tahunmovie);
        durasimovie = findViewById(R.id.durasimovie);
        deskripsimovie = findViewById(R.id.deskripsimovie);
    }
}
