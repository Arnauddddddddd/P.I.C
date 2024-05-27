package files.pic.app;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import files.pic.Client;

import files.pic.Main;
import files.pic.movie.Movie;
import files.pic.movie.MovieSeen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    public Label titleMenu;
    public Label pageNumber;
    public Hyperlink PreviousPage;
    public Hyperlink NextPage;
    public ScrollPane scrollBar;
    private Integer page = 0;


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
        JSONArray jsonArrayMovieSeenRate = (JSONArray) jsonFile.get("MovieSeenRate");
        JSONArray jsonArrayMovieSeenComment = (JSONArray) jsonFile.get("MovieSeenComment");

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
                MovieSeen movieSeen = new MovieSeen(new Movie(jsonObject));
                movieSeen.setNote((Double) jsonArrayMovieSeenRate.get(i));
                movieSeen.setCommentary((String) jsonArrayMovieSeenComment.get(i));
                moviesSeenDb.add(movieSeen);
            }
            client.setMoviesSeen(moviesSeenDb);
        }
        popularMoviesButtonClick();

    }

    public void shutdown() {
        System.out.println("Stop");
        save();
        Platform.exit();
    }


    public void updatePage(String strTitleMenu, int actualPage, Double scrollbarValue) {
        titleMenu.setText(strTitleMenu);
        page = actualPage;
        updateHUDPage();
        updateMovies(page);
        scrollBar.setVvalue(scrollbarValue);
    }

    public void updateMovies(Integer page) {
        try {
            for (int i = page * 15; i < Math.min(client.getSearchedMovies().size(), page * 15 + 15); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                FXMLLoader fxmlLoader2 = new FXMLLoader();
                if (client.containsMovieSeen(client.getSearchedMovies().get(i))) {
                    URL url2 = new File("src/main/resources/files/pic/movieSeenCard.fxml").toURI().toURL();
                    fxmlLoader.setLocation(url2);
                    HBox carbox = fxmlLoader.load();
                    MovieSeenCard movieSeenCard = fxmlLoader.getController();
                    movieSeenCard.setData(client.getMovieSeenByMovie(client.getSearchedMovies().get(i)), client, this);
                    movieCardLayout.getChildren().add(carbox);
                } else {
                    URL url2 = new File("src/main/resources/files/pic/movieCard.fxml").toURI().toURL();
                    fxmlLoader2.setLocation(url2);
                    HBox carbox = fxmlLoader2.load();
                    MovieCard movieCard = fxmlLoader2.getController();
                    movieCard.setData(client.getSearchedMovies().get(i), client, this);
                    movieCardLayout.getChildren().add(carbox);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateHUDPage() {
        movieCardLayout.getChildren().clear();
        int currentPage = page + 1;
        pageNumber.setText(Integer.toString(currentPage) + " / " + (int) Math.ceil((double) client.getSearchedMovies().size() /15));
        NextPage.setDisable(!((page + 1) * 15 < client.getSearchedMovies().size()));
        PreviousPage.setDisable(!(page > 0));

    }


    public void save() {
        org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();
        client.saveMovieSeenId();
        client.saveMovieToWatchId();
        jsonObject.put("MovieToWatch", client.getMovieToWatchId());
        jsonObject.put("MovieSeen", client.getMovieSeenId());
        jsonObject.put("MovieSeenRate", client.getClientRateMoviesSeen());
        jsonObject.put("MovieSeenComment", client.getClientCommentMoviesSeen());


        try {
            FileWriter file = new FileWriter("src/main/resources/files/pic/data.json");
            file.write(jsonObject.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void sortPopularMovies(ActionEvent actionEvent) {
        client.getSearch().sortMovies("popularity");
        client.getSearch().reverseMovies();
        updatePage(titleMenu.getText() + " : Most Popular", 0, 0.0);

    }

    public void sortBestRatedMovies(ActionEvent actionEvent) {
        client.getSearch().sortMovies("vote_average");
        client.getSearch().reverseMovies();
        updatePage(titleMenu.getText() + " : Best Rated", 0, 0.0);


    }

    public void sortMoreRecentMovies(ActionEvent actionEvent) {
        client.getSearch().sortMovies("year");
        client.getSearch().reverseMovies();
        updatePage(titleMenu.getText() + " : More Recent", 0, 0.0);

    }

    public void sortLessMovies(ActionEvent actionEvent) {
        client.getSearch().sortMovies("year");
        updatePage(titleMenu.getText() + " : Less Recent", 0, 0.0);

    }



    public void previousPage() {
        page--;
        updatePage(titleMenu.getText(), page, 0.0);

    }

    public void nextPage(ActionEvent actionEvent) {
        page++;
        updatePage(titleMenu.getText(), page, 0.0);
    }



    @FXML
    protected void onSearchButtonClick() {
        if (!searchBox.getText().isEmpty()) {
            client.search(searchBox.getText());
            client.getSearch().sortMovies("vote_average");
            client.getSearch().reverseMovies();
            updatePage("Search : " + searchBox.getText(), 0, 0.0);
        }
    }

    @FXML
    protected void movieToWatchButtonClick() {
        client.getSearch().setActualMoviesByMovieArray(client.getMoviesToWatch());
        updatePage("Movies in your Watchlist !", 0, 0.0);
    }

    @FXML
    public void seeMoviesButtonClick(ActionEvent actionEvent) {
        client.getSearch().setActualMoviesByMovieArray(client.getMoviesSeenMovie());
        updatePage("Visioned Movies", 0, 0.0);
    }

    @FXML
    protected void upComingButtonClick(ActionEvent actionEvent) {
        client.upcomingMovies();
        updatePage("Soon in Cinema !", 0, 0.0);
    }

    @FXML
    protected void popularMoviesButtonClick() {
        client.popularMovies();
        updatePage("Most popular Movies of the moment !", 0, 0.0);
    }


    @FXML
    protected void bestMoviesButtonClick() {
        client.bestMovies();
        updatePage("Best appreciated Movies of the moment !", 0, 0.0);
    }

    public Integer getPage() {
        return page;
    }

}