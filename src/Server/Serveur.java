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
                    ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());


                    reponse = new Reponse(true, "Bienvenue sur le serveur. Entrez une requête : ");
                    os.writeObject((Object) reponse);

                    while (true) {
                        try {
                            if ((requete = (Requete) is.readObject()) != null) {
                                reponse = protocole.getReponse(requete);
                                os.writeObject((Object) reponse);
                            }
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                catch (IOException e) {
                    System.err.println("Erreur de connexion : "+e.getMessage());
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
            listener.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Serveur server = new Serveur(1993);
        server.startServer();
    }


    
 
}
