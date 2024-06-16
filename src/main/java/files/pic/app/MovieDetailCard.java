package files.pic.app;

import files.pic.Client;
import files.pic.movie.Movie;
import files.pic.movie.MovieDetails;
import javafx.event.ActionEvent;
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
        revenus.setText((String.valueOf(movieDetail.getRevenue())));
        genders.setText(movie.getGenreIds());
        voteAverage.setText(String.valueOf(movie.getVoteAverage()));
        resume.setText(movie.getResume());
        jokeYear();
    }

    /* This feature adds jokes based on the year of the movie */
    public void jokeYear() {
        switch (movie.getYear()) {
            case 2005:
                releaseDate.setText("Saboteur2005");
                break;
            case 1977:
                releaseDate.setText("Année Star-Wars");
                break;
            case 1979:
                releaseDate.setText("Attention au passager clandestin");
                break;
            case 2001:
                releaseDate.setText("Surveillez bien vos jumelles");
                break;
            case 1997:
                releaseDate.setText("Dylan");
                break;
            case 1999:
                releaseDate.setText("Des étrangers ???!!!");
                break;
            case 1993:
                releaseDate.setText("Je t'écraserai à mon retour");
                break;
            case 1998:
                releaseDate.setText("I will survive");
                break;
            case 2018:
                releaseDate.setText("Second poteau Pavard");
                break;
        }
    }

    public void closeWindow(ActionEvent actionEvent) {
        stage.close();
    }
}
