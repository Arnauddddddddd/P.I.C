package files.pic;


import java.util.ArrayList;
import java.util.Objects;

import files.pic.movie.Movie;
import files.pic.movie.MovieSeen;
import org.json.JSONObject;


public class Client {
    private final Search search = new Search();
    private ArrayList<MovieSeen> moviesSeen = new ArrayList<>();
    private ArrayList<Movie> moviesToWatch = new ArrayList<>();

    private ArrayList<JSONObject> moviesSeenJSon = new ArrayList<JSONObject>();
    private ArrayList<JSONObject> moviesToWatchJSon = new ArrayList<JSONObject>();



    public void searchMovie(String movie) {
        getSearch().setActualMovies(getSearch().getApi().search(movie));
    }

    public void popularMovies() {
        getSearch().getPopularMovies();
    }

    public void bestMovies() {
        getSearch().getBestMovies();
    }

    public void upcomingMovies() {
        getSearch().getUpComingMovies();
    }

    public ArrayList<Movie> getSearchedMovies() {
        return getSearch().getActualMovies();
    }


    public Search getSearch() {
        return search;
    }

    public ArrayList<MovieSeen> getMoviesSeen() {
        return moviesSeen;
    }

    public MovieSeen getMovieSeenByMovie(Movie movie) {
        for (MovieSeen movieSeen : moviesSeen) {
            if (movieSeen.getId().equals(movie.getId())) {
                return movieSeen;
            }
        }
        return null;
    }

    public ArrayList<Movie> getMoviesSeenMovie() {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < getMoviesSeen().size(); i++) {
            movies.add(getMoviesSeen().get(i).getMovie());
        }
        return movies;
    }


    public ArrayList<Movie> getMoviesToWatch() {
        return moviesToWatch;
    }

    public void setMoviesSeen(ArrayList<MovieSeen> moviesSeen) {
        this.moviesSeen = moviesSeen;
    }

    public void setMoviesToWatch(ArrayList<Movie> moviesToWatch) {
        this.moviesToWatch = moviesToWatch;
    }

    public void addMovieToWatch(Movie movie) {
        this.moviesToWatch.add(movie);
    }

    public void removeMovieToWatch(Movie movie) {
        for (int i = 0; i < getMoviesToWatch().size(); i++) {
            if (Objects.equals(movie.getId(),  getMoviesToWatch().get(i).getId())) {
                this.moviesToWatch.remove(getMoviesToWatch().get(i));
            }
        }
    }

    public boolean containsMovieToWatch(Movie movie) {
        for (int i = 0; i < getMoviesToWatch().size(); i++) {
            if (Objects.equals(movie.getId(),  getMoviesToWatch().get(i).getId())) {
                return true;
            }
        }
        return false;
    }

    public void addMovieSeen(Movie movie) {
        MovieSeen moviesSeen = new MovieSeen(movie);
        this.moviesSeen.add(moviesSeen);
    }

    public void removeMovieSeen(Movie movie) {
        for (int i = 0; i < getMoviesSeen().size(); i++) {
            if (Objects.equals(movie.getId(), getMoviesSeen().get(i).getId())) {
                this.moviesSeen.remove(getMoviesSeen().get(i));
            }
        }
    }

    public boolean containsMovieSeen(Movie movie) {
        for (int i = 0; i < getMoviesSeen().size(); i++) {
            if (Objects.equals(movie.getId(), getMoviesSeen().get(i).getId())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Double> getClientRateMoviesSeen() {
        ArrayList<Double> array = new ArrayList<>();
        for ( MovieSeen movieSeen : getMoviesSeen()) {
            array.add(movieSeen.getNote());
        }
        return array;
    }

    public ArrayList<String> getClientCommentMoviesSeen() {
        ArrayList<String> array = new ArrayList<>();
        for ( MovieSeen movieSeen : getMoviesSeen()) {
            array.add(movieSeen.getCommentary());
        }
        return  array;
    }



    public ArrayList<JSONObject> getMovieSeenId() {
        return moviesSeenJSon;
    }

    public ArrayList<JSONObject> getMovieToWatchId() {
        return moviesToWatchJSon;
    }

    public void saveMovieToWatchId() {
        moviesToWatchJSon = new ArrayList<JSONObject>();
        for (int i = 0; i < getMoviesToWatch().size(); i++) {
            moviesToWatchJSon.add(getMoviesToWatch().get(i).getJsonObject());
        }
    }

    public void saveMovieSeenId() {
        moviesSeenJSon = new ArrayList<JSONObject>();
        for (int i = 0; i < getMoviesSeen().size(); i++) {
            moviesSeenJSon.add(getMoviesSeen().get(i).getJsonObject());
        }
    }

    public void search(String text) {
        searchMovie(text);
    }
}
