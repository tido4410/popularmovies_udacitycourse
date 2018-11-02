package com.example.general.android.popularmoviesapp.ui.details;

import android.os.AsyncTask;

import com.example.general.android.popularmoviesapp.model.Review;
import com.example.general.android.popularmoviesapp.util.JSONParser;
import com.example.general.android.popularmoviesapp.util.NetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ReviewsApiQueryTask extends AsyncTask<URL, Void, ArrayList<Review>> {

    private UpdateRecyclerView contract;

    ReviewsApiQueryTask(UpdateRecyclerView acontract) {
        contract = acontract;
    }

    @Override
    protected ArrayList<Review> doInBackground(URL... params) {
        URL searchURL = params[0];
        String response = null;
        ArrayList<Review> reviewLst = null;

        try {
            response = NetworkUtils.getResponseFromHttpURL(searchURL);
            reviewLst = JSONParser.gettingReviewResultsValues(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reviewLst;
    }

    @Override
    protected void onPostExecute(ArrayList<Review> lst) {
        super.onPostExecute(lst);
        if (contract != null && lst != null) {
            contract.onUpdate(lst);
        }
    }

    interface UpdateRecyclerView {
        void onUpdate(ArrayList<Review> results);
    }
}