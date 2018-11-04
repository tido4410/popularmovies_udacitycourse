package com.example.general.android.popularmoviesapp.ui.details;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.example.general.android.popularmoviesapp.model.Movie;
import com.example.general.android.popularmoviesapp.model.Review;
import com.example.general.android.popularmoviesapp.model.VideoTrailer;
import com.example.general.android.popularmoviesapp.util.MathService;

import java.util.ArrayList;

public class DetailsViewModel extends AndroidViewModel {

    private MutableLiveData<Movie> movie = new MutableLiveData<>();
    private MutableLiveData<ArrayList<VideoTrailer>> trailerLst = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Review>> reviewsLst = new MutableLiveData<>();

    public DetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public void setMovie(Movie movieTarget) {
        movie.setValue(movieTarget);
    }

    public void setReviews(ArrayList<Review> reviews) {
        reviewsLst.setValue(reviews);
    }

    public void setTrailerLst(ArrayList<VideoTrailer> trailers) {
        this.trailerLst.setValue(trailers);
    }

    public MutableLiveData<Movie> getMovie() {
        return movie;
    }

    public MutableLiveData<ArrayList<VideoTrailer>> getTrailerLst() {
        return trailerLst;
    }

    public MutableLiveData<ArrayList<Review>> getReviewsLst() {
        return reviewsLst;
    }

    public String getMovieTitle() {
        return movie.getValue().getTitle();
    }

    public String getMovieReleaseDate() {
        return MathService.getYearFromDate(movie.getValue().getReleaseDate());
    }

    public String getMovieUserRating() {
        return movie.getValue().getVoteAverage() + "/10";
    }

    public String getMovieOverview() {
        return movie.getValue().getOverview();
    }

}