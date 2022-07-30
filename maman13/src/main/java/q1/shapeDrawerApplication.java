package q1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static q1.shapeDrawerController.initialize;
/*
 * A drawing application where you can create three types of shapes of different
 * colors and drag them into a painting pane.
 */
public class shapeDrawerApplication extends Application {
    /*
     * Main function in q1. Starts the application and initialize the scene
     * (using initialize())
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(shapeDrawerApplication.class.getResource("shapeDrawerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Shape Drawer Application");
        stage.setScene(scene);
        initialize(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}