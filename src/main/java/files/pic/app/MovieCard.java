package files.pic.app;

import files.pic.Client;
import files.pic.movie.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MovieCard {

    public ImageView moviePoster;
    public Label movieTitle;
    public Label movieResume;
    public Label moviePopularity;
    public HBox box;
    public Button addWatchList;
    private Movie movie;
    private Client client;


    public void setData(Movie movie, Client client) {
        Image image = new Image(movie.getPoster());
        this.movie = movie;
        this.client = client;
        moviePoster.setImage(image);
        moviePopularity.setText(String.valueOf(movie.getVoteAverage()));
        movieTitle.setText(movie.getTitle());
        movieResume.setText(movie.getId().toString());
        if (client.containsMovieToWatch(movie)) {
            this.addWatchList.setText("Remove to watch list");
        } else {
            this.addWatchList.setText("Add to watch list");
        }
    }

    @FXML
    protected void listViewtowatch() {
        if (!client.containsMovieToWatch(movie)) {
            client.addMovieToWatch(movie);
            this.addWatchList.setText("Remove to watch list");
        } else {
            client.removeMovieToWatch(movie);
            this.addWatchList.setText("Add to watch list");
        }
    }


    @FXML
    protected void listMovieSeen() {
        if (!client.containsMovieToWatch(movie)) {
            client.addMovieToWatch(movie);
            this.addWatchList.setText("Remove to watch list");
        } else {
            client.removeMovieToWatch(movie);
            this.addWatchList.setText("Add to watch list");
        }
    }
}
