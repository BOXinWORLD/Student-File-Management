package frame;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;

import common.DocServer;
 
public class SocketServer extends ServerSocket {
    private static final int SERVER_PORT =12345;
 
    public SocketServer()throws IOException {
        super(SERVER_PORT);
 
        try {
            while (true) {
                Socket socket = accept();
                new CreateServerThread(socket);//当有请求时，启一个线程处理
            }
        }catch (IOException e) {
        }finally {
            close();
        }
    }
 
    //线程类
    class CreateServerThread extends Thread {
        private Socket client;
        private BufferedReader bufferedReader;
        private PrintWriter printWriter;
 
        public CreateServerThread(Socket s)throws IOException {
            client = s;
 
            
            System.out.println("\n Client(" + getName() +") come in...");
             
            start();
        }
 
        public void run() {
        DocServer application = new DocServer(); // create server
//  	      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  	      
  	      application.runServer(client); // run server application
        }
    }
 
    public static void main(String[] args)throws IOException {
        new SocketServer();
    }
}