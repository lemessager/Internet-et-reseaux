package Server;
import Requettes.Reponse;
import Requettes.Requete;

import java.io.*;
import java.net.*;


public class Serveur extends Thread{

    public static int port=1993;

    private Donnees donnees;
    private Protocole protocole;
    Reponse reponse;
    Requete requete;
    
    public Serveur(int port){
        donnees = new Donnees();
        initialiserDonnees();
        protocole = new Protocole(donnees);
        Serveur.port=  port;
    }

    private void initialiserDonnees() {
        donnees.add("Riana Rabehasy","Riri13");
        donnees.add("Riana Rabehasy","Jean_Jacques");

    }

    public void startServer() throws IOException{
        ServerSocket listener = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = listener.accept();

                try {
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                    System.out.println("stream ok");


                    reponse = new Reponse(true, "Bienvenue sur le serveur.");
                    os.writeObject((Object) reponse);
                    try {
                        while (true) {

                            if ((requete = (Requete) is.readObject()) != null) {
                                reponse = protocole.getReponse(requete);
                                if (reponse != null) {
                                    os.writeObject((Object) reponse);
                                }
                            }
                        }
                    }
                    catch (IOException e) {
                        is.close();
                        os.close();
                        socket.close();
                        System.err.println("Deconnexion du client!");
                    }

                }

                catch (IOException e) {
                    System.err.println("Erreur de connexion : "+e.getMessage());
                    System.exit(0);
                }
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


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
        Serveur server = new Serveur(1993);
        server.startServer();
    }


    
 
}
