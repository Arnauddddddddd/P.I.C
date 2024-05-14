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
import javafx.stage.Stage;

public class HelloController {
    public TextField searchBox;
    public Label helloLabel;
    public ListView<String> listView;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        Main main = new Main();
        //main.getClient().search(searchBox.getText());
        main.getClient().poplarMovies();
        //main.getClient().getSearch().sortMovies("year");
        main.getClient().getSearch().drawResult("popularity");
        System.out.println(searchBox.getText()); // Permet de voir ce que l'utilisateur a entré
        System.out.println(main.getClient().getSearch().getActualMovies().get(0).getPoster()); // Permet de voir l'url de l'image

        listView.getItems().clear();
        
        for (int i = 0; i < main.getClient().getSearch().getActualMovies().size(); i++) {
            listView.getItems().add(main.getClient().getSearch().getActualMovies().get(i).getTitle());
        }
    }
}