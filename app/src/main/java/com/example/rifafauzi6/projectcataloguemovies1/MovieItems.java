package com.example.rifafauzi6.projectcataloguemovies1;

import org.json.JSONObject;

import java.io.Serializable;

public class MovieItems implements Serializable {
    private int id;
    private String movieName;
    private String movieDescription;
    private String moviePoster;
    private String movieDate;

    public MovieItems(JSONObject object) {
        try {
            int id = object.getInt("id");
            String moviePoster = object.getString("poster_path");
            String movieName = object.getString("title");
            String movieDescription = object.getString("overview");
            String movieDate = object.getString("release_date");

            this.id = id;
            this.moviePoster = moviePoster;
            this.movieName = movieName;
            this.movieDescription = movieDescription;
            this.movieDate = movieDate;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }


    public String getMovieDescription() {
        return movieDescription;
    }


    public String getmoviePoster() {
        return moviePoster;
    }

    public String getMovieDate() {
        return movieDate;
    }

}