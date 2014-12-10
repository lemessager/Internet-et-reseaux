package object_protocol;
import java.io.Serializable;


public class Request implements Serializable{

    private RequestType requestType;
    private String nom;
    private String surnom;
    public Request(RequestType tdr, String nom, String surnom){
        requestType =tdr;
        this.nom=nom;
        this.surnom=surnom;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getNom() {
        return nom;
    }

    public String getSurnom() {
        return surnom;
    }
}
