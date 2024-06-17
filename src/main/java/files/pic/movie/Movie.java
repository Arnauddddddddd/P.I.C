package files.pic.movie;

import files.pic.Search;
import org.json.*;

import java.util.ArrayList;

public class Movie {
    private final JSONObject jsonObject;
    private final Integer id;
    private final String title;
    private final float popularity;
    private final float voteAverage;
    private final String poster;
    private final String image;
    private final int year;
    private final String resume;
    private String genreIds;
    private String genreStr;
    private ArrayList<Movie> simularMovies;

    /* we create a Movie object with various characteristics from the retrieved json */
    public Movie(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        id = Integer.parseInt(jsonObject.get("id").toString());
        title = jsonObject.get("title").toString();
        popularity = Float.parseFloat(jsonObject.get("popularity").toString());
        voteAverage = Float.parseFloat(jsonObject.get("vote_average").toString()) / 2;
        resume = jsonObject.get("overview").toString();
        poster = "https://image.tmdb.org/t/p/w500" + jsonObject.get("poster_path").toString();
        image = "https://image.tmdb.org/t/p/w500" + jsonObject.get("backdrop_path").toString();
        genreIds = jsonObject.get("genre_ids").toString();
        genreStr = "";
        simularMovies = new ArrayList<>();
        try {
            setGenreIds();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public String getGenreStr() {
        return genreStr;
    }

    public String getImage() {
        return image;
    }

    public void setGenreIds() {
        ArrayList<Integer> genreIdsInt = new ArrayList<>();
        genreIds = genreIds.substring(1, genreIds.length()-1);
        if (genreIds.contains(",")) {
            String[] text = genreIds.split(",");
            String genre;
            for (int i = 0; i < text.length; i++) {
                int genreInt = Integer.parseInt(text[i]);
                switch (genreInt) {
                    case 28:
                        genre = "Action";
                        break;
                    case 12:
                        genre = "Adventure";
                        break;
                    case 16:
                        genre = "Animation";
                        break;
                    case 35:
                        genre = "Comedy";
                        break;
                    case 80:
                        genre = "Crime";
                        break;
                    case 99:
                        genre = "Documentary";
                        break;
                    case 18:
                        genre = "Drama";
                        break;
                    case 10751:
                        genre = "Family";
                        break;
                    case 14:
                        genre = "Fantasy";
                        break;
                    case 36:
                        genre = "History";
                        break;
                    case 27:
                        genre = "Horror";
                        break;
                    case 10402:
                        genre = "Music";
                        break;
                    case 9648:
                        genre = "Mystery";
                        break;
                    case 10749:
                        genre = "Romance";
                        break;
                    case 878:
                        genre = "Science Fiction";
                        break;
                    case 10770:
                        genre = "TV Movie";
                        break;
                    case 53:
                        genre = "Thriller";
                        break;
                    case 10752:
                        genre = "War";
                        break;
                    case 37:
                        genre = "Western";
                        break;
                    default:
                        genre = "Unknown";
                        break;
                }
                genreStr += genre + " ";
            }
        } else {
            genreStr += genreIds;
        }
    }
}