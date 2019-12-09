package com.example.moviego;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviego.model.DataNews;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    List<DataNews.Articles> newsdetails;

    public NewsAdapter (Context context, List<DataNews.Articles> newsdetails ) {
        this.context = context;
        this.newsdetails = newsdetails;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataNews.Articles newsI = newsdetails.get(position);
        Glide.with(context).load(newsI.getUrltoimage()).into(holder.imageView);
        holder.newsTitle.setText(newsI.getTitle());
        holder.newsDescription.setText(newsI.getDescription());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, News.class)
                .putExtra("thumb", newsI.getUrltoimage())
                .putExtra("title", newsI.getTitle())
                .putExtra("content", newsI.getContent()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsdetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView newsTitle, newsDescription;

        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageThumbnail);
            newsDescription = itemView.findViewById(R.id.news_description);
            newsTitle = itemView.findViewById(R.id.news_title);

            layout = itemView.findViewById(R.id.layout_comment);

        }
    }
}
