package files.pic;

import org.json.*;

import files.pic.movie.*;
import java.util.ArrayList;

public class Search {
    private ArrayList<Movie> actualMovies;

    public Search() {
        this.actualMovies = new ArrayList<Movie>();
    }

    public void setActualMovies(JSONArray jsonList) {
        for (int i = 0; i < jsonList.length(); i++){
            actualMovies.add(new Movie(jsonList.getJSONObject(i)));
        }
        System.out.println(actualMovies);
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
            int minimum = i;
            for (int j = i + 1; j < actualMovies.size(); j++) {
                float fl1 = Float.parseFloat(actualMovies.get(j).getType(type).toString());
                float fl2 = Float.parseFloat(actualMovies.get(minimum).getType(type).toString());
                if (fl1 < fl2) {
                    minimum = j;
                }

                Movie temp = actualMovies.get(i);
                actualMovies.set(i, actualMovies.get(minimum));
                actualMovies.set(minimum, temp);
            }
        }
    }
}