package com.room.whatevetv.mymovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.room.whatevetv.mymovie.R;
import com.room.whatevetv.mymovie.entity.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Samsung PC on 2/20/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> movies;
    private OnItemClickListener onItemClickListener;

    public MovieAdapter(List<Movie> movies, OnItemClickListener onItemClickListener) {
        this.movies = movies;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        Movie param = movies.get(position);
        holder.textViewTitle.setText(param.getTitle());
        holder.textViewDesc.setText(param.getOverview());
        holder.textViewDate.setText(param.getReleaseDate());
        if (param.getPosterPath() != null){
            String image = param.getPosterPath().replace("\"", "");
            Picasso.with(context).load("http://image.tmdb.org/t/p/w342/"+image).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface OnItemClickListener {

        void onMovieClick(View view, int position);

        void onImageClick(View view, int position);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.itemImage) ImageView imageView;
        @BindView(R.id.itemTitle) TextView textViewTitle;
        @BindView(R.id.itemDesc) TextView textViewDesc;
        @BindView(R.id.itemDate) TextView textViewDate;
        @BindView(R.id.itemView) LinearLayout linearLayoutItem;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            linearLayoutItem.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == linearLayoutItem.getId()){
                if (onItemClickListener != null){
                    onItemClickListener.onMovieClick(view, getAdapterPosition());
                }
            } else {
                if (onItemClickListener != null){
                    onItemClickListener.onImageClick(view, getAdapterPosition());
                }
            }
        }
    }
}
