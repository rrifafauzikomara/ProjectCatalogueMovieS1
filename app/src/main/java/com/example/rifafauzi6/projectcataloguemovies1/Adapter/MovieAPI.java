package com.example.rifafauzi6.projectcataloguemovies1.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.rifafauzi6.projectcataloguemovies1.MovieItems;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieAPI extends AsyncTaskLoader<ArrayList<MovieItems>> {

    private ArrayList<MovieItems> lMovie;
    private boolean result = false;
    private String movieCollection;

    public MovieAPI(final Context context, String movieCollection) {
        super(context);
        onContentChanged();
        this.movieCollection = movieCollection;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (result)
            deliverResult(lMovie);
    }

    @Override
    public void deliverResult(@Nullable final ArrayList<MovieItems> data) {
        lMovie = data;
        result = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (result) {
            onReleaseResource(lMovie);
            lMovie = null;
            result = false;
        }
    }

    private static final String API_KEY = "24d18ef569e3997bf2779d05440d3c6e";

    @Nullable
    @Override
    public ArrayList<MovieItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<MovieItems> movieItemses = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=" +
                API_KEY + "&language=en-US&query=" + movieCollection;


        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray results = responseObject.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject movie = results.getJSONObject(i);
                        MovieItems movieItems = new MovieItems(movie);
                        Log.d("LIST", "on success :" + movieItems.getmoviePoster());
                        Log.d("LIST", "on success :" + movieItems.getMovieDescription());
                        movieItemses.add(movieItems);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemses;
    }

    protected void onReleaseResource(ArrayList<MovieItems> data) {

    }
}