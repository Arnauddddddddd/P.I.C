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


import javafx.scene.input.KeyCode;
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
    public VBox movieCardLayout;
    public Label titleMenu;
    public Label pageNumber;
    public Hyperlink PreviousPage;
    public Hyperlink NextPage;
    public ScrollPane scrollBar;
    private Integer page = 0;
    private static FileWriter file;
    private final Client client = new Client();



    /*
       this function is called at startup, it loads the client's backup and adds its films to its list
     */
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

    /*  this function is called when main file detect that program is closing; It executes the save function  */
    public void shutdown() {
        save();
        Platform.exit();
    }

    /* this function is called every time the client
    do an action --> if he adds or removes movies for their arrays; if he changes the category of movies or sort them */
    public void updatePage(String strTitleMenu, int actualPage, Double scrollbarValue) {
        titleMenu.setText(strTitleMenu);
        page = actualPage;
        updateHUDPage();
        updateMovies(page);
        scrollBar.setVvalue(scrollbarValue);
    }


    /* this function updates movies in the window, for each movie, it detects if he was seen or not and generates theirs cards */
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

    /* this function updates HUD page like the current page, the title */
    public void updateHUDPage() {
        movieCardLayout.getChildren().clear();
        int currentPage = page + 1;
        pageNumber.setText(Integer.toString(currentPage) + " / " + (int) Math.ceil((double) client.getSearchedMovies().size() /15));
        NextPage.setDisable(!((page + 1) * 15 < client.getSearchedMovies().size()));
        PreviousPage.setDisable(!(page > 0));
    }

    /* this function was called if the window is closing and add the client's movies to the json file */
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


    /* this function is called if the client clicks on popular sort button and sorts the actual movies*/
    public void sortPopularMovies(ActionEvent actionEvent) {
        client.getSearch().sortMovies("popularity");
        client.getSearch().reverseMovies();
        updatePage(titleMenu.getText() + " : Most Popular", 0, 0.0);

    }

    /* this function is called if the client clicks on Best rated sort button and sorts the actual movies*/
    public void sortBestRatedMovies(ActionEvent actionEvent) {
        client.getSearch().sortMovies("vote_average");
        client.getSearch().reverseMovies();
        updatePage(titleMenu.getText() + " : Best Rated", 0, 0.0);
    }

    /* this function is called if the client clicks on More recent sort button and sorts the actual movies*/
    public void sortMoreRecentMovies(ActionEvent actionEvent) {
        client.getSearch().sortMovies("year");
        client.getSearch().reverseMovies();
        updatePage(titleMenu.getText() + " : More Recent", 0, 0.0);

    }

    /* this function is called if the client clicks on less recent sort button and sorts the actual movies*/
    public void sortLessMovies(ActionEvent actionEvent) {
        client.getSearch().sortMovies("year");
        updatePage(titleMenu.getText() + " : Less Recent", 0, 0.0);

    }

    /* The two functions below are called if the client push the button that corresponds to the change page */
    public void previousPage() {
        page--;
        updatePage(titleMenu.getText(), page, 0.0);
    }

    public void nextPage(ActionEvent actionEvent) {
        page++;
        updatePage(titleMenu.getText(), page, 0.0);
    }

    public Integer getPage() {
        return page;
    }

    /* the main file detect if client pressed keys and this function executes depending on the key pressed */
    public void keyUpdate(KeyCode key) {
        if (key == KeyCode.ENTER) {
            onSearchButtonClick();
        }
    }


    /* The six functions below are called if the customer pressed the button corresponding to the called function */
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

}