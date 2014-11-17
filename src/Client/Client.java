package Client;

import Requettes.Reponse;
import Requettes.Requete;
import Requettes.TypeDeRequete;

import java.io.*;
import java.net.Socket;
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
        return new Requete(TypeDeRequete.getRequette(tab[0].charAt(0)),tab[1],tab[2]);

    }

    public String getAddress (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir un mot :");
        String str = sc.nextLine();
        return str;

    }

    public void startClient() throws IOException{
        String serverAdress= getAddress();
        Socket s = new Socket(serverAdress,port);

        Requete requete;
        Reponse reponse;

        ObjectInputStream entree = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream sortie = new ObjectOutputStream(s.getOutputStream());
    while(true) {
        sortie.writeObject(creerRequete());
        try {
            reponse = (Reponse) entree.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(1993);
        client.startClient();
    }
}