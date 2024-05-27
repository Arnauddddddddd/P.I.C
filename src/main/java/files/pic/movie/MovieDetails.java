package files.pic.movie;

import files.pic.API;
import org.json.JSONObject;

public class MovieDetails {

    private final Movie movie;
    private final JSONObject detailsJson;
    private final Integer revenue;
    private final String release_date;
    private final String tagline;
    private final String homepage;
    private final String status;
    private final Integer runtime;

    public MovieDetails(Movie movie) {
        this.movie = movie;
        detailsJson = new API().findDetailsByID(movie.getId());
        revenue = Integer.parseInt(detailsJson.get("revenue").toString());
        release_date = detailsJson.get("release_date").toString();
        tagline = detailsJson.get("tagline").toString();
        homepage =  detailsJson.get("homepage").toString();
        status = detailsJson.get("status").toString();
        runtime = Integer.parseInt(detailsJson.get("runtime").toString());
    }

    public Movie getMovie() {
        return movie;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public JSONObject getDetailsJson() {
        return detailsJson;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }
}