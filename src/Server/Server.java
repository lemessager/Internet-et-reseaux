package Server;
import java.io.*;
import java.net.*;


public class Server extends Thread{

    public static int port=1993;
    
    public Server (int port){
        Server.port=  port;
    }

    public static void main(String[] args) throws IOException {
        
        
        ServerSocket listener = new ServerSocket(port);
        
        
        try {
            while (true) {
                Socket socket = listener.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    System.out.println(out);
                    out.println("bienvenu sur le port :"+listener.getLocalPort());
                    
                    System.out.println("connection etablie");
                    
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String reponse;
                    
                    while ((reponse = in.readLine()) != null){
                      System.out.println(reponse);
                      out.println(reponse);
                    }
                    
                    
                }
                catch (IOException e){
                    System.err.println("connection terminee"+e.getMessage());
                    
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


    
 
}
