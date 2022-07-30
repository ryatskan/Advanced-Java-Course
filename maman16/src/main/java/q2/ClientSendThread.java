package q2;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * A Thread that is in charge of sending an amount of messages to the Server.
 */
public class ClientSendThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < Q2.amountOfMessages; i++) {
            sendToServer(i);
            try {
                sleep(Q2.sleepBetweenEachSend);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Receive an Integer, send it as a message to the server
     */
    public void sendToServer(int msg) {
        byte[] data = Integer.toString(msg).getBytes();
        try {
            DatagramPacket packet = new DatagramPacket(data, data.length, Q2.serverIP, Q2.serverPort);
            Q2.clientSocket.send(packet);
        } catch (IOException e) {
            System.out.println("Failed to send message " + msg);
        }
    }

}
