package files.pic.app;

import files.pic.Client;

import files.pic.movie.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextField searchBox;
    public Client client = new Client();
    public VBox movieCardLayout;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client.upcomingMovies();
        updateMovies();
        movieCardLayout.getChildren().clear();

    }

    @FXML
    protected void onSearchButtonClick() {
        client.search(searchBox.getText());
        client.getSearch().sortMovies("popularity");
        client.getSearch().reverseMovies();
        movieCardLayout.getChildren().clear();
        updateMovies();
    }

    @FXML
    protected void soonMoviesButtonClick() {
        client.upcomingMovies();
        movieCardLayout.getChildren().clear();
        updateMovies();

    }

    @FXML
    protected void popularMoviesButtonClick() {
        client.popularMovies();
        movieCardLayout.getChildren().clear();
        updateMovies();

    }


    @FXML
    protected void bestMoviesButtonClick() {
        movieCardLayout.getChildren().clear();
        client.bestMovies();
        updateMovies();
    }

    public void updateMovies() {
        client.getSearch().drawResult("popularity");
        try {
            for (int i = 0; i < client.getSearchedMovies().size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                URL url2 = new File("src/main/java/files/pic/movieCard.fxml").toURI().toURL();
                fxmlLoader.setLocation(url2);
                HBox carbox = fxmlLoader.load();
                MovieCard movieCard = fxmlLoader.getController();
                movieCard.setData(client.getSearchedMovies().get(i));
                movieCardLayout.getChildren().add(carbox);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}