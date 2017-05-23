package regulo.udacity.popularmovies;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import regulo.udacity.popularmovies.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class TopRatedMovieTest {

    public static final String GENRES = "Crime, Drama";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnGridViewItem(){
        onView(withId(R.id.viewpager)).perform(swipeLeft());

        onView(ViewMatchers.withId(R.id.recyclerview_movies_top))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        onView(withId(R.id.tv_genres_movie)).check(matches(withText(GENRES)));
    }
}
