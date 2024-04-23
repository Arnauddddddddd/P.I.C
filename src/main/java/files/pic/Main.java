package files.pic;

public class Main {

    public void start() {
        TMDBClient tmdbClient = new TMDBClient();
        Search search = new Search();
        search.setActualMovies(tmdbClient.search("Star wars"));
        search.sortMovies("popularity");
        search.drawResult("popularity");
        //System.out.println(search.getActualMovies().get(0).getType("poster_path"));;
        Client client = new Client(search);
    }
}


