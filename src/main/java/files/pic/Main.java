package files.pic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private final Client client;
    public TextField searchBox;
    public ListView listView;

    public Main() {
        this.client = new Client();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("film.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Private Int Cow");
        stage.setScene(scene);
        stage.show();
    }

    public Client getClient() {
        return client;
    }

    public static void main(String[] args) {
        launch();
    }

}


