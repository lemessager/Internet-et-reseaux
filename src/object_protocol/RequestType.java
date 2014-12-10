package object_protocol;

public enum RequestType {

    GET,ADD,REMOVE,EDIT,LIST,QUIT;

    public static RequestType getRequest(String s) throws IllegalArgumentException {
        return RequestType.valueOf(s.toUpperCase());
    }
}
