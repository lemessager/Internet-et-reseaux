package Clients;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by Benjamin on 17/11/2014.
 */
public class ClientString {

    static int port=27645;

    public ClientString (int port){
        ClientString.port=port;
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
            String requete;
            String reponse;

            PrintWriter sortie = new PrintWriter(s.getOutputStream(),true);
            BufferedReader entree = new BufferedReader(new InputStreamReader(s.getInputStream()));


                System.out.println("try");



            while (true) {
                requete =creerRequete();
                sortie.print("1\nazelkazje\nazlejzaelkj\n");
                System.out.println("requete envoye");
                if (requete==null) {
                    entree.close();
                    sortie.close();
                    s.close();
                    System.exit(0);
                }

                reponse = entree.readLine();

                System.out.println(reponse);


            }
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ClientString clientString = new ClientString(27645);
        clientString.startClientString();
    }
}
