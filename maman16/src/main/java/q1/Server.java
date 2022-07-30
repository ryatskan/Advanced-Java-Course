package q1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Server which controls the chatting, and the only way of communication between clients.
 */
public class Server {
    private final static int PORT = 10;
    ArrayList<ServerThread> clients = new ArrayList<>();

    /**
     * The constructor and the main method in the class. Constantly receives and connects new clients.
     */
    public Server() {
        ServerSocket serverSocket;
        Socket socket;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is listening!");
            while (!serverSocket.isClosed()) {
                socket = serverSocket.accept();
                ServerThread newClient = new ServerThread(socket, this);
                newClient.start();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("An error occurred!");
        }

    }

    public static void main(String[] args) {
        new Server();
    }
    /**
     * Send a message to all the clients.
     */
    public void sendToAll(Data data) {
        for (ServerThread serverThread : this.clients) {
            serverThread.sendMsg(data);
        }

    }
    /**
     * Sends the clients the updated participants list.
     */
    public void updateParticipantList() {
        String[] partList = new String[this.clients.size()];
        for (int i = 0; i < partList.length; i++) {
            partList[i] = clients.get(i).getClientName();
        }
        Arrays.sort(partList);
        sendToAll(new Data("server", Data.MsgType.UpdatedClientList, partList));
    }
    /**
     * Remove a connection between the server and a client from the list of connections (clients list),
     * and notify all clients.
     */
    void removeClient(ServerThread client) {
        this.clients.remove(client);
        sendToAll(new Data("server", client.getClientName() + " has left the chatroom!", Data.MsgType.NewChatBoxMessage));
    }
    /**
     * Adds the connection between the server and a client to the list of connections,
     * and notify all clients.
     */
    void addClient(ServerThread client) {
        this.clients.add(client);
        sendToAll(new Data("server", client.getClientName() + " has joined the chatroom!", Data.MsgType.NewChatBoxMessage));
    }
}
