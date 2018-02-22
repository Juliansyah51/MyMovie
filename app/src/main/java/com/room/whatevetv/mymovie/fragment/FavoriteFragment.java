package com.room.whatevetv.mymovie.fragment;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.room.whatevetv.mymovie.R;
import com.room.whatevetv.mymovie.activity.FavoriteActivity;
import com.room.whatevetv.mymovie.activity.PostersActivity;
import com.room.whatevetv.mymovie.adapter.FavoriteAdapter;
import com.room.whatevetv.mymovie.entity.Favorite;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.room.whatevetv.mymovie.db.DatabaseMovie.CONTENT_URI;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteAdapter.OnItemClickListener{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerViewMovie;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.no_data_found) TextView textViewNotFound;

    private Cursor cursorList;
    private FavoriteAdapter adapter;

    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        ButterKnife.bind(this, view);

        adapter = new FavoriteAdapter(getContext(), this);
        adapter.setListFavorite(cursorList);

        recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMovie.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new LoadFavoriteAsync().execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private class LoadFavoriteAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getActivity().getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor Favorites) {
            super.onPostExecute(Favorites);
            progressBar.setVisibility(View.GONE);

            cursorList = Favorites;
            adapter.setListFavorite(cursorList);
            adapter.notifyDataSetChanged();
            if (cursorList.getCount() == 0){
                Toast.makeText(getContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMovieClick(View view, int position) {
        if (!cursorList.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        } else {
            Favorite Favorite = new Favorite(cursorList);
            Intent intent = new Intent(getContext(), FavoriteActivity.class);
            intent.putExtra("param", Favorite);
            startActivity(intent);
        }
    }

    @Override
    public void onImageClick(View view, int position) {
        if (!cursorList.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid");
        } else {
            Favorite Favorite = new Favorite(cursorList);
            Intent intent = new Intent(getContext(), PostersActivity.class);
            String image = Favorite.getPosterPath().replace("\"", "");
            intent.putExtra("poster", image);
            startActivity(intent);
        }
    }
}
