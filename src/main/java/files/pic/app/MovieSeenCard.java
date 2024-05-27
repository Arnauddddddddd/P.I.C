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

import java.util.Objects;

public class MovieSeenCard extends MovieCard {

    public Label clientNote;
    public Label clientCommentary;

    public void setData(MovieSeen movieSeen, Client client, Controller controller) {
        super.setData(movieSeen, client, controller);
        addListMoviesViewed.setText(strRemoveMovieViewed);
        if (movieSeen.getNote() == -1.0) {
            clientNote.setText("no rated");
        } else {
            clientNote.setText(movieSeen.getNote().toString());
        }
        if (Objects.equals(movieSeen.getCommentary(), "")) {
            clientCommentary.setText("no comment");
        } else {
            clientCommentary.setText(movieSeen.getCommentary());
        }
    }



    @FXML
    protected void removeMoviesViewed() {
        if (client.containsMovieSeen(movie)) {
            client.removeMovieSeen(movie);
            this.addListMoviesViewed.setText(strAddMovieToWatch);
        }
        controller.updatePage(controller.titleMenu.getText(), controller.getPage(), controller.scrollBar.getVvalue());
    }

    public void editMovieSeen(ActionEvent actionEvent) throws Exception {
        super.openEditMovieSeen(movie, client);
    }
}
