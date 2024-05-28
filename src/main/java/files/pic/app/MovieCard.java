package files.pic.app;

import files.pic.Client;
import files.pic.movie.Movie;
import files.pic.movie.MovieDetails;
import files.pic.movie.MovieSeen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MovieCard {

    public Hyperlink addWatchList;
    public Hyperlink addListMoviesViewed;
    protected Controller controller;

    protected final String strAddMovieViewed = "Add to Viewed list";
    protected final String strRemoveMovieViewed = "Remove to Viewed list";
    protected final String strAddMovieToWatch = "Add to Watch list";
    protected final String strRemoveMovieToWatch = "Remove to Watch list";
    protected Movie movie;
    protected Client client;

    @FXML
    public ImageView moviePoster;
    public Label movieTitle;
    public HBox box;
    public Label movieRate;
    public Stage stage = new Stage();




    public void setData(Movie movie, Client client, Controller controller) {
        Image image = new Image(movie.getPoster());
        this.movie = movie;
        this.client = client;
        this.controller = controller;
        moviePoster.setImage(image);
        movieRate.setText(String.format("%.1f", movie.getVoteAverage()));
        movieTitle.setText(movie.getTitle());
        if (!client.containsMovieSeen(movie)) {
            if (client.containsMovieToWatch(movie)) {
                this.addWatchList.setText(strRemoveMovieToWatch);
            } else {
                this.addWatchList.setText(strAddMovieToWatch);
            }
        }
        if (client.containsMovieSeen(movie)) {
            this.addListMoviesViewed.setText(strRemoveMovieViewed);
        } else {
            this.addListMoviesViewed.setText(strAddMovieViewed);
        }
        switch (Math.round(movie.getVoteAverage())) {
            case 0, 1, 2, 3, 4 -> movieRate.setTextFill(Color.RED);
            case 5, 6 -> movieRate.setTextFill(Color.ORANGE);
            case 7, 8, 9, 10 -> movieRate.setTextFill(Color.GREEN);
        }
    }

    @FXML
    protected void listViewtowatch() {
        if (movie.getClass() != MovieSeen.class) {
            if (!client.containsMovieToWatch(movie) && !client.containsMovieSeen(movie)) {
                client.addMovieToWatch(movie);
                this.addWatchList.setText(strRemoveMovieToWatch);
            } else {
                client.removeMovieToWatch(movie);
                this.addWatchList.setText(strAddMovieToWatch);
            }
        }
    }

    @FXML
    protected void listMoviesViewed() {
        if (!client.containsMovieSeen(movie)) {
            if (client.containsMovieToWatch(movie)) {
                client.removeMovieToWatch(movie);
                this.addWatchList.setText(strAddMovieToWatch);
            }
            this.addWatchList.setOpacity(0.35);
            client.addMovieSeen(movie);
            this.addListMoviesViewed.setText(strRemoveMovieViewed);
            try {
                openEditMovieSeen(movie, client);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            client.removeMovieSeen(movie);
            this.addListMoviesViewed.setText(strAddMovieViewed);
            this.addWatchList.setOpacity(1);
        }
    }


    public void openEditMovieSeen(Movie movie, Client client) throws Exception {
        URL url = new File("src/main/resources/files/pic/editMovieSeenCard.fxml").toURI().toURL();
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(url);
        Parent root2 = loader2.load();
        EditMovieSeenCard editMovieSeenCard = loader2.getController();
        Scene scene = new Scene(root2, 873, 322);
        stage.setScene(scene);
        editMovieSeenCard.setData(movie, client, stage, controller);
        stage.show();
    }

    public void showDetails(ActionEvent actionEvent) throws Exception {
        URL url = new File("src/main/resources/files/pic/movieDetailCard.fxml").toURI().toURL();
        FXMLLoader loader3 = new FXMLLoader();
        loader3.setLocation(url);
        Parent root2 = loader3.load();
        MovieDetails movieDetails = new MovieDetails(movie);
        MovieDetailCard movieDetailCard = loader3.getController();
        Scene scene = new Scene(root2, 873, 500);
        stage.setScene(scene);
        movieDetailCard.setData(movieDetails, client, stage);
        stage.show();
    }
}
