package files.pic.movie;

import org.json.*;
public class Movie {

    private JSONObject jsonObject;
    private String title;
    private float popularity;
    private float voteAverage;
    private String poster;
    public Movie(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        title = jsonObject.get("title").toString();
        popularity = Float.parseFloat(jsonObject.get("popularity").toString());
        voteAverage = Float.parseFloat(jsonObject.get("vote_average").toString());
        poster = "https://image.tmdb.org/t/p/w500" + jsonObject.get("poster_path").toString();
    }
    public String getType(String type) {
        return jsonObject.get(type).toString();
    }

    public float getPopularity() {
        return popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }
}