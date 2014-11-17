package Requettes;
import java.util.ArrayList;


public class Requete {

    TypeDeRequete typeDeRequete;
    private String nom;
    private String surnom;
    public Requete (TypeDeRequete tdr, String nom, String surnom){
        typeDeRequete =tdr;
        this.nom=nom;
        this.surnom=surnom;
    }
    
}
