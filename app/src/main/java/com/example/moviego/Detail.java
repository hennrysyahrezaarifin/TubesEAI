package com.example.moviego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviego.model.DataMovie;
import com.example.moviego.model.Results;
import com.orhanobut.hawk.Hawk;

public class Detail extends AppCompatActivity {
    private static final String TAG = "Detail";
    ImageView gambarmovie,star1,star2,star3,star4,star5;
    TextView judulmovie, tahunmovie, durasimovie, deskripsimovie,review;
    Results movie;
    Button send;
    EditText edit_review;
    int idMovie;
    int idStar=0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bindview();

        if(getIntent() != null){
            Log.d(TAG, "onCreate: "+getIntent().getParcelableExtra("movie"));
            movie = getIntent().getParcelableExtra("movie");
            idMovie = movie.getId();
            Glide.with(this).load("https://image.tmdb.org/t/p/w200"+getIntent().getStringExtra("gambar"))
                    .into(gambarmovie);

            Log.d(TAG, "onCreate: "+movie.getPosterPath());

            judulmovie.setText(movie.getTitle());
            tahunmovie.setText(movie.getReleaseDate());
            durasimovie.setText("");
            deskripsimovie.setText(movie.getOverview());

        }


        Hawk.init(Detail.this).build();
        if (Hawk.contains("review-"+idMovie)) {
            review.setText(Hawk.get("review-"+idMovie).toString());
            disableReview();
            idStar=Hawk.get("star-"+idMovie);
            if (idStar==5) {
                DrawableCompat.setTint(star1.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star2.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star3.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star4.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star5.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));

            }
            else if (idStar==4) {
                DrawableCompat.setTint(star1.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star2.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star3.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star4.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star5.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
            }
            else if (idStar==3) {
                DrawableCompat.setTint(star1.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star2.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star3.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star4.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star5.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));

            }
            else if (idStar==2) {
                DrawableCompat.setTint(star1.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star2.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star3.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star4.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star5.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));

            }
            else if (idStar==1) {
                DrawableCompat.setTint(star1.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star2.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star3.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star4.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star5.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));

            }
        }

        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idStar=5;
                DrawableCompat.setTint(star1.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star2.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star3.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star4.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star5.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
            }

        });
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idStar = 4;
                DrawableCompat.setTint(star1.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star2.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star3.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star4.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star5.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
            }

        });
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idStar=3;
                DrawableCompat.setTint(star1.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star2.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star3.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star4.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star5.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
            }

        });
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idStar=2;
                DrawableCompat.setTint(star1.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star2.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star3.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star4.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star5.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
            }

        });
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idStar=1;
                DrawableCompat.setTint(star1.getDrawable(), ContextCompat.getColor(Detail.this, R.color.gold));
                DrawableCompat.setTint(star2.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star3.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star4.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
                DrawableCompat.setTint(star5.getDrawable(), ContextCompat.getColor(Detail.this, R.color.grey));
            }

        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hasil = edit_review.getText().toString();
                review.setText(hasil);
                disableReview();
                Hawk.put("review-"+idMovie, hasil);
                Hawk.put("star-"+idMovie, idStar);
            }
        });
    }

    private void disableReview(){
        star1.setEnabled(false);
        star2.setEnabled(false);
        star3.setEnabled(false);
        star4.setEnabled(false);
        star5.setEnabled(false);

        edit_review.setVisibility(View.GONE);
        send.setVisibility(View.GONE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.rate, menu);
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
        star1 = findViewById(R.id.star1);
        star2 = findViewById(R.id.star2);
        star3 = findViewById(R.id.star3);
        star4 = findViewById(R.id.star4);
        star5 = findViewById(R.id.star5);
        send = findViewById(R.id.button_send);
        review = findViewById(R.id.hasil);
        edit_review = findViewById(R.id.edit_review);

    }
}
