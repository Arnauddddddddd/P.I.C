module files.pic {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires okhttp3;
    requires org.json;
    requires gson;
    requires json.simple;
    requires kotlin.stdlib;


    opens files.pic to javafx.fxml;
    exports files.pic;
    exports files.pic.app;
    opens files.pic.app to javafx.fxml;
}