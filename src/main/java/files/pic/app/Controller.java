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
        System.out.println("Initializing Controller");
    }


    @FXML
    protected void onSearchButtonClick() {
        client.search(searchBox.getText());
        client.getSearch().sortMovies("vote_average");
        client.getSearch().reverseMovies();
        updateMovies(0);
    }

    @FXML
    protected void soonMoviesButtonClick() {
        client.getSearch().setActualMoviesByMovieArray(client.getMoviesToWatch());
        updateMovies(0);

    }

    @FXML
    protected void popularMoviesButtonClick() {
        client.popularMovies();
        updateMovies(0);

    }


    @FXML
    protected void bestMoviesButtonClick() {
        client.bestMovies();
        updateMovies(0);
    }

    public void updateMovies(Integer page) {
        movieCardLayout.getChildren().clear();
        try {
            for (int i = page + page * 15; i < Math.min(client.getSearchedMovies().size(), page + page * 15 + 15); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                URL url2 = new File("src/main/resources/files/pic/movieCard.fxml").toURI().toURL();
                fxmlLoader.setLocation(url2);
                HBox carbox = fxmlLoader.load();
                MovieCard movieCard = fxmlLoader.getController();
                movieCard.setData(client.getSearchedMovies().get(i), client);
                movieCardLayout.getChildren().add(carbox);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}