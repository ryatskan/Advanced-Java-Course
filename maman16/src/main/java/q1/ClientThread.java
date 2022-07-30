package q1;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Handle the connection between the Client and Server.
 */
public class ClientThread extends Thread {
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private ClientFXMLController controller;
    private final String name;

    public ClientThread(String host, int port, String name) throws IOException {
        Socket socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        this.name = name;
    }

    public void addFXMLController(ClientFXMLController controller) {
        this.controller = controller;
        controller.initialize(this);
    }

    /**
     * Listens for new messages from Server.
     */
    @Override
    public void run() {
        try {
            // send first message of the name of the client
            while (true) {
                Data input = (Data) in.readObject();
                if (input.getMsgType().equals(Data.MsgType.NewChatBoxMessage)) {
                    controller.addMsgToChatBox(input.getMsg(), input.getSender());

                } else if (input.getMsgType().equals(Data.MsgType.UpdatedClientList)) {
                    // the server sent a list of users to update the client
                    controller.updateClientsList(input.getClientList());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.exit(0);
        }
    }

    /**
     * Update the server that the Client want to leave the chatroom, and exits.
     */
    public void leaveChatRoom() {
        try {
            out.writeObject(new Data(name, Data.MsgType.RemoveClientFromServer));
        } catch (Exception e) {
        }
    }

    /**
     * Sends a message to the Server, regarding new chatroom msg.
     */
    public void sendMessage(String msg) {
        try {
            Data newMessage = new Data(name, msg, Data.MsgType.NewChatBoxMessage);
            out.writeObject(newMessage);
        } catch (IOException e) {

        }
    }

    /**
     * Asks the server to join the chatroom.
     */
    public void joinChatRoom() {
        try {
            out.writeObject(new Data(name, Data.MsgType.AddClientToServer));
        } catch (Exception e) {
        }
    }
    public String getClientName() {
        return name;
    }
}
