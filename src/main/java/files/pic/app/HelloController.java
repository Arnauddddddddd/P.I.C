package files.pic.app;

import files.pic.Main;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloController {
    public TextField searchBox;
    public Label helloLabel;
    public VBox listView;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        Main main = new Main();
        main.getClient().search(searchBox.getText());
        //main.getClient().popularMovies();
        //main.getClient().bestMovies();
        //main.getClient().getSearch().sortMovies("year");
        main.getClient().getSearch().drawResult("popularity");
        System.out.println(searchBox.getText()); // Permet de voir ce que l'utilisateur a entré
        System.out.println(main.getClient().getSearch().getActualMovies().get(0).getPoster()); // Permet de voir l'url de l'image

        listView.getInsets();

        for (int i = 0; i < 5; i++) {
            listView.getChildren().add(new HBox(new Label(main.getClient().getSearch().getActualMovies().get(i).getTitle())));
            Image seats_image = new Image(main.getClient().getSearch().getActualMovies().get(i).getPoster());
            ImageView image = new ImageView(seats_image);
            image.setFitHeight(150);
            image.setFitWidth(100);
            listView.getChildren().add(new HBox(image));
        }
    }
}