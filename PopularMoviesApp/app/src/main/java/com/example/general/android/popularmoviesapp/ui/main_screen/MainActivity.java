package com.example.general.android.popularmoviesapp.ui.main_screen;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.general.android.popularmoviesapp.R;
import com.example.general.android.popularmoviesapp.databinding.ActivityMainDiscoveryScreenBinding;
import com.example.general.android.popularmoviesapp.model.Movie;

import java.util.ArrayList;

/**
 * Main screen
 */
public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView rcRecyclerView;
    protected Spinner spCriteria;
    protected SwipeRefreshLayout srlMovies;
    private MainViewModel viewModel;
    private static final int POPULAR_MOVIES = 0;
    private static final int TOP_RATED_MOVIES = 1;
    private static final int FAVORITE_MOVIES = 2;
    private int currentSpinnerPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainDiscoveryScreenBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main_discovery_screen);

        setContentView(R.layout.activity_main_discovery_screen);


        rcRecyclerView = findViewById(R.id.rvMovies);
        spCriteria = findViewById(R.id.spCriteria);
        srlMovies = findViewById(R.id.srlMovies);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        binding.setViewModel(viewModel);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcRecyclerView.setLayoutManager(gridLayoutManager);
        MovieItemAdapter adapter = new MovieItemAdapter(new ArrayList<Movie>());
        rcRecyclerView.setAdapter(adapter);
        viewModel.setAdapter(adapter);

        setupSwipeRefreshLayout();

        setupSpinnerListener();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void setupSwipeRefreshLayout() {
        srlMovies.setOnRefreshListener(this);
    }

    private void setupSpinnerListener() {
        spCriteria.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case POPULAR_MOVIES: {
                                viewModel.loadPopularMovies();
                                break;
                            }
                            case TOP_RATED_MOVIES: {
                                viewModel.loadTopRatedMovies();
                                break;
                            }
                            case FAVORITE_MOVIES: {
                                viewModel.loadFavoriteMovies();
                                break;
                            }
                            default: {
                                break;
                            }
                        }
                        currentSpinnerPosition = position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        currentSpinnerPosition = POPULAR_MOVIES;
                    }
                }
        );
    }

    @Override
    public void onRefresh() {
        switch (currentSpinnerPosition) {
            case FAVORITE_MOVIES:
                this.srlMovies.setRefreshing(false);
                viewModel.loadFavoriteMovies();
                break;
            case POPULAR_MOVIES:
                viewModel.loadPopularMovies();
                break;
            case TOP_RATED_MOVIES:
                viewModel.loadTopRatedMovies();
                break;
            default:
                break;
        }
    }
}
