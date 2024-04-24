package files.pic;

public class Main {

    private Client client;
    private Search search;
    private TMDBClient tmdbClient;

    public Main() {
        this.search = new Search();
        this.client = new Client(search);
        this.tmdbClient = new TMDBClient();

    }


    public void start() {
        search.setActualMovies(tmdbClient.search("Star wars"));
        search.sortMovies("popularity");
        search.drawResult("popularity");
        //System.out.println(search.getActualMovies().get(0).getType("poster_path"));;
    }

    public Client getClient() {
        return client;
    }

    public Search getSearch() {
        return search;
    }

    public TMDBClient getTmdbClient() {
        return tmdbClient;
    }



    
}


