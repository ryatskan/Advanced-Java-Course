package q2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
 * Main class in q2.
 */
public class DictionaryApplication extends Application {
    public static final int sceneWidth = 900;
    public static final int sceneHeight = 600;
    public static Stage currStage = null;
    /*
     * Main function in q2. Starts the dictionary application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        currStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(DictionaryApplication.class.getResource("Dictionary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), sceneWidth, sceneHeight);
        stage.setTitle("Dictionary Interface");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
