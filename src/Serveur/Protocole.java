package Serveur;

import ProtocoleObjet.Reponse;
import ProtocoleObjet.Requete;
import ProtocoleObjet.TypeDeRequete;

/**
 * Created by riana-r on 17/11/14.
 */
public class Protocole {
    private Donnees donnees;

    public Protocole(Donnees donnees){
        this.donnees = donnees;
    }
    public Reponse getReponse (Requete requete){
        TypeDeRequete type = requete.getTypeDeRequete();
        String surnom=requete.getSurnom();
        String nom = requete.getNom();
        Reponse reponse = new Reponse(false, "",false);
        switch (type){
            case GET:
                break;
            case ADD:
                if (donnees.add(nom, surnom)) {
                    reponse.setRequestExecuted(true);
                }
                break;
            case REMOVE:
                break;
            case EDIT:
                break;
            case QUIT:
                reponse.setDeconnectedFromClient(true);
            case LIST:
                reponse.setRequestExecuted(true);
                reponse.setContent(donnees.list());
                break;
        }
        return reponse;
    }
}
