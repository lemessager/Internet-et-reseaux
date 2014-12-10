package clients;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Benjamin on 17/11/2014.
 */
public class StringClient {

    static int port=27645;

    public StringClient(int port){
        StringClient.port=port;
    }

    public String creerRequete (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir requete :");
        String str = sc.nextLine();
        return str;

    }

    public String getAddress (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer l'adresse du serveur :");
        String str = sc.nextLine();
        return str;

    }

    public void startClientString() throws IOException {
        String serverAdress= getAddress();
        System.out.println(serverAdress);
        try {
            Socket s = new Socket(serverAdress, port);
            String request;
            String response;

            PrintWriter sortie = new PrintWriter(s.getOutputStream(),true);
            BufferedReader entree = new BufferedReader(new InputStreamReader(s.getInputStream()));


                System.out.println("try");



            while (true) {
                request =creerRequete();
                sortie.print("1\nazelkazje\nazlejzaelkj\n");
                System.out.println("requete envoye");
                if (request==null) {
                    entree.close();
                    sortie.close();
                    s.close();
                    System.exit(0);
                }

                response = entree.readLine();

                System.out.println(response);


            }
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        StringClient stringClient = new StringClient(27645);
        stringClient.startClientString();
    }
}
