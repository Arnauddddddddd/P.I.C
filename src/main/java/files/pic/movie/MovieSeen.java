package files.pic.movie;


public class MovieSeen extends Movie {

    private Integer note;
    private String commentary;
    private Movie movie;


    public MovieSeen(Movie movie) {
        super(movie.getJsonObject());
        this.note = null;
        this.commentary = "";
        this.movie = movie;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Integer getNote() {
        return note;
    }

    public String getCommentary() {
        return commentary;
    }

    public Movie getMovie() {
        return movie;
    }
}
