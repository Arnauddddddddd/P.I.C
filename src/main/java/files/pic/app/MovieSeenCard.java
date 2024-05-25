package files.pic.app;

import files.pic.Client;
import files.pic.movie.Movie;
import files.pic.movie.MovieSeen;
import files.pic.Client;
import files.pic.movie.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MovieSeenCard extends MovieCard {
    public Label clientNote;
    public Label clientCommentary;
    public Button addListMoviesViewed;


    public void setData(MovieSeen movieSeen, Client client) {
        super.setData(movieSeen, client);
        addListMoviesViewed.setText(strRemoveMovieViewed);
        clientNote.setText(movieSeen.getNote().toString());
        clientCommentary.setText(movieSeen.getCommentary());
    }

    @FXML
    protected void removeMoviesViewed() {
        if (client.containsMovieSeen(movie)) {
            client.removeMovieSeen(movie);
            this.addListMoviesViewed.setText(strAddMovieToWatch);
        }
    }
}
