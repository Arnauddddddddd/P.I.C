package files.pic.app;

import files.pic.Client;
import files.pic.movie.Movie;
import files.pic.movie.MovieSeen;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EditMovieSeenCard {

    @FXML
    public TextField clientMovieCommentary;
    public TextField clientMovieRate;
    public Label titleMovie;
    public ImageView imageMovie;

    protected Client client;
    protected Movie movie;
    private Stage stage;
    private Controller controller;

    public void setData(Movie movie, Client client, Stage stage, Controller controller) {
        this.movie = movie;
        this.client = client;
        this.controller = controller;
        this.stage = stage;
        clientMovieCommentary.setText(client.getMovieSeenByMovie(movie).getCommentary());
        clientMovieRate.setText(String.valueOf(client.getMovieSeenByMovie(movie).getNote()));
        if (client.getMovieSeenByMovie(movie).getNote() == -1) {
            clientMovieRate.setText("");
        }
        Image image = new Image(movie.getImage());
        titleMovie.setText(movie.getTitle());
        imageMovie.setImage(image);
    }

    /* Saves the data that the user has entered the movie that he has rated */
    @FXML
    protected void saveMovieSeenData() {
        try {
            Double note = Double.parseDouble(clientMovieRate.getText());
            if (note < 0) {
                note = 0.0;
            } else if (note > 5) {
                note = 5.0;
            }
            client.getMovieSeenByMovie(movie).setNote(note);
        } catch (Exception e) {
            client.getMovieSeenByMovie(movie).setNote(-1.0);
        }
        client.getMovieSeenByMovie(movie).setCommentary(clientMovieCommentary.getText());
        controller.updatePage(controller.titleMenu.getText(), controller.getPage(), controller.scrollBar.getVvalue());
        stage.close();
    }

    public Movie getMovie() {
        return movie;
    }
}
