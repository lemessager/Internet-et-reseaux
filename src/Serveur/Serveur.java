package Serveur;
import ProtocoleObjet.Reponse;
import ProtocoleObjet.Requete;

import java.io.*;
import java.net.*;


public class Serveur {

    public static int port=2000;

    private Donnees donnees;
    private Protocole protocole;

    public Serveur(int port){
        donnees = new Donnees();
        initialiserDonnees();
        protocole = new Protocole(donnees);
        Serveur.port=  port;
    }

    private void initialiserDonnees() {
        donnees.add("Nom1","Surnom1");
        donnees.add("Nom1","Surnom2");

        donnees.add("Nom2","Surnom1");
        donnees.add("Nom2","Surnom2");

    }

    public void startServer() throws IOException{
        ServerSocket listener = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = listener.accept();
                new Thread(new ThreadClient(socket,protocole)).run();
            }
        }

        catch (IOException e){
            System.err.println("Connection error "+port);
        }
        finally {
            if (!listener.isClosed()) {
                listener.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Serveur server = new Serveur(2000);
        server.startServer();
    }


    
 
}
