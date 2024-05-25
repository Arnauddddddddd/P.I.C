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
import javafx.scene.paint.Color;

public class MovieCard {

    protected final String strAddMovieViewed = "Add to Viewed list";
    protected final String strRemoveMovieViewed = "Remove to Viewed list";
    protected final String strAddMovieToWatch = "Add to Watch list";
    protected final String strRemoveMovieToWatch = "Remove to Watch list";
    protected Movie movie;
    protected Client client;

    public ImageView moviePoster;
    public Label movieTitle;
    public Label movieResume;
    public HBox box;
    public Button addWatchList;
    public Button addListMoviesViewed;
    public Label movieRate;



    public void setData(Movie movie, Client client) {
        Image image = new Image(movie.getPoster());
        this.movie = movie;
        this.client = client;
        moviePoster.setImage(image);
        movieRate.setText(String.format("%.1f", movie.getVoteAverage()));
        movieTitle.setText(movie.getTitle());
        movieResume.setText(movie.getResume());
        if (!client.containsMovieSeen(movie)) {
            if (client.containsMovieToWatch(movie)) {
                this.addWatchList.setText(strRemoveMovieToWatch);
            } else {
                this.addWatchList.setText(strAddMovieToWatch);
            }
        }
        if (client.containsMovieSeen(movie)) {
            this.addListMoviesViewed.setText(strRemoveMovieViewed);
        } else {
            this.addListMoviesViewed.setText(strAddMovieViewed);
        }
        switch (Math.round(movie.getVoteAverage())) {
            case 0, 1, 2, 3, 4 -> movieRate.setTextFill(Color.RED);
            case 5, 6 -> movieRate.setTextFill(Color.ORANGE);
            case 7, 8, 9, 10 -> movieRate.setTextFill(Color.GREEN);
        }

    }

    @FXML
    protected void listViewtowatch() {
        if (movie.getClass() != MovieSeen.class) {
            if (!client.containsMovieToWatch(movie) && !client.containsMovieSeen(movie)) {
                client.addMovieToWatch(movie);
                this.addWatchList.setText(strRemoveMovieToWatch);
            } else {
                client.removeMovieToWatch(movie);
                this.addWatchList.setText(strAddMovieToWatch);
            }
        }
    }

    @FXML
    protected void listMoviesViewed() {
        if (!client.containsMovieSeen(movie)) {
            if (client.containsMovieToWatch(movie)) {
                client.removeMovieToWatch(movie);
                this.addWatchList.setText(strAddMovieToWatch);
            }
            this.addWatchList.setOpacity(0.35);
            client.addMovieSeen(movie);
            this.addListMoviesViewed.setText(strRemoveMovieViewed);
        } else {
            client.removeMovieSeen(movie);
            this.addListMoviesViewed.setText(strAddMovieViewed);
            this.addWatchList.setOpacity(1);
        }
    }
}
