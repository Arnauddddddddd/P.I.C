package files.pic;

import okhttp3.*;
import org.json.*;

import java.util.Objects;


public class API {

    JSONArray jsonArray = new JSONArray();
    OkHttpClient client = new OkHttpClient();

    public JSONArray search(String str) {
        jsonArray = new JSONArray();
        for (var i = 1; i < 8; i++) {
            useApi(i, "", str, null);
        }
        return jsonArray;
    }

    public JSONArray searchSimularMovies(Integer idMovie) {
        jsonArray = new JSONArray();
        for (var i = 1; i < 2; i++) {
            useApi(1, "simular","", idMovie);
        }
        return jsonArray;
    }

    public JSONArray getPopularMovies() {
        jsonArray = new JSONArray();
        for (var i = 1; i < 20; i++) {
            useApi(i, "popular", "", null);
        }
        return jsonArray;
    }

    public JSONArray getBestMovies() {
        jsonArray = new JSONArray();
        for (var i = 1; i < 20; i++) {
            useApi(i, "top_rated", "", null);
        }
        return jsonArray;
    }

    public JSONArray getUpcomingMovies() {
        jsonArray = new JSONArray();
        for (var i = 1; i < 3; i++) {
            useApi(i, "upcoming", "", null);
        }
        return jsonArray;
    }


    /* this method allows you to make a request to the api based on the parameters it contains */
    public void useApi(int page, String sort, String search, Integer idMovie) {
        String urlPage = "";
        if (!Objects.equals(search, "") && Objects.equals(sort, "")) {
            urlPage += "https://api.themoviedb.org/3/search/movie?query=" + search + "&include_adult=true&language=en-US&page=" + page;
        } else if (idMovie != null && Objects.equals(sort, "simular")) {
            urlPage += "https://api.themoviedb.org/3/movie/" + idMovie + "/similar?language=en-US&page=" + page;
        } else{
            urlPage += "https://api.themoviedb.org/3/movie/" + sort + "?language=en-US&page=" + page;
        }
        String result = useRequest(urlPage);
        JSONObject json = new JSONObject(result);
        JSONArray jsonArray2 = (JSONArray) json.get("results");
        if (!jsonArray2.isEmpty()) {
            for (int j = 0; j < jsonArray2.length(); j++) {
                jsonArray.put(jsonArray2.get(j));
            }
        }
    }

    public String useRequest(String url) {
        String result = "";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OWFlNDI1ZWZiNDRmNTQ0YjBhZmE3OGVjZTBmNWM2MiIsInN1YiI6IjY2MWJkMzVlZjVjODI0MDE4NzVlMzk5MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.pABJ9i7rnJ1Q-9xodhK36bHFYddohEjbHgrcz-l8h-w")
                .build();

        try {
            Response response = client.newCall(request).execute();
            result = response.body().string();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    /* this method allows you to retrieve additional information about a film using its ID */
    public JSONObject findDetailsByID(int idMovie) {
        String result = useRequest("https://api.themoviedb.org/3/movie/" + idMovie);
        JSONObject json = new JSONObject(result);
        return json;
    }
}