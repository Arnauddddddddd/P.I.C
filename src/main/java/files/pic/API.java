package files.pic;

import okhttp3.*;
import org.json.*;

import java.util.Objects;


public class API {

    JSONArray jsonArray = new JSONArray();
    OkHttpClient client = new OkHttpClient();

    public JSONArray search(String str) {
        for (var i = 1; i < 4; i++) {
            useApi(i, "", str);
        }
        return jsonArray;
    }

    public JSONArray getPopularMovies() {
        for (var i = 1; i < 50; i++) {
            useApi(i, "popular", "");
        }
        return jsonArray;
    }

    public JSONArray getBestMovies() {
        for (var i = 1; i < 50; i++) {
            useApi(i, "top_rated", "");
        }
        return jsonArray;
    }

    public JSONArray getUpcomingMovies() {
        for (var i = 1; i < 50; i++) {
            useApi(i, "upcoming", "");
        }
        return jsonArray;
    }


    public void useApi(int page, String sort, String search) {
        String urlPage = "";
        if (!Objects.equals(search, "")) {
            urlPage += "https://api.themoviedb.org/3/search/movie?query=" + search + "&include_adult=true&language=en-US&page=" + page;
        } else{
            urlPage += "https://api.themoviedb.org/3/movie/" + sort + "?language=en-US&page=" + page;
        }

        Request request = new Request.Builder()
                .url(urlPage)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OWFlNDI1ZWZiNDRmNTQ0YjBhZmE3OGVjZTBmNWM2MiIsInN1YiI6IjY2MWJkMzVlZjVjODI0MDE4NzVlMzk5MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.pABJ9i7rnJ1Q-9xodhK36bHFYddohEjbHgrcz-l8h-w")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.code());
            String result = response.body().string();
            JSONObject json = new JSONObject(result);
            JSONArray jsonArray2 = (JSONArray) json.get("results");
            if (!jsonArray2.isEmpty()) {
                for (int j = 0; j < jsonArray2.length(); j++) {
                    jsonArray.put(jsonArray2.get(j));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}