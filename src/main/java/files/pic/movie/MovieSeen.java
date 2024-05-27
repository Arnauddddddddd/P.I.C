package files.pic.movie;


import java.util.ArrayList;

public class MovieSeen extends Movie {

    private Double note;
    private String commentary;
    private Movie movie;


    public MovieSeen(Movie movie) {
        super(movie.getJsonObject());
        this.note = -1.0;
        this.commentary = "";
        this.movie = movie;
    }

    public void setNote(Double clientNote) {
        this.note = clientNote;
    }

    public void setCommentary(String str) {
        this.commentary = str;
    }

    public Double getNote() {
        return note;
    }

    public String getCommentary() {
        return commentary;
    }

    public Movie getMovie() {
        return movie;
    }
}
