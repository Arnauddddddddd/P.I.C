package files.pic.app;

import files.pic.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloController {
    public TextField searchBox;
    public Label helloLabel;
    public VBox vBox;
    public Button button;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        Main main = new Main();


        vBox.getChildren().clear();

        main.getClient().search(searchBox.getText());

        //main.getClient().bestMovies();
        //main.getClient().popularMovies();
        //main.getClient().upcomingMovies();

        main.getClient().getSearch().sortMovies("popularity");
        main.getClient().getSearch().reverseMovies();
        main.getClient().getSearch().drawResult("backdrop_path");
        System.out.println(searchBox.getText()); // Permet de voir ce que l'utilisateur a entré
        System.out.println(main.getClient().getSearch().getActualMovies().get(0).getPoster()); // Permet de voir l'url de l'image

        main.getClient().getSearch().getActualMovies().get(0).setSimularMovies();


        for (int i = 0; i < 5; i++) {
            vBox.getChildren().add(new HBox(new Label(main.getClient().getSearch().getActualMovies().get(i).getTitle())));
            Image seats_image = new Image(main.getClient().getSearch().getActualMovies().get(i).getPoster());
            ImageView image = new ImageView(seats_image);
            image.setFitHeight(150);
            image.setFitWidth(100);
            vBox.getChildren().add(new HBox(image));
        }
    }
}