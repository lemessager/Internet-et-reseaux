package Client;

import java.io.*;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class Client {

    static int port=1993;
    
    public Client (int port){
        Client.port=port;
    }

    public void startClient() throws IOException{
        String serverAddress = JOptionPane.showInputDialog(
                "Enter IP Address of the server : ");
        Socket s = new Socket(serverAddress,port);


    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(1993);
        client.startClient();
    }
}