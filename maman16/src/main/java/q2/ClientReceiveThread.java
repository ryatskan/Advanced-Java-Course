package q2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;
/**
 * A Thread that is in charge of receiving an amount of messages from the Server, and prints them.
 */

public class ClientReceiveThread extends Thread {
    @Override
    public void run() {
        int msgsSuccessfullyReceived = 0;
        try {
            for (int i = 0; i < Q2.amountOfMessages; i++) {
                byte[] receive = new byte[1024];
                DatagramPacket packet = new DatagramPacket(receive, receive.length);
                Q2.clientSocket.receive(packet);
                printPacket(packet);
                msgsSuccessfullyReceived++;
            }
        } catch (SocketTimeoutException e) {
            System.out.println("timeout");
        } catch (IOException e) {
            System.out.println("IO Error!");
        } finally {
            System.out.println("Successfully received " + msgsSuccessfullyReceived + " messages out of " + Q2.amountOfMessages + " expected.");
            Q2.clientSocket.close();
        }

    }

    /**
     * Prints the content of the given packet and its sender.
     */
    static void printPacket(DatagramPacket packet) {
        String content = new String(packet.getData()).substring(0, packet.getLength());
        System.out.println("Msg received from " + packet.getAddress().toString() + ". Msg's content: " + content);
    }


}
