package regulo.udacity.popularmovies.utilities;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import regulo.udacity.popularmovies.models.Genres;

public class SharedPrefsHelper {
    /*****************************************************************************************************
     * SharedPrefsHelper was created to store all the movies genres when the API is called the first time
     * so it won't be necessary to call it every time the list of genres is needed.
     *****************************************************************************************************/

    private final static String SHARED_PREF_FILE_NAME = SharedPrefsHelper.class.getPackage().toString();
    private static final String KEY = "my_genres";

    private static SharedPreferences getAppPrefs(final Context pContext) {
        return pContext.getSharedPreferences(SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(final SharedPreferences appPreferences) {
        return appPreferences.edit();
    }

    /**
     * Converts a list of movies genres to Json String and then saves it into a SharedPreference file.
     * @param context {@link regulo.udacity.popularmovies.activities.MainActivity}
     * @param genres List of genres.
     */
    public static void saveListOfGenres(Context context, final List<Genres.Genre> genres) {
        final  SharedPreferences.Editor editor = getEditor(getAppPrefs(context));
        Gson gson = new Gson();
        String json = gson.toJson(genres);
        editor.putString(KEY, json);
        editor.apply();
    }

    /**
     * Gets the list of movies genres from the SharedPreference file.
     * @param context {@link regulo.udacity.popularmovies.activities.MainActivity}
     * @return a {@link List} of {@link Genres.Genre}
     */
    public static List<Genres.Genre> getListOfGenres(Context context) {
        List<Genres.Genre> genres;
        Gson gson = new Gson();
        String json = getAppPrefs(context).getString(KEY, null);

        assert json != null;
        if (json.isEmpty()) {
            genres = new ArrayList<>();
        } else {
            Type type = new TypeToken<List<Genres.Genre>>(){}.getType();
            genres = gson.fromJson(json, type);
        }

        return genres;
    }
}
