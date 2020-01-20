package com.example.rifafauzi6.projectcataloguemovies1;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.rifafauzi6.projectcataloguemovies1.Adapter.MovieAPI;
import com.example.rifafauzi6.projectcataloguemovies1.Adapter.MovieAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>> {

    ListView listView;
    MovieAdapter adapter;
    EditText edtMovieName;
    Button btnSearch;
    static final String Movie = "EXTRAS_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        listView = findViewById(R.id.list_movie);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieItems movieItems = (MovieItems) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, MovieDetail.class);

                intent.putExtra(MovieDetail.Title, movieItems.getMovieName());
                intent.putExtra(MovieDetail.Desc, movieItems.getMovieDescription());
                intent.putExtra(MovieDetail.Poster, movieItems.getmoviePoster());
                intent.putExtra(MovieDetail.Date, movieItems.getMovieDate());

                startActivity(intent);
            }
        });

        edtMovieName = findViewById(R.id.edt_search_movie);
        btnSearch = findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(movieListener);

        String movie = edtMovieName.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(Movie, movie);

        getSupportLoaderManager().initLoader(0, bundle, this);

    }

    @NonNull
    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, @Nullable Bundle args) {
        String movieCollection = "";
        if (args != null) {
            movieCollection = args.getString(Movie);
        }
        return new MovieAPI(this, movieCollection);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

    View.OnClickListener movieListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String movie = edtMovieName.getText().toString();
            if (TextUtils.isEmpty(movie))
                return;

            Bundle bundle = new Bundle();
            bundle.putString(Movie, movie);
            getSupportLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };

}
