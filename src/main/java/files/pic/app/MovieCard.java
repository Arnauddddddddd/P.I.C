package files.pic.app;

import files.pic.Client;
import files.pic.movie.Movie;
import files.pic.movie.MovieSeen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MovieCard {

    public ImageView moviePoster;
    public Label movieTitle;
    public Label movieResume;
    public HBox box;
    public Button addWatchList;
    public Button addListMoviesViewed;
    public Label movieRate;
    protected Movie movie;
    protected Client client;


    public void setData(Movie movie, Client client) {
        Image image = new Image(movie.getPoster());
        this.movie = movie;
        this.client = client;
        moviePoster.setImage(image);
        movieRate.setText(String.valueOf(movie.getVoteAverage()) + " / 10");
        movieTitle.setText(movie.getTitle());
        movieResume.setText(movie.getResume());
        if (client.containsMovieToWatch(movie) && movie.getClass() != MovieSeen.class) {
            this.addWatchList.setText("Remove to watch list");
        }
        if (client.containsMovieSeen(movie)) {
            this.addListMoviesViewed.setText("Remove to watch list");
        }

    }

    @FXML
    protected void listViewtowatch() {
        if (movie.getClass() != MovieSeen.class) {
            if (!client.containsMovieToWatch(movie) && !client.containsMovieSeen(movie)) {
                client.addMovieToWatch(movie);
                this.addWatchList.setText("Remove to watch list");
            } else {
                client.removeMovieToWatch(movie);
                this.addWatchList.setText("Add to watch list");
            }
        }
    }

    @FXML
    protected void listMoviesViewed() {
        if (!client.containsMovieSeen(movie)) {
            if (client.containsMovieToWatch(movie)) {
                client.removeMovieToWatch(movie);
                this.addWatchList.setText("Add to watch list");
            }
            this.addWatchList.setOpacity(0.35);
            client.addMovieSeen(movie);
            this.addListMoviesViewed.setText("Remove to Viewed list");
        } else {
            client.removeMovieSeen(movie);
            this.addListMoviesViewed.setText("Add to Viewed list");
            this.addWatchList.setOpacity(1);
        }
    }
}
