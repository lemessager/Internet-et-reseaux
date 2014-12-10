package server;

import error_codes.ErrorCode;
import object_protocol.Response;
import object_protocol.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by riana-r on 01/12/14.
 */
public class ClientThread implements Runnable {
    private Socket clientSocket;
    private ObjectProtocol objectProtocol;

    public ClientThread(Socket clientSocket, ObjectProtocol objectProtocol) {
        this.clientSocket = clientSocket;
        this.objectProtocol = objectProtocol;
    }

    @Override
    public void run() {
        Response response;
        Request request;
        ObjectOutputStream os=null;
        ObjectInputStream is=null;

        try {
            os = new ObjectOutputStream(clientSocket.getOutputStream());
            is = new ObjectInputStream(clientSocket.getInputStream());

            response = new Response(null, ErrorCode.NONE);
            os.writeObject(response);

            while (true) {
                if ((request = (Request) is.readObject()) != null) {
                    response = objectProtocol.getReponse(request);
                    if (response != null) {
                        os.writeObject(response);
                        if (response.getErrorCode() == ErrorCode.DECONNECTED_FROM_CLIENT) {
                            break;
                        }
                    }
                }
            }
        }

        catch (IOException e) {
            System.err.println("Erreur de connexion");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (is!=null) is.close();
                if (os!=null) os.close();
                clientSocket.close();
                System.err.println("Déconnexion du client.");
            }
            catch (IOException e) {
                System.err.println("Erreur lors de la déconnexion du client.");
            }

        }
    }

}
