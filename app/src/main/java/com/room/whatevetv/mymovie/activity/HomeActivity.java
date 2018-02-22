package com.room.whatevetv.mymovie.activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.room.whatevetv.mymovie.R;
import com.room.whatevetv.mymovie.entity.Movie;
import com.room.whatevetv.mymovie.fragment.FavoriteFragment;
import com.room.whatevetv.mymovie.fragment.NowPlayingFragment;
import com.room.whatevetv.mymovie.fragment.UpcomingFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        UpcomingFragment.FragmentInteractionListener,
        SearchView.OnCloseListener,
        SearchView.OnQueryTextListener {

    private Fragment fragmentActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle(getString(R.string.now_playing));

        fragmentActive = NowPlayingFragment.newInstance();
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.fragment_container, fragmentActive);
        mFragmentTransaction.addToBackStack(null);
        mFragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setQueryHint(getResources().getString(R.string.search));
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_eng) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String title = "";
        if (id == R.id.nav_playing) {
            title = getString(R.string.now_playing);
            fragmentActive = NowPlayingFragment.newInstance();
        } else if (id == R.id.nav_upcoming) {
            title = getString(R.string.upcoming);
            fragmentActive = UpcomingFragment.newInstance();
        } else if (id == R.id.nav_favorite) {
            title = getString(R.string.favorite);
            fragmentActive = FavoriteFragment.newInstance();
        }

        if (fragmentActive != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragmentActive)
                    .commit();
        }

        getSupportActionBar().setTitle(title);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Movie item) {

    }

    @Override
    public boolean onClose() {
        if (fragmentActive instanceof UpcomingFragment) {
            ((UpcomingFragment) fragmentActive).onSearchReset();
        } else if (fragmentActive instanceof NowPlayingFragment) {
            ((NowPlayingFragment) fragmentActive).onSearchReset();
        }

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (fragmentActive instanceof UpcomingFragment) {
            ((UpcomingFragment) fragmentActive).onSearchMovie(query);
        } else if (fragmentActive instanceof NowPlayingFragment) {
            ((NowPlayingFragment) fragmentActive).onSearchMovie(query);
        }

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
