package files.pic.app;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import files.pic.Client;

import files.pic.movie.Movie;
import files.pic.movie.MovieSeen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;


import java.io.FileWriter;
import java.net.URL;
import java.util.*;


public class Controller implements Initializable {

    @FXML
    public TextField searchBox;
    public Client client = new Client();
    public VBox movieCardLayout;
    private static FileWriter file;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JSONParser parser = new JSONParser();

        org.json.simple.JSONObject jsonFile;
        try {
            jsonFile = (org.json.simple.JSONObject) parser.parse(new FileReader("src/main/resources/files/pic/data.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JSONArray jsonArrayMovieToWatch = (JSONArray) jsonFile.get("MovieToWatch");
        JSONArray jsonArrayMovieSeen = (JSONArray) jsonFile.get("MovieSeen");

        if (!jsonArrayMovieToWatch.isEmpty()) {
            ArrayList<Movie> moviesToWatchDb = new ArrayList<>();
            for (int i = 0; i < jsonArrayMovieToWatch.size(); i++){
                String object = jsonArrayMovieToWatch.get(i).toString();
                JSONObject jsonObject = new JSONObject(object);
                moviesToWatchDb.add(new Movie(jsonObject));
            }
            client.setMoviesToWatch(moviesToWatchDb);
        }

        if (!jsonArrayMovieSeen.isEmpty()) {
            ArrayList<MovieSeen> moviesSeenDb = new ArrayList<>();
            for (int i = 0; i < jsonArrayMovieSeen.size(); i++){
                String object = jsonArrayMovieSeen.get(i).toString();
                JSONObject jsonObject = new JSONObject(object);
                moviesSeenDb.add(new MovieSeen(new Movie(jsonObject)));
            }
            client.setMoviesSeen(moviesSeenDb);
        }

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

    public void save() {
        org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();
        client.saveMovieSeenId();
        client.saveMovieToWatchId();
        jsonObject.put("MovieToWatch", client.getMovieToWatchId());
        jsonObject.put("MovieSeen", client.getMovieSeenId());

        try {
            FileWriter file = new FileWriter("src/main/resources/files/pic/data.json");
            file.write(jsonObject.toString());
            file.close();
            System.out.println(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}