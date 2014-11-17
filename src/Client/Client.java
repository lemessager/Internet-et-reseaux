package Client;

import Requettes.Reponse;
import Requettes.Requete;
import Requettes.TypeDeRequete;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Trivial client for the date server.
 */
public class Client {

    static int port=1993;
    
    public Client (int port){
        Client.port=port;
    }

    public Requete creerRequete (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir requete :");
        String str = sc.nextLine();
        String [] tab = str.split("-");
        if (TypeDeRequete.getRequette(tab[0].charAt(0))==TypeDeRequete.QUIT)
            return null;
        return new Requete(TypeDeRequete.getRequette(tab[0].charAt(0)),tab[1],tab[2]);

    }

    public String getAddress (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer l'adresse du serveur :");
        String str = sc.nextLine();
        return str;

    }

    public void startClient() throws IOException{
        String serverAdress= getAddress();
        System.out.println(serverAdress);
        try {
            Socket s = new Socket(serverAdress, port);
            Requete requete;
            Reponse reponse;

            ObjectOutputStream sortie = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream entree = new ObjectInputStream(s.getInputStream());

            try {
                System.out.println("try");
                reponse = (Reponse) entree.readObject();
                System.out.println(reponse.getMessageReponse());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            while (true) {
                requete =creerRequete();
                sortie.writeObject(requete);
                if (requete==null) {
                    entree.close();
                    sortie.close();
                    s.close();
                    System.exit(0);
                }
                try {
                    reponse = (Reponse) entree.readObject();
                    System.out.println(reponse.getMessageReponse());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(1993);
        client.startClient();
    }
}