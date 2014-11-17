package Requettes;
import java.util.ArrayList;


public class Requete {

    private TypeDeRequete typeDeRequete;
    private String nom;
    private String surnom;
    public Requete (TypeDeRequete tdr, String nom, String surnom){
        typeDeRequete =tdr;
        this.nom=nom;
        this.surnom=surnom;
    }

    public TypeDeRequete getTypeDeRequete() {
        return typeDeRequete;
    }

    public String getNom() {
        return nom;
    }

    public String getSurnom() {
        return surnom;
    }
}
