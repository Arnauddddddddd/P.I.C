package files.pic.app;

import files.pic.Client;
import files.pic.movie.Movie;
import files.pic.movie.MovieSeen;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void setData(Movie movie, Client client, Stage stage) {
        this.movie = movie;
        this.client = client;
        this.stage = stage;
        Image image = new Image(movie.getImage());
        titleMovie.setText(movie.getTitle());
        imageMovie.setImage(image);
    }


    @FXML
    protected void saveMovieSeenData() {
        try {
            client.getMovieSeenByMovie(movie).setNote(Double.parseDouble(clientMovieRate.getText()));
        } catch (Exception e) {
            client.getMovieSeenByMovie(movie).setNote(null);
        }
        client.getMovieSeenByMovie(movie).setCommentary(clientMovieCommentary.getText());
        System.out.println("Saving movie seen data");
        stage.close();
    }


    public Client getClient() {
        return client;
    }

    public Movie getMovie() {
        return movie;
    }
}
