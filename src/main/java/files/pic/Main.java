package files.pic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private final Client client;

    public Main() {
        this.client = new Client();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
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


