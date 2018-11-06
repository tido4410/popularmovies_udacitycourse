package com.example.general.android.popularmoviesapp.model.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.general.android.popularmoviesapp.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie WHERE is_favorite == 1")
    List<Movie> loadAllFavoriteMovies();

    @Query("SELECT * FROM movie WHERE is_favorite == 1 and id == :id")
    List<Movie> getSomeFavoriteMovieAccordingId(long id);

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);
}
