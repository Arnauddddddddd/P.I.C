package files.pic.app;

import files.pic.Client;
import files.pic.movie.Movie;
import files.pic.movie.MovieDetails;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MovieDetailCard {

    private Movie movie;
    private Client client;
    private Stage stage;

    public ImageView poster;
    public Label movieTitle;
    public Label tagline;
    public Label releaseDate;
    public Label status;
    public Label runtime;
    public Label genders;
    public Label voteAverage;
    public Label resume;
    public Label revenus;


    public void setData(MovieDetails movieDetail, Client client, Stage stage) {
        this.movie = movieDetail.getMovie();
        this.client = client;
        this.stage = stage;
        Image image = new Image(movie.getPoster());
        poster.setImage(image);
        movieTitle.setText(movie.getTitle());
        tagline.setText(movieDetail.getTagline());
        releaseDate.setText(movieDetail.getRelease_date());
        status.setText(movieDetail.getStatus());
        runtime.setText(movie.getResume());
        revenus.setText((String.valueOf(movieDetail.getRevenue())));
        genders.setText("Comedy.....");
        voteAverage.setText(String.valueOf(movie.getVoteAverage()));
        resume.setText(movie.getResume());
    }
}
