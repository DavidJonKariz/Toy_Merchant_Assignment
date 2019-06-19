/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toy_merchant_assignment;

import java.io.*;
import java.net.*;

/**
 *
 * @author djkar
 */
public class SocketServer extends Thread{
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream clientInput = null;
//    private DataInputStream input = null;
//    private DataOutputStream output = null;
    
    public SocketServer (int port) {
        // start server connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server Initialized.");
            System.out.println("Waiting for Client to connect.......");
            // Client accepts connection
            socket = server.accept();
            System.out.println("Client Accepted");
            
            // Client input
//            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            clientInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            //Client output
//            output = new DataOutputStream(socket.getOutputStream());
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            String line="";
            String outputLine="";
            SocketProtocol sp = new SocketProtocol();
            outputLine = sp.processInput(null);
            System.out.println(outputLine);
            
            // reads Client messages
            while(!line.equals("Done"))
            {
                try
                {
                    line = clientInput.readUTF();
                    System.out.println(line);
                    outputLine = sp.processInput(line);
                    System.out.println(outputLine);
                } catch(IOException i) {
                    System.out.println(i);
                }
            }
            
            // close socket connection
            System.out.println("Connection is Being Closed...");
            socket.close();
            clientInput.close();
        } catch(IOException i) {
            System.out.println(i);
        }
        
    }
    
    public static void main(String args[])
    {
        SocketServer socketServer = new SocketServer(1200);
    }
}
