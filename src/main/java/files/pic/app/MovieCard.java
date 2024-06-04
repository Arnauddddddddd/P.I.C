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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieCard {
    public Hyperlink addWatchList;
    public Hyperlink addListMoviesViewed;
    public ImageView star1;
    public ImageView star2;
    public ImageView star3;
    public ImageView star4;
    public ImageView star5;
    protected Controller controller;

    protected final String strAddMovieViewed = "Add to Viewed list";
    protected final String strRemoveMovieViewed = "Remove to Viewed list";
    protected final String strAddMovieToWatch = "Add to Watch list";
    protected final String strRemoveMovieToWatch = "Remove to Watch list";
    protected Movie movie;
    protected Client client;
    protected Image starVoid;
    protected Image starFiled;
    protected Image starFiled25p;
    protected Image starFiled50p;
    protected Image starFiled75p;



    @FXML
    public ImageView moviePoster;
    public Label movieTitle;
    public HBox box;
    public Label movieRate;
    public Stage stage = new Stage();




    public void setData(Movie movie, Client client, Controller controller) {
        this.movie = movie;
        this.client = client;
        this.controller = controller;
        setCard();
        setImages(star1, star2, star3, star4, star5);
        setRate(movie.getVoteAverage(), star1, star2, star3, star4, star5, movieRate);
    }

    public void setRate(float number, ImageView s1, ImageView s2, ImageView s3, ImageView s4, ImageView s5, Label rateTxt) {
        int arroundRate = Math.round(number);
        int arroundInferiorRate = (int) Math.floor(number);
        switch (arroundRate) {
            case 0, 1, 2 -> rateTxt.setTextFill(Color.RED);
            case 3 -> rateTxt.setTextFill(Color.ORANGE);
            case 4, 5 -> rateTxt.setTextFill(Color.GREEN);
        }
        if (arroundInferiorRate >= 1) {s1.setImage(starFiled);}
        if (arroundInferiorRate >= 2) {s2.setImage(starFiled);}
        if (arroundInferiorRate >= 3) {s3.setImage(starFiled);}
        if (arroundInferiorRate >= 4) {s4.setImage(starFiled);}
        if (arroundInferiorRate == 5) {s5.setImage(starFiled);}

        if (number - arroundInferiorRate >= 0.18 && number - arroundInferiorRate < 0.35) {setStarImage(starFiled25p, s1, s2, s3, s4, s5);}
        else if (number - arroundInferiorRate >= 0.35 && number - arroundInferiorRate < 0.65) {setStarImage(starFiled50p, s1, s2, s3, s4, s5);}
        else if (number - arroundInferiorRate >= 0.65 && number - arroundInferiorRate < 0.83) {setStarImage(starFiled75p, s1, s2, s3, s4, s5);}
        else if (number - arroundInferiorRate >= 0.83 && number - arroundInferiorRate < 1 ){setStarImage(starFiled, s1, s2, s3, s4, s5);}
        else {setStarImage(starVoid, s1, s2, s3, s4, s5);}
    }

    public void setCard() {
        Image poster = new Image(movie.getPoster());
        moviePoster.setImage(poster);
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
    }

    public void setImages(ImageView s1, ImageView s2, ImageView s3, ImageView s4, ImageView s5) {
        try {
            InputStream starFiledPath = new FileInputStream("src/main/resources/files/pic/pictures/star.png");
            InputStream starFiled25pPath = new FileInputStream("src/main/resources/files/pic/pictures/star0.25.png");
            InputStream starFiled50pPath = new FileInputStream("src/main/resources/files/pic/pictures/star0.5.png");
            InputStream starFiled75pPath = new FileInputStream("src/main/resources/files/pic/pictures/star0.75.png");
            InputStream starVoidPath = new FileInputStream("src/main/resources/files/pic/pictures/starVoid.png");
            starFiled = new Image(starFiledPath);
            starFiled25p = new Image(starFiled25pPath);
            starFiled50p = new Image(starFiled50pPath);
            starFiled75p = new Image(starFiled75pPath);
            starVoid = new Image(starVoidPath);
            s1.setImage(starVoid);
            s2.setImage(starVoid);
            s3.setImage(starVoid);
            s4.setImage(starVoid);
            s5.setImage(starVoid);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void setStarImage(Image image, ImageView s1, ImageView s2, ImageView s3, ImageView s4, ImageView s5) {
        if (s1.getImage().equals(starVoid)) {
            s1.setImage(image);}
        else if (s2.getImage().equals(starVoid)) {
            s2.setImage(image);}
        else if (s3.getImage().equals(starVoid)) {
            s3.setImage(image);}
        else if (s4.getImage().equals(starVoid)) {
            s4.setImage(image);}
        else if (s5.getImage().equals(starVoid)) {
            s5.setImage(image);}
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
            controller.updatePage(controller.titleMenu.getText(), controller.getPage(), controller.scrollBar.getVvalue());
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
        System.out.println(movie.getVoteAverage() - (int) Math.floor(movie.getVoteAverage()));
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
