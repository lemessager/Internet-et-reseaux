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
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);
        BufferedReader input =
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        JOptionPane.showMessageDialog(null, answer);
        String message="";

        message = JOptionPane.showInputDialog(
                "Enter a message : ");
        String line;
        while (message != null) {
            System.out.println("Boucle client : message = "+message);
            out.println(message);
            while ((line=input.readLine()) != null) {
                System.out.println("itération");
                answer += line + "\n";
                System.out.println(line);

            }
            System.out.println("SORTI BOUCLE");
            message=JOptionPane.showInputDialog(
                    "Entrez une nouvelle requête ");
        }
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(1993);
        client.startClient();
    }
}