package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
    int port;
    ClientHandler ch;
    boolean stop=false;

    public MyServer(int _port, ClientHandler _ch){
        this.port=_port;
        this.ch=_ch;
    }
    public void start(){
        this.stop=false;
        new Thread(() -> {
        ServerSocket server=null;
        try {
            
       
        server=new ServerSocket(this.port);
        server.setSoTimeout(1000);
        while (!this.stop){
            try {
                Socket aClient=server.accept();
                try {
                    this.ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
                    aClient.getInputStream().close();
                    aClient.getOutputStream().close();
                    aClient.close();
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                }
            }                                                                                                                                                                                                                                                                                                                                                           catch (SocketTimeoutException e) {
                System.out.println("Socket timed out: " + e.getMessage());
            }
        }
    } catch (IOException e) {
        System.out.println("Server error: " + e.getMessage());
    }finally{
        if (server != null && !server.isClosed()) {
            try {
                server.close();
            } catch (IOException e) {
                System.out.println("Failed to close server: " + e.getMessage());
            }
        }
    }
    }).start();  // Start the thread
    }
    public void close(){
        this.stop=true;
    }

}
