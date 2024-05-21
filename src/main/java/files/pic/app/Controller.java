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
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextField searchBox;
    public Client client = new Client();
    public HBox movieCardLayout;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        client.bestMovies();
        List<Movie> movies = client.getSearch().getActualMovies();
        try {
            for (int i = 0; i < movies.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                URL url2 = new File("src/main/java/files/pic/movieCard.fxml").toURI().toURL();
                fxmlLoader.setLocation(url2);
                HBox carbox = fxmlLoader.load();
                MovieCard movieCard = fxmlLoader.getController();
                movieCard.setData(movies.get(i));
                movieCardLayout.getChildren().add(carbox);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void onSearchButtonClick() {

        //vBox.getChildren().clear();

        client.search(searchBox.getText());

        //main.getClient().bestMovies();
        //main.getClient().popularMovies();
        //main.getClient().upcomingMovies();

        client.getSearch().sortMovies("popularity");
        client.getSearch().reverseMovies();
        client.getSearch().drawResult("backdrop_path");
        System.out.println(searchBox.getText()); // Permet de voir ce que l'utilisateur a entré
        System.out.println(client.getSearch().getActualMovies().get(0).getPoster()); // Permet de voir l'url de l'image

        client.getSearch().getActualMovies().get(0).setSimularMovies();

    }
}