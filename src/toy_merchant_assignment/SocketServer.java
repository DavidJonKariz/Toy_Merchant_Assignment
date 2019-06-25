/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toy_merchant_assignment;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * @author djkar
 */
public class SocketServer extends Thread{
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream clientInput = null;
    int port;
    String line="";
    String outputLine="";
    ArrayList<String> info = new ArrayList<String>();
    
    public void serverConnection()
    {
        // start server connection
        try {
            server = new ServerSocket(port);
            outputLine = "Server Initialized.\nWaiting for Client to connect.......\n";
            System.out.println(outputLine);
            // Client accepts connection
            socket = server.accept();
            outputLine += "Client Accepted\n";
            System.out.println(outputLine);
            
            // Client input
            clientInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            
            SocketProtocol sp = new SocketProtocol();
            outputLine = sp.processInput(null);
            System.out.println("Server: " + outputLine);
            
            // reads Client messages
            while(!line.equals("Done"))
            {
                try
                {
                    line = clientInput.readUTF();
                    System.out.println("Client: " + line);
                    outputLine = sp.processInput(line);
                    System.out.println("Server: " + outputLine);
                    if(!line.equals("Done"))
                        info.add(line);
                } catch(IOException i) {
                    System.out.println(i);
                }
            }
            
            // close socket connection
            System.out.println("Connection is Being Closed...");
            System.out.println(info);
            socket.close();
            clientInput.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }
    
    public void GUIserverConnection(javax.swing.JTextArea theTextArea)
    {
        // start server connection
        try {
            server = new ServerSocket(port);
            theTextArea.setText(outputLine);
//            outputLine = "Server Initialized.\nWaiting for Client to connect.......\n";
            theTextArea.append("Server Initialized.\nWaiting for Client to connect.......\n");
            // Client accepts connection
            socket = server.accept();
//            outputLine += "Client Accepted\n";
            theTextArea.append("Client Accepted\n");
            
            // Client input
            clientInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            
            SocketProtocol sp = new SocketProtocol();
            theTextArea.append("Server: " + sp.processInput(null));
            // reads Client messages
            while(!line.equals("Done"))
            {
                try
                {
                    if(line == null)
                    {
                        System.out.println("No Further Input Required. Exit Please.");
                    }
                    line = clientInput.readUTF();
                    info.add(line);
                    System.out.println("Client: " + line);
                    theTextArea.append("Server: " + sp.processInput(line));
                    if(!line.equals("Done"))
                        info.add(line);
                } catch(IOException i) {
                    System.out.println(i);
                }
            }
            
            // close socket connection
            theTextArea.append("Connection is Being Closed...");
//            System.out.println("Connection is Being Closed...");
            socket.close();
            clientInput.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }
    
    public int getServerPort(){return port;}
    
    public void setServerPort(int theServerPort){ port=theServerPort;}
    
    public String getLine(){return line;}
    
    public void setLine(String theServerPort){ line=theServerPort;}
    
    public String getOutputLine(){return outputLine;}
    
    public void setOutputLine(String theOutputLine){ outputLine=theOutputLine;}
    
    public static void main(String args[])
    {
        SocketServer socketServer = new SocketServer();
        socketServer.setServerPort(Integer.parseInt("1200"));
        socketServer.serverConnection();
    }
}
