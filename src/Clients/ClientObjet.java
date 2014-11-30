package Clients;

import ProtocoleObjet.Reponse;
import ProtocoleObjet.Requete;
import ProtocoleObjet.TypeDeRequete;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Trivial client for the date server.
 */
public class ClientObjet {

    int port=2000;
    
    public ClientObjet(int port){
        this.port=port;
    }

    public Requete creerRequete (){
        Scanner sc = new Scanner(System.in);
        boolean validRequest=false;
        String[] tab=new String[3];
        String str;
        TypeDeRequete typeReq;

        do {
            System.out.print("Veuillez saisir votre requete (requete-nom-surnom/list/quit) : ");
            str = sc.nextLine();

            tab = str.split("-");
            typeReq=TypeDeRequete.getRequete(tab[0]);
            switch (tab.length) {
                case 0 :
                    System.out.println("Requête mal formée.");
                    validRequest=false;
                    break;
                case 1 :
                    if ((typeReq == TypeDeRequete.QUIT) || (typeReq == TypeDeRequete.LIST)) {
                        return new Requete(typeReq,"","");
                    }
                    else {
                        System.out.println("Requête mal formée.");
                        validRequest=false;
                    }
                    break;
                case 2 :
                    System.out.println("Requête mal formée.");
                    validRequest = false;
                    break;
                case 3 :
                    validRequest=true;
                    break;
            }

        } while (!validRequest);

        return new Requete(TypeDeRequete.getRequete(tab[0]),tab[1],tab[2]);

    }

    public String getAddress (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer l'adresse du serveur :");
        String str = sc.nextLine();
        return str;

    }

    public void startClient() throws IOException{
        String serverAdress= getAddress();
        ObjectOutputStream sortie=null;
        ObjectInputStream entree=null;
        Socket socket=null;

        try {
            socket = new Socket(serverAdress, port);
            Requete requete;
            Reponse reponse;
            try {
                sortie=new ObjectOutputStream(socket.getOutputStream());
                entree=new ObjectInputStream(socket.getInputStream());

                reponse = (Reponse) entree.readObject();
                System.out.println(reponse.getContent());

                while (true) {
                    requete = creerRequete();

                    /*if (requete == null) {
                        break;
                    }
                    else {
                        sortie.writeObject(requete);
                        reponse = (Reponse) entree.readObject();
                        System.out.println(reponse.getContent());
                    }*/
                    sortie.writeObject(requete);
                    reponse = (Reponse) entree.readObject();
                    System.out.println("Lu");
                    if (reponse.deconnectedFromClient()) {
                        break;
                    }
                    System.out.println(reponse.getContent());
                }
            }
            catch (IOException e) {
                System.err.println("Erreur de création des flux d'entrée-sortie.");
            }
        }
        catch (ClassNotFoundException e) {
            System.err.println("La réponse du serveur n'a pas pu être lue correctement.");
            System.err.println("Deconnexion du client.");
        }
        catch (UnknownHostException e) {
            System.err.println("Erreur de connexion au serveur.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (entree!=null) entree.close();
            if (sortie!=null) sortie.close();
            if (socket!=null) socket.close();
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        ClientObjet clientObjet = new ClientObjet(2000);
        clientObjet.startClient();
    }
}