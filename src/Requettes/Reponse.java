package Requettes;

import java.io.Serializable;

/**
 * Created by riana-r on 17/11/14.
 */
public class Reponse implements Serializable {
    boolean requestExecuted;
    String messageReponse;

    public Reponse(boolean requestExecuted, String messageReponse) {
        this.requestExecuted = requestExecuted;
        this.messageReponse = messageReponse;
    }

    public boolean isRequestExecuted() {
        return requestExecuted;
    }

    public String getMessageReponse() {
        return messageReponse;
    }

    public void setRequestExecuted(boolean requestExecuted) {
        this.requestExecuted = requestExecuted;
    }

    public void setMessageReponse(String messageReponse) {
        this.messageReponse = messageReponse;
    }
}
