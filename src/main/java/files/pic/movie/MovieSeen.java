package files.pic.movie;


import java.util.ArrayList;

public class MovieSeen extends Movie {

    private Double note;
    private String commentary;

    /* we create a MovieSeen object from the movie, and we can give it the client's ratings */
    public MovieSeen(Movie movie) {
        super(movie.getJsonObject());
        this.note = -1.0;
        this.commentary = "";
    }

    public void setNote(Double clientNote) {

        this.note = clientNote;
        jokingMovie();
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
        return new Movie(this.getJsonObject());
    }

    public void jokingMovie(){
        switch(this.getId()) {
            case 181808, 140607, 181812:
            this.note = 0.0; 
            break;  

            case 49013, 260514, 387, 1895, 920:
            this.note = 5.0;
            break;

            case 60378:
            this.note = 39.45;
            break;

            case 12244:
            this.note = 9.999;
            break;
        }

    }
}
