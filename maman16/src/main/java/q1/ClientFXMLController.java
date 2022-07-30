package q1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * FXML Controller class.
 */
public class ClientFXMLController {
    @FXML
    private Text clientName;
    @FXML
    private TextField msgField;
    @FXML
    VBox participantsBox;
    @FXML
    private VBox chatBox;
    private ClientThread connection;

    /**
     * Adds the ClientThread in charge of connection with the server, and add the client name to the UI.
     */
    public void initialize(ClientThread connection) {
        this.connection = connection;
        clientName.setText("Client Name: " + connection.getClientName());
    }

    /**
     * Upon clicking the join button, enter the chatroom.
     */
    @FXML
    void joinAction() {
        connection.joinChatRoom();
    }

    /**
     * Upon clicking the leave button, leave the chatroom.
     */
    @FXML
    void leaveAction() {
        connection.leaveChatRoom();
    }

    /**
     * Upon clicking the send button, send the message in the TextField.
     */
    @FXML
    void sendAction() {
        connection.sendMessage(msgField.getText());
    }
    /**
     * Add a new message to the chatbox. Also mention the sender of the msg.
     *
     * @param msg    the msg content
     * @param sender the sender of the msg
     */
    public void addMsgToChatBox(String msg, String sender) {
        VBox thisChatBox = this.chatBox;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Text message = new Text(sender + ": " + msg);
                thisChatBox.getChildren().add(message);
            }
        });
    }

    /**
     * Graphically update participants list based on the given parameter.
     *
     * @param participants a String array of names of all participants
     */
    public void updateClientsList(String[] participants) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                participantsBox.getChildren().clear();
                for (int i = 0; i < participants.length; i++) {

                    participantsBox.getChildren().add(new Text(participants[i]));
                }
            }
        });

    }
}
