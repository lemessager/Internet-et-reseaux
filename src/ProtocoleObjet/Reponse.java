package ProtocoleObjet;

import java.io.Serializable;

/**
 * Created by riana-r on 17/11/14.
 */
public class Reponse implements Serializable {
    boolean requestExecuted;
    String content;
    boolean deconnectedFromClient;


    public Reponse(boolean requestExecuted, String content, boolean deconnectedFromClient) {
        this.requestExecuted = requestExecuted;
        this.content = content;
        this.deconnectedFromClient=deconnectedFromClient;
    }

    public boolean isRequestExecuted() {
        return requestExecuted;
    }

    public String getContent() {
        return content;
    }

    public boolean deconnectedFromClient() {
        return deconnectedFromClient;
    }

    public void setDeconnectedFromClient(boolean deconnectedFromClient) {
        this.deconnectedFromClient = deconnectedFromClient;
    }

    public void setRequestExecuted(boolean requestExecuted) {
        this.requestExecuted = requestExecuted;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
