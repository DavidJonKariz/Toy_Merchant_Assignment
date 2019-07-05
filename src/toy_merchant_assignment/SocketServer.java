/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toy_merchant_assignment;

import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
    // Customer Details
    ArrayList<String> info = new ArrayList<>();
    
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
//            socket.close();
//            clientInput.close();
            closeServer();
        } catch(IOException i) {
            System.out.println(i);
        }
    }
    
    public void GUIserverConnection(javax.swing.JTextArea theTextArea, javax.swing.JFrame theFrame)
    {
        // start server connection
        try {
            server = new ServerSocket(port);
            theTextArea.setText(outputLine);
            appendText(theTextArea, "Server Initialized.\nWaiting for Client to connect.......\n");
            
            // Client accepts connection
            socket = server.accept();
            appendText(theTextArea, "Client Accepted\n\n");
            
            // Client input
            clientInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            
            SocketProtocol sp = new SocketProtocol();
            outputLine = sp.processInput(null);
            appendText(theTextArea, "Server: " + outputLine);
            System.out.println("Server: " + outputLine + "\n");
            
            // reads Client messages
            while(!line.equals("Done"))
            {
                if(line == null)
                    {
                        System.out.println("No Input Given. Please try Again.");
                    }
                    line = clientInput.readUTF();
                    System.out.println("Client: " + line);
                    appendText(theTextArea, "Client: " + line + "\n\n");
                    outputLine = sp.processInput(line);
                    appendText(theTextArea, "Server: " + outputLine);
                    System.out.println("Server: " + outputLine + "\n");
                    info.add(line);
            }
            
            // close socket connection
            appendText(theTextArea, "Connection is Being Closed...\n");
            System.out.println("Connection is Being Closed...");
            GUIcloseServer(theTextArea, theFrame);
        } catch(IOException | HeadlessException | IllegalArgumentException i) {
            System.out.println(i);
            JOptionPane.showMessageDialog(theFrame, i.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void closeServer()
    {
        try {
            socket.close();
            server.close();
            clientInput.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }  catch(Exception e) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void GUIcloseServer(javax.swing.JTextArea theTextArea, javax.swing.JFrame theFrame)
    {
        appendText(theTextArea, "The Customer's Input: " + line);
        appendText(theTextArea, "Connection is Being Closed...");
        System.out.println("The Customer's Input: " + line);
        System.out.println("Connection is Being Closed...");
        try {
            socket.close();
            server.close();
            clientInput.close();
            theFrame.dispatchEvent(new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING));
        } catch (IOException ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(theFrame, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }  catch(Exception e) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(theFrame, e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public int getServerPort(){return port;}
    
    public void setServerPort(int theServerPort){ port=theServerPort;}
    
    public String getLine(){return line;}
    
    public void setLine(String theServerPort){ line=theServerPort;}
    
    public ArrayList<String> getInfo(){return info;}
    
    public void setInfo(ArrayList<String> infoArray){ info=infoArray;}
    
    public String getOutputLine(){return outputLine;}
    
    public void setOutputLine(String theOutputLine){ outputLine=theOutputLine;}
    
    public void appendText(javax.swing.JTextArea theTextArea, String str)
    {
        theTextArea.append(str);
        theTextArea.setCaretPosition(theTextArea.getText().length());
    }
    
    public static void main(String args[])
    {
        SocketServer socketServer = new SocketServer();
        socketServer.setServerPort(Integer.parseInt("1200"));
        socketServer.serverConnection();
    }
}
