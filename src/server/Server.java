package server;

import java.io.*;
import java.net.*;


public class Server {

    public static int port=2000;

    private Data data;
    private ObjectProtocol objectProtocol;

    public Server(int port){
        data = new Data();
        initializeData();
        objectProtocol = new ObjectProtocol(data);
        Server.port=port;
    }

    private void initializeData() {
        data.add("Nom1","Surnom1");
        data.add("Nom1","Surnom2");

        data.add("Nom2","Surnom1");
        data.add("Nom2","Surnom2");

    }

    public void startServer() throws IOException{
        ServerSocket listener = new ServerSocket(port);

        while (true) {
            Socket socket = listener.accept();
            new Thread(new ClientThread(socket, objectProtocol)).start();
        }
        /*

        catch (IOException e){
            System.err.println("Connection error "+port);
        }
        finally {
            if (!listener.isClosed()) {
                listener.close();
            }
        }*/
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(2000);
        server.startServer();
    }


    
 
}
