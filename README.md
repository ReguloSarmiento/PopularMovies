
# Popular Movies App

![Screen](https://raw.githubusercontent.com/ReguloSarmiento/PopularMovies/master/poster_popular_movies.png)
PopularMovies is an app that fetches movie data using [themoviedb.org](https://www.themoviedb.org/) API.

## Features:
* UI optimized for different screen sizes.
* Most popular and top rated movies.
* Mark as favorite any movie for easy access in the future.
* Watch movie trailers and read movie reviews.

## Used libraries:
* [RxJava](https://github.com/ReactiveX/RxAndroid) and [Retrofit](http://square.github.io/retrofit/) libraries to manage Rest Client
* [ButterKnife](http://jakewharton.github.io/butterknife/) library to bind views and avoid boilerplate views code
* [Picasso](http://square.github.io/picasso/) library to load images
* [Mockito](http://site.mockito.org/) library to make unit test and [Espresso](https://google.github.io/android-testing-support-library/docs/espresso/) library to make UI test.

## Design pattern
MVP (Model View Presenter) pattern to keep it simple and make the code testable, robust and easier to maintain

## Build from the source:
In order to build the app you must provide your own API key from themoviedb.org.
Open local.properties file and paste your key instead of ***YOUR_API_KEY*** text in this line:
```
dbmovieskey=“YOUR_API_KEY”
youtubekey=“YOUR_API_KEY”
```
