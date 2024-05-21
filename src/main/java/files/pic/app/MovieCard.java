package files.pic.app;

import files.pic.movie.Movie;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MovieCard {


    public ImageView moviePoster;
    public Label movieTitle;
    public Label movieResume;
    public Label moviePopularity;
    public HBox box;

    public void setData(Movie movie) {
        Image image = new Image(movie.getPoster());
        moviePoster.setImage(image);
        moviePopularity.setText(String.valueOf(movie.getPopularity()));
        movieTitle.setText(movie.getTitle());
        movieResume.setText(movie.getType("overview"));
    }


}
