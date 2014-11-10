package Server;
import java.io.*;
import java.net.*;


public class Server extends Thread{

    public static int port=1993;

    private Data data;
    
    public Server (int port){
        data = new Data();
        data.add("Riana Rabehasy","Riri13");
        data.add("Riana Rabehasy","Jean_Jacques");
        Server.port=  port;
    }

    public void startServer() throws IOException{
        ServerSocket listener = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    System.out.println(out);
                    out.println("Bienvenue sur le port :"+listener.getLocalPort());

                    System.out.println("Connexion etablie");

                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String reponse;
                    System.out.println("server");

                    while ((reponse = in.readLine()) != null){
                        System.out.println("message reçu, "+reponse);
                        if (reponse.equals("list")) {
                            out.println(data.list());
                        }
                    }


                }
                catch (IOException e){
                    System.err.println("Connexion terminee\n"+e.getMessage());

                }
                finally {
                    socket.close();
                }
            }
        }

        catch (IOException e){
            System.err.println("Connection error "+port);
        }
        finally {
            listener.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(1993);
        server.startServer();
    }


    
 
}
