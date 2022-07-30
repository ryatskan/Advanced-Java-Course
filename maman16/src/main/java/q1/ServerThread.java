package q1;

import java.io.*;
import java.net.Socket;

/**
 * A Thread in charge of a single connection between the server and a specific client.
 */
public class ServerThread extends Thread {
    private final Server server;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final Socket socket;
    private String clientName = "unknown";

    /**
     * The Constructor.
     *
     * @param socket the socket of the connection this ServerThread is in charge of.
     * @param server the main server instance.
     */
    public ServerThread(Socket socket, Server server) throws IOException {
        this.server = server;
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }


    @Override
    public void run() {
        try {
            joinClient();
            while (true) {
                Data n;
                // get data from client
                n = (Data) in.readObject();
                System.out.println("Server received a message from " + clientName + ". Message type: " + n.getMsgType().toString());
                server.sendToAll(n);

                if (n.getMsgType().equals(Data.MsgType.RemoveClientFromServer)) {
                    socket.close();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error in the connection with " + clientName);

        } finally {
            System.out.println("Ended connection with " + clientName + ".");
            this.server.removeClient(this);
            server.updateParticipantList();
        }
    }

    /**
     * Adds the client connected with the thread to the list of clients and essentially the chatroom.
     */
    void joinClient() throws IOException, ClassNotFoundException {
        Data n;
        do {
            n = (Data) in.readObject();
            this.clientName = n.getSender();
        } while (!n.getMsgType().equals(Data.MsgType.AddClientToServer));
        System.out.println("Server connected with " + clientName + "!");
        server.addClient(this);
        server.updateParticipantList();
    }
    /**
     * Sends a Message to the client.
     */
    public void sendMsg(Data msg) {
        try {
            out.writeObject(msg);
        } catch (IOException e) {
        }
    }

    /**
     * Getter
     */
    String getClientName() {
        return this.clientName;
    }
}
