package Requettes;

/**
 * Created by riana-r on 17/11/14.
 */
public class Reponse {
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
}
