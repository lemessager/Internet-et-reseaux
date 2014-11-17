package ProtocoleObjet;

public enum TypeDeRequete {

    GET,ADD,REMOVE,EDIT,LIST,QUIT;

    public static TypeDeRequete getRequete(String s){
        return TypeDeRequete.valueOf(s.toUpperCase());
    }
}
