package Requettes;

public enum TypeDeRequete {

    GET,ADD,REMOVE,EDIT,LIST;

    public static TypeDeRequete getRequette(char s){
        switch (s){
            case ('G'):return GET;
            case ('A'):return ADD;
            case ('R'):return REMOVE;
            case ('E'):return EDIT;
            case ('L'):return LIST;
        }
        return LIST;
    }
}
