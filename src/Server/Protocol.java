package Server;

import Requettes.Reponse;
import Requettes.Requete;
import Requettes.TypeDeRequete;

import static Requettes.TypeDeRequete.*;

/**
 * Created by riana-r on 17/11/14.
 */
public class Protocol {
    private Data data;
    public Protocol (Data data){
        this.data=data;
    }
    public Reponse getReponse (Requete requete){
        TypeDeRequete type = requete.getTypeDeRequete();
        String surnom, nom;
        Reponse reponse = new Reponse(false, "");
        switch (type){
            case GET:
                break;
            case ADD:
                break;
            case REMOVE:
                break;
            case EDIT:
                break;
            case LIST:
                reponse=new Reponse(true, data.list());
                break;
        }
        return reponse;
    }
}
