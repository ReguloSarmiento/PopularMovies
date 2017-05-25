package regulo.udacity.popularmovies.models;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import regulo.udacity.popularmovies.restclient.RestUtils;
import regulo.udacity.popularmovies.utilities.SharedPrefsHelper;

public class Movie implements Parcelable{

    private int id;
    private String title;
    private String overview;
    private String release_date;
    private float popularity;
    private float vote_average;
    private String poster_path;
    private String backdrop_path;
    private int[] genre_ids;
    private String genres;

    public Movie(int id, String title, String overview, String release_date,
                 float popularity, float vote_average, String poster_path,
                 String backdrop_path, String genres) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.release_date = release_date;
        this.popularity = popularity;
        this.vote_average = vote_average;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }


    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return RestUtils.THUMBNAIL_URL + poster_path;
    }

    // Without size.
    public String getPoster() {return  poster_path;}

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return RestUtils.BACKDROP_URL + backdrop_path;
    }

    // Without size.
    public String getBackdrop() { return  backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    /**
     * Helper that provides a string with the names of genres
     * @param context {@link regulo.udacity.popularmovies.activities.MainActivity}
     * @return a {@link String}
     */
    public String getGenres(Context context){
        List<Genres.Genre> sharedPrefs = SharedPrefsHelper.getListOfGenres(context);
        String genres = "";

        if(this.getGenre_ids() != null && this.getGenre_ids().length > 0 ){
            for (int i = 0 ; i < sharedPrefs.size(); i++ ) {
                for (int j = 0 ; j < this.getGenre_ids().length; j++ ) {
                    if (getGenre_ids()[j] == sharedPrefs.get(i).getId()) {
                        genres += sharedPrefs.get(i).getName()+", ";
                    }
                }
            }
        }else{
            return this.genres;
        }

        return genres.length() > 2 ? genres.substring(0, genres.length() - 2): genres;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeFloat(popularity);
        dest.writeFloat(vote_average);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeIntArray(genre_ids);
        dest.writeString(genres);
    }

    private Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        popularity = in.readFloat();
        vote_average = in.readFloat();
        poster_path = in.readString();
        backdrop_path = in.readString();
        genre_ids = in.createIntArray();
        genres = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
