package Serveur;

import ProtocoleObjet.Reponse;
import ProtocoleObjet.Requete;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by riana-r on 01/12/14.
 */
public class ThreadClient implements Runnable {
    private Socket socketClient;
    private Protocole protocole;

    public ThreadClient(Socket socketClient, Protocole protocole) {
        this.socketClient=socketClient;
        this.protocole=protocole;
    }

    @Override
    public void run() {
        Reponse reponse;
        Requete requete;
        try {
            ObjectOutputStream os = new ObjectOutputStream(socketClient.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socketClient.getInputStream());


            reponse = new Reponse(true, "Bienvenue sur le serveur.",false);
            os.writeObject(reponse);
            try {
                while (true) {

                    if ((requete = (Requete) is.readObject()) != null) {
                        reponse = protocole.getReponse(requete);
                        if (reponse != null) {
                            os.writeObject(reponse);
                            if (reponse.deconnectedFromClient()) {
                                throw new IOException();
                            }
                        }

                    }
                }
            }
            catch (IOException e) {
                is.close();
                os.close();
                socketClient.close();
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
