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
        return movie;
    }

    public void jokingMovie(){
        switch(movie.getId()) {
            case 181808:
            this.note = 0.0; 
            break;  
            
            case 181812:
            this.note = 0.0;
            break;

            case 140607:
            this.note = 0.0;
            break;

            case 920:
            this.note = 10.0;
            break;

            case 49013:
            this.note = 10.0;
            break;

            case 260514:
            this.note = 10.0;
            break;

            case 387:
            this.note = 10.0;
            break;

            case 1895:
            this.note = 10.0;
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
