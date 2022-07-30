package q1;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.InetAddress;
import java.util.Scanner;

/**
 * In charge of launching the two parts of the Client application -
 * the ClientThread in charge of receiving/sending messages to the Server and processing them.
 * the ClientFXMLController in charge of graphics and receiving input from user.
 */
public class ClientMain extends Application {
    final static int serverPort = 10;
    private ClientThread connection;

    /**
     * Starts the application, both the connection-wise and graphic-wise.
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        boolean succeeded = launchConnection();
        if (succeeded) {
            FXMLLoader fxmlLoader = launchGraphics(stage);
            connection.addFXMLController(fxmlLoader.getController());
            connection.start();
        } else {
            System.exit(1);
        }
    }

    /**
     * Receives a name from the user, and create a connection (ClientConnection instance) with the server.
     */
    boolean launchConnection() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please write your name:");
        String name = scan.next();
        System.out.println("Please write your ip:");
        String ip = scan.next();

        try {
            if (ip.length() ==1 ) {
                connection = new ClientThread(InetAddress.getLocalHost().getHostAddress(), serverPort, name);
            } else {
                connection = new ClientThread(ip, serverPort, name);
            }
            return true;

        } catch (Exception e) {
            System.out.println("Failed to connect to the server. Please try again");
            return false;
        }
    }

    /**
     * launch the JavaFX application as expected.
     */
    FXMLLoader launchGraphics(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMain.class.getResource("ChatBox.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 400);
        stage.setTitle("Chatroom");
        stage.setScene(scene);
        stage.show();
        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }
}
