package q2;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static q2.FourInARowLogic.*;

/*
 * A Four in a Row interactive \game.
 */
public class FourInARowApplication extends Application {
    public static Scene currScene;

    /*
     * Main function in q2. Starts the application and create the two
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FourInARowApplication.class.getResource("FourInARow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 525);
        stage.setTitle("Four In A Row Application");
        stage.setScene(scene);
        // starts the game log
        initGame(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}