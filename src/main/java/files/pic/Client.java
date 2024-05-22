package files.pic;


import java.util.ArrayList;
import java.util.Objects;

import files.pic.movie.Movie;
import files.pic.movie.MovieSeen;



public class Client {
    private final Search search = new Search();
    private ArrayList<MovieSeen> moviesSeen = new ArrayList<>();
    private ArrayList<Movie> moviesToWatch = new ArrayList<>();

    public void searchMovie(String movie) {
        getSearch().setActualMovies(getSearch().getApi().search(movie));
    }

    public void popularMovies() {
        getSearch().setActualMovies(getSearch().getApi().getPopularMovies());
    }

    public void bestMovies() {
        getSearch().setActualMovies(getSearch().getApi().getBestMovies());
    }

    public void upcomingMovies() {
        getSearch().setActualMovies(getSearch().getApi().getUpcomingMovies());
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

    public void search(String text) {
        searchMovie(text);
    }
}
