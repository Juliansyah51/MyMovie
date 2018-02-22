package com.room.whatevetv.mymovie.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.room.whatevetv.mymovie.activity.DetailsActivity;
import com.room.whatevetv.mymovie.activity.PostersActivity;
import com.room.whatevetv.mymovie.adapter.MovieAdapter;
import com.room.whatevetv.mymovie.entity.Movie;
import com.room.whatevetv.mymovie.main.MainPresenter;
import com.room.whatevetv.mymovie.main.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements MainView, MovieAdapter.OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerViewMovie;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.no_data_found) TextView textViewNotFound;

    private UpcomingFragment.FragmentInteractionListener mListener;
    private MainPresenter presenter;
    private MovieAdapter adapter;
    private List<Movie> movies = new ArrayList<>();

    public UpcomingFragment() {
    }

    public static UpcomingFragment newInstance() {
        UpcomingFragment fragment = new UpcomingFragment();
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

        presenter = new MainPresenter(this);
        presenter.findMovies(getString(R.string.api_key), "Coco");

        adapter = new MovieAdapter(movies, this);
        recyclerViewMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMovie.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UpcomingFragment.FragmentInteractionListener) {
            mListener = (UpcomingFragment.FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void onSearchMovie(String query){
        if (query != null){
            presenter.search(getString(R.string.api_key), query);
        }
    }

    public void onSearchReset(){
        presenter.findMovies(getString(R.string.api_key), "Coco");
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoadMovieFinish(List<Movie> results) {
        textViewNotFound.setVisibility(View.GONE);
        if (movies.size() != 0){
            movies.clear();
        }

        movies.addAll(results);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMovieFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadEmptyMovie() {
        movies.clear();
        adapter.notifyDataSetChanged();
        textViewNotFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMovieClick(View view, int position) {
        Movie param = movies.get(position);
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("movie", param);
        startActivity(intent);
    }

    @Override
    public void onImageClick(View view, int position) {
        Movie param = movies.get(position);
        Intent intent = new Intent(getContext(), PostersActivity.class);
        String image = param.getPosterPath().replace("\"", "");
        intent.putExtra("poster", image);
        startActivity(intent);
    }

    public interface FragmentInteractionListener {
        void onListFragmentInteraction(Movie item);
    }
}
