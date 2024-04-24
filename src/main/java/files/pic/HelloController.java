package files.pic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Main main = new Main();
        main.start();
        welcomeText.setText(main.getSearch().getActualMovies().get(0).getTitle());

    
    }
}