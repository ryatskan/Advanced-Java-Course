package q2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
/**
 * The Server Thread that receives messages from clients and send them back unchanged.
 */
public class Server extends Thread {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public Server() {
    }

    @Override
    public void run() {
        while (true) {
            sendAndReceive();
        }
    }
    /**
     * Receive messages in the serverPort, and send them back the sender unchanged.
     */
    public void sendAndReceive() {
        try {
            DatagramSocket receiveSocket = new DatagramSocket(Q2.serverPort);
            byte[] receive = new byte[1024];
            DatagramPacket packetReceive = new DatagramPacket(receive, receive.length);
            receiveSocket.receive(packetReceive);
            System.out.println("Received a message");
            DatagramPacket packetSend = new DatagramPacket(receive, packetReceive.getLength(), packetReceive.getAddress(), Q2.clientPort);
            receiveSocket.send(packetSend);
        } catch (IOException e) {

        }

    }
}
