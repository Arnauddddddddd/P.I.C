package files.pic;

import okhttp3.*;
import org.json.*;


public class API {

    public JSONArray search(String str) {

        JSONArray jsonArray = new JSONArray();

        for (var i = 1; i < 4; i++) {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/search/movie?query=" + str + "&include_adult=true&language=en-US&page=" + i)
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4OWFlNDI1ZWZiNDRmNTQ0YjBhZmE3OGVjZTBmNWM2MiIsInN1YiI6IjY2MWJkMzVlZjVjODI0MDE4NzVlMzk5MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.pABJ9i7rnJ1Q-9xodhK36bHFYddohEjbHgrcz-l8h-w")
                    .build();


            try {
                Response response = client.newCall(request).execute();
                System.out.println(response.code());

                // On transforme notre réponse en Objet Json
                String result = response.body().string();

                JSONObject json = new JSONObject(result);


                // On renvoie la liste des films en JsonArray que contient notre object Json
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
        return jsonArray;
    }

    public JSONArray getPopularMovies() {
        OkHttpClient client = new OkHttpClient();
        JSONArray jsonArray = new JSONArray();

        for (var i = 1; i < 50; i++) {
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/movie/popular?language=en-US&page=" + i)
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
        return jsonArray;

    }
}