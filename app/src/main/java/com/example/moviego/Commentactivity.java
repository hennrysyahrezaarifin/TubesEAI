package com.example.moviego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviego.model.DataMovie;
import com.example.moviego.model.Results;

public class Commentactivity extends AppCompatActivity {
    ImageView imagemovie;
    TextView judulfilm, tahunfilm, durasifilm, rnr, rate, range;
    Results movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentactivity);
        bindview();
        if(getIntent() != null){
            movie = getIntent().getParcelableExtra("movie");

            Glide.with(this).load("https://image.tmdb.org/t/p/w200"+getIntent().getStringExtra("gambar"))
                    .into(imagemovie);

            judulfilm.setText(movie.getTitle());
            tahunfilm.setText(movie.getReleaseDate());
            durasifilm.setText("");
            rnr.setText(movie.getOverview());
            rate.setText(String.valueOf(movie.getVoteAverage()));
            range.setText("Out of 10");

        }

    }


    private void bindview() {
        imagemovie = findViewById(R.id.imagemovie);
        judulfilm = findViewById(R.id.judulfilm);
        tahunfilm = findViewById(R.id.tahunfilm);
        durasifilm = findViewById(R.id.durasifilm);
        rnr = findViewById(R.id.rnr);
        rate = findViewById(R.id.rate);
        range = findViewById(R.id.range);


    }
}
