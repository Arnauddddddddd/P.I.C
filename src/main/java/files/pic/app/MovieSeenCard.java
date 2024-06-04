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
    public Label releaseDate;
    public ImageView clientstar1;
    public ImageView clientstar2;
    public ImageView clientstar3;
    public ImageView clientstar4;
    public ImageView clientstar5;

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
        setImages(clientstar1, clientstar2, clientstar3, clientstar4, clientstar5);
        double movieNoteDouble = movieSeen.getNote();
        float movieNotefloat = (float) movieNoteDouble;
        setRate(movieNotefloat, clientstar1, clientstar2, clientstar3, clientstar4, clientstar5, clientNote);
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
