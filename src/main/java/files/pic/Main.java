package files.pic;

public class Main {

    private Client client;

    public Main() {
        this.client = new Client();
    }


    public void start() {
        this.getClient().searchMovie("The Game");
        this.getClient().getSearch().sortMovies("popularity");
        this.getClient().getSearch().drawResult("popularity");
        //System.out.println(search.getActualMovies().get(0).getType("poster_path"));;
    }

    public Client getClient() {
        return client;
    }



    
}


