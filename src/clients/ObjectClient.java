package clients;

import error_codes.ErrorCode;
import object_protocol.Response;
import object_protocol.RequestType;
import object_protocol.Request;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Trivial client for the date server.
 */
public class ObjectClient {

    int port=2000;
    
    public ObjectClient(int port){
        this.port=port;
    }

    public Request createRequest(){
        Scanner sc = new Scanner(System.in);
        boolean validRequest=false;
        List<String> tab;
        String str;
        RequestType typeReq;

        do {
            System.out.print("Veuillez saisir votre requete (requete-nom-surnom/list/quit) : ");
            str = sc.nextLine();

            tab = Arrays.asList(str.split("-"));
            try {
                typeReq = RequestType.getRequest(tab.get(0));
            }
            catch (IllegalArgumentException e) {
                System.out.println("Requête mal formée.");
                validRequest=false;
                continue;
            }

            switch (tab.size()) {
                case 1 :
                    if ((typeReq == RequestType.QUIT) || (typeReq == RequestType.LIST)) {
                        return new Request(typeReq,"","");
                    }
                    else {
                        System.out.println("Requête mal formée.");
                        validRequest=false;
                    }
                    break;

                case 3 :
                    validRequest=true;
                    break;
                default :
                    System.out.println("Requête mal formée.");
                    validRequest=false;
                    break;
            }

        } while (!validRequest);

        return new Request(RequestType.getRequest(tab.get(0)),tab.get(1),tab.get(2));

    }

    public String getAddress(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez entrer l'adresse du serveur :");
        String str = sc.nextLine();
        return str;

    }

    public String printResponse(Response response) {
        StringBuilder sb=new StringBuilder();
        Map<String,List<String>> content = response.getContent();
        for (String nom : content.keySet()) {
            sb.append("Surnoms associés à ");
            sb.append(nom);
            sb.append(" : ");
            sb.append("\n");
            List<String> surnoms = content.get(nom);
            for (String surnom : surnoms) {
                sb.append("-->");
                sb.append(surnom);
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void startClient() throws IOException{
        String serverAddress="";
        ObjectOutputStream output=null;
        ObjectInputStream input=null;
        Socket socket=null;

        try {
            serverAddress= getAddress();
            socket = new Socket(serverAddress, port);
            Request request;
            Response response;
            try {
                output=new ObjectOutputStream(socket.getOutputStream());
                input=new ObjectInputStream(socket.getInputStream());

                response = (Response) input.readObject();
                if (response.getErrorCode() != ErrorCode.DECONNECTED_FROM_CLIENT) {
                    while (true) {
                        request = createRequest();

                        output.writeObject(request);
                        response = (Response) input.readObject();
                        ErrorCode errorCode = response.getErrorCode();
                        if (errorCode == ErrorCode.DECONNECTED_FROM_CLIENT) {
                            break;
                        } else {
                            switch (errorCode) {
                                case NONE:
                                    System.out.println(printResponse(response));
                                    break;
                                case INVALID_ARGUMENT:
                                    System.out.println("Le nom ou le surnom n'est pas valide.");
                                    break;
                                case SURNAME_EXISTS:
                                    System.out.println("Le surnom existe déjà.");
                                    break;

                            }
                        }
                    }
                }
                else {

                }
            }
            catch (IOException e) {
                System.err.println("Erreur de création des flux d'entrée-sortie.");
            }
        }
        catch (ClassNotFoundException e) {
            System.err.println("La réponse du serveur n'a pas pu être lue correctement.");
            System.err.println("Deconnexion du client.");
        }
        catch (UnknownHostException e) {
            System.err.println("Erreur de connexion au serveur.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (input!=null) input.close();
            if (output!=null) output.close();
            if (socket!=null) socket.close();
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        ObjectClient objectClient = new ObjectClient(2000);
        objectClient.startClient();
    }
}