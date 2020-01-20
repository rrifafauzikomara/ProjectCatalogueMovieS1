package com.example.rifafauzi6.projectcataloguemovies1;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieDetail extends AppCompatActivity {

    TextView movieTitle, movieReleaseDate, movieDesc;
    ImageView image;

    public static String Title = "EXTRA_TITLE";
    public static String Desc = "EXTRA_DESC";
    public static String Date = "EXTRA_DATE";
    public static String Poster = "EXTRA_POSTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movieTitle = findViewById(R.id.movie_name);
        movieReleaseDate = findViewById(R.id.movie_date);
        movieDesc = findViewById(R.id.movie_description);
        image = findViewById(R.id.movie_poster);

        String title = getIntent().getStringExtra(Title);
        String desc = getIntent().getStringExtra(Desc);
        String poster = getIntent().getStringExtra(Poster);
        String release = getIntent().getStringExtra(Date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(release);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String date_release = newDateFormat.format(date);
            movieReleaseDate.setText(date_release);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        movieTitle.setText(title);
        movieDesc.setText(desc);
        movieReleaseDate.setText(release);

        Glide.with(MovieDetail.this).load("http://image.tmdb.org/t/p/original/" + poster)
                .override(350, 350).into(image);

        //membuat back button toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(MovieDetail.this, MainActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MovieDetail.this, MainActivity.class);
        startActivity(intent);
    }

}
