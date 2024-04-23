package files.pic.movie;


public class MovieSeen extends Movie {

    private Integer note;
    private String commentary;


    public MovieSeen(Movie movie) {
        super(movie.getJsonObject());
        this.note = null;
        this.commentary = "";
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
}
