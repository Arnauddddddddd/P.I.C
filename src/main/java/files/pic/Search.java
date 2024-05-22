package files.pic;

import org.json.*;

import files.pic.movie.*;

import java.util.ArrayList;
import java.util.Collections;

public class Search {
    private ArrayList<Movie> actualMovies;
    private API api;

    public Search() {
        this.actualMovies = new ArrayList<Movie>();
        this.api = new API();
    }

    public API getApi() {
        return api;
    }


    public void setActualMovies(JSONArray jsonList) {
        actualMovies = new ArrayList<Movie>();
        for (int i = 0; i < jsonList.length(); i++){
            actualMovies.add(new Movie(jsonList.getJSONObject(i)));
        }
    }

    public void setActualMoviesByMovieArray(ArrayList<Movie> movieList) {
        actualMovies = movieList;

    }

    public ArrayList<Movie> getActualMovies() {
        return actualMovies;
    }

    public void drawResult(String type) {
        for (int i = actualMovies.size()-1; i >= 0; i--) {
            Movie movie = actualMovies.get(i);
            System.out.print(movie.getTitle());
            System.out.print("    ");
            System.out.print(movie.getType(type));
            System.out.print("\n");
        }
    }

    public void sortMovies(String type) {
        for (int i = 0; i < actualMovies.size() - 1; i++) {
            for (int j = i + 1; j < actualMovies.size(); j++) {
                float fl1 = Float.parseFloat(actualMovies.get(i).getType(type).toString());
                float fl2 = Float.parseFloat(actualMovies.get(j).getType(type).toString());
                if (fl1 > fl2) {
                    Movie temp = actualMovies.get(i);
                    actualMovies.set(i, actualMovies.get(j));
                    actualMovies.set(j, temp);
                }
            }
        }
    }

    public void reverseMovies() {
        Collections.reverse(actualMovies);
    }
}