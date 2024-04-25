package files.pic.app;

import files.pic.Main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;

public class HelloController {
    public TextField searchBox;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        ImageView myImageView;

        Main main = new Main();
        //main.start();
        //welcomeText.setText(main.getClient().getSearch().getActualMovies().get(0).getTitle());
        searchBox.getText();
        main.getClient().searchMovie(searchBox.getText());
        main.getClient().getSearch().drawResult("popularity");
        System.out.println(searchBox.getText()); // Permet de voir ce que l'utilisateur a entré
        //main.getClient().getSearch().getActualMovies();
        Image image = new Image(main.getClient().getSearch().getActualMovies().get(0).getPoster());
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        System.out.println(main.getClient().getSearch().getActualMovies().get(0).getPoster());

        // Create the root of the scene graph.
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        // Create the scene and set it in the stage.
        Scene scene = new Scene(root, 800, 600); // Adjust width and height as needed.
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);

        // Set the title of the window.
        primaryStage.setTitle("Image Display App");

        // Show the window.
        primaryStage.show();

    }
}