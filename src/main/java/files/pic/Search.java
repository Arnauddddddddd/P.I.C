package files.pic;

import org.json.*;

import files.pic.movie.*;

import java.util.ArrayList;
import java.util.Collections;

public class Search {
    private ArrayList<Movie> actualMovies;

    private ArrayList<Movie> popularMovies;
    private ArrayList<Movie> bestMovies;
    private ArrayList<Movie> upcomingMovies;

    private API api;

    public Search() {
        this.actualMovies = new ArrayList<Movie>();
        this.api = new API();
        this.popularMovies = new ArrayList<>();
        this.bestMovies = new ArrayList<>();
        this.upcomingMovies = new ArrayList<>();
        setBestMovies();
        setPopularMovies();
        setUpcomingMovies();

    }

    public ArrayList<Movie> getActualMovies() {
        return actualMovies;
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

    public void setPopularMovies() {
        JSONArray jsonList = getApi().getPopularMovies();
        for (int i = 0; i < jsonList.length(); i++){
            popularMovies.add(new Movie(jsonList.getJSONObject(i)));
        }
    }

    public void setBestMovies() {
        JSONArray jsonList = getApi().getBestMovies();
        for (int i = 0; i < jsonList.length(); i++){
            bestMovies.add(new Movie(jsonList.getJSONObject(i)));
        }
    }

    public void setUpcomingMovies() {
        JSONArray jsonList = getApi().getUpcomingMovies();
        for (int i = 0; i < jsonList.length(); i++){
            upcomingMovies.add(new Movie(jsonList.getJSONObject(i)));
        }
    }

    public void getPopularMovies() {
        actualMovies = popularMovies;
    }

    public void getBestMovies() {
        actualMovies = bestMovies;
    }

    public void getUpComingMovies() {
        actualMovies = upcomingMovies;
    }





    public void setActualMoviesByMovieArray(ArrayList<Movie> movieList) {
        actualMovies = movieList;

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