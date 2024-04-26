package files.pic;


import java.util.ArrayList;
import files.pic.movie.Movie;
import files.pic.movie.MovieSeen;



public class Client {
    private Search search;
    private ArrayList<MovieSeen> moviesSeen;
    private ArrayList<Movie> moviesToWatch;

    public Client() {
        search = new Search();
        moviesSeen = new ArrayList<>();
        moviesToWatch = new ArrayList<>();
    }

    public void searchMovie(String movie) {
        getSearch().setActualMovies(getSearch().getApi().search(movie));
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

    public void setSearch(Search search) {
        this.search = search;
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

    public void addMovieSeen(Movie movie) {
        MovieSeen moviesSeen = new MovieSeen(movie);
        this.moviesSeen.add(moviesSeen);
    }

    public void search(String text) {
        searchMovie(text);
    }
}
