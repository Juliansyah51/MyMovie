package com.room.whatevetv.mymovie.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.room.whatevetv.mymovie.R;
import com.room.whatevetv.mymovie.entity.Favorite;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Samsung PC on 2/21/2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewholder>{

    private Cursor listFavorite;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public FavoriteAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public void setListFavorite(Cursor listFavorite) {
        this.listFavorite = listFavorite;
    }

    @Override
    public FavoriteViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new FavoriteViewholder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewholder holder, int position) {
        Favorite param = getItem(position);
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
        if (listFavorite == null) return 0;
        return listFavorite.getCount();
    }

    private Favorite getItem(int position){
        if (!listFavorite.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        }

        return new Favorite(listFavorite);
    }

    public interface OnItemClickListener {

        void onMovieClick(View view, int position);

        void onImageClick(View view, int position);
    }

    class FavoriteViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.itemImage)
        ImageView imageView;
        @BindView(R.id.itemTitle)
        TextView textViewTitle;
        @BindView(R.id.itemDesc)
        TextView textViewDesc;
        @BindView(R.id.itemDate)
        TextView textViewDate;
        @BindView(R.id.itemView)
        LinearLayout linearLayoutItem;

        FavoriteViewholder(View itemView) {
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
