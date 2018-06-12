package com.example.rifafauzi6.projectcataloguemovies1.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rifafauzi6.projectcataloguemovies1.MovieItems;
import com.example.rifafauzi6.projectcataloguemovies1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> lMovie = new ArrayList<>();
    private LayoutInflater lInflater;
    private Context context;
    private String fOverview;

    public MovieAdapter(Context context) {
        this.context = context;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items) {
        lMovie = items;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (lMovie == null)
            return 0;
        return lMovie.size();
    }

    @Override
    public MovieItems getItem(int position) {
        return lMovie.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = lInflater.inflate(R.layout.list_movie, null);
            holder.MovieName = convertView.findViewById(R.id.movie_name);
            holder.MovieDescription = convertView.findViewById(R.id.movie_description);
            holder.MovieDate = convertView.findViewById(R.id.movie_date);
            holder.MoviePoster = convertView.findViewById(R.id.movie_poster);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.MovieName.setText(lMovie.get(position).getMovieName());
        String retrievedDate = lMovie.get(position).getMovieDate();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(retrievedDate);
            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd/MM/yyyy");
            String date_of_release = new_date_format.format(date);
            holder.MovieDate.setText(date_of_release);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String overview = lMovie.get(position).getMovieDescription();
        if (TextUtils.isEmpty(overview)) {
            fOverview = "Null";
        } else {
            fOverview = overview;
        }
        holder.MovieDescription.setText(fOverview);
        Glide.with(context).load("http://image.tmdb.org/t/p/original/" + lMovie.get(position)
                .getmoviePoster()).into(holder.MoviePoster);
        return convertView;
    }

    private static class ViewHolder {
        TextView MovieName;
        TextView MovieDescription;
        TextView MovieDate;
        ImageView MoviePoster;
    }

}
