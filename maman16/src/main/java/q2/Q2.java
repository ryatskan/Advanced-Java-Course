package q2;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Main class for question 2.
 */
public class Q2 {
    static final int serverPort = 8888;
    static final int clientPort = 10;
    static final int amountOfMessages = 10;
    static final int waitingTime = 10;
    static final int sleepBetweenEachSend = 4000;
    static DatagramSocket clientSocket;
    static InetAddress serverIP;


    public static void main(String[] args) {
        try {
            serverIP = InetAddress.getByName(args[0]);
            // create the socket for sending and receiving for the Client
            clientSocket = new DatagramSocket(clientPort);
            clientSocket.setSoTimeout(waitingTime * 1000);
            new ClientController();
        } catch (IOException e) {
            System.out.println("Connection Error");
        }
    }
}

