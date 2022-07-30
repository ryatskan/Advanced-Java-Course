package q2;

/**
 * Create a client that sends messages to the Server and wait for the server to respond back to each msg.
 */
public class ClientController {
    ClientController() {
        ClientReceiveThread receiveThread = new ClientReceiveThread();
        ClientSendThread sendThread = new ClientSendThread();
        // send messages to the server
        sendThread.start();
        // wait for the server to send back the messages sendThread sent.
        receiveThread.start();
    }

}

