package files.pic.movie;

import files.pic.Search;
import org.json.*;

import java.util.ArrayList;

public class Movie {

    private JSONObject jsonObject;
    private Integer id;
    private String title;
    private float popularity;
    private float voteAverage;
    private String poster;
    private int year;
    private String resume;
    private ArrayList<Movie> simularMovies;

    public Movie(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        id = Integer.parseInt(jsonObject.get("id").toString());
        title = jsonObject.get("title").toString();
        popularity = Float.parseFloat(jsonObject.get("popularity").toString());
        voteAverage = Float.parseFloat(jsonObject.get("vote_average").toString());
        resume = jsonObject.get("overview").toString();
        poster = "https://image.tmdb.org/t/p/w500" + jsonObject.get("poster_path").toString();
        simularMovies = new ArrayList<>();

        if (jsonObject.get("release_date").toString().length() == 10) {
            year = Integer.parseInt(jsonObject.get("release_date").toString().substring(0, 4));
        } else {
            year = 0;
        }
        
    }
    public String getType(String type) {
        if (type == "year") {
            return Integer.toString(year);
        }
        return jsonObject.get(type).toString();
    }

    public Integer getId() {
        return id;
    }

    public void setSimularMovies() {
        Search search = new Search();
        search.setActualMovies(new Search().getApi().searchSimularMovies(this.getId()));
        simularMovies = search.getActualMovies();
        search.drawResult("popularity");
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

    public String getResume() {
        return resume;
    }

    public int getYear() {
        return year;
    }

}