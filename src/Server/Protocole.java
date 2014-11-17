package Server;

import Requettes.Reponse;
import Requettes.Requete;
import Requettes.TypeDeRequete;

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
        Reponse reponse = new Reponse(false, "");
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
            case LIST:
                reponse.setRequestExecuted(true);
                reponse.setMessageReponse(donnees.list());
                break;
        }
        return reponse;
    }
}
