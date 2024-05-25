package files.pic;

import files.pic.app.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kotlin.reflect.KFunction;

import java.io.File;
import java.net.URL;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/resources/files/pic/main.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        Parent root = loader.load();
        Controller controller = loader.getController();

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("Private Int Cow");
        stage.setScene(scene);
        stage.setOnHidden(e -> controller.shutdown());
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}


