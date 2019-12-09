package com.example.moviego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class News extends AppCompatActivity {
    ImageView gambarberita;
    TextView judul, isiberita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        bindview();
        if(getIntent() != null) {
            Glide.with(this).load(getIntent().getStringExtra("thumb"))
                    .into(gambarberita);

            judul.setText(getIntent().getStringExtra("title"));
            isiberita.setText(getIntent().getStringExtra("content"));
        }
    }
    private void bindview() {
        gambarberita = findViewById(R.id.gambarberita);
        judul = findViewById(R.id.judul);
        isiberita = findViewById(R.id.isiberita);
    }
}
