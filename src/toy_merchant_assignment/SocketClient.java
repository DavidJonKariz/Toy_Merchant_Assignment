/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toy_merchant_assignment;

import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author djkar
 */
public class SocketClient {
    //establish socket connection details, such as input and output streams for reading and displaying
    private Socket socket = null;
    private BufferedReader serverInput = null;
    private DataOutputStream serverOutput = null;
    //reads message from input
    String serverMsg = "";
    String clientMsg = "";
    // IP address and port number as arguments
    String address;
    int port;
    
    // establish connection
    public void startConnection()
    {
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connection Established");
            
            //input
            serverInput = new BufferedReader(new InputStreamReader(System.in));
            
            //output
            serverOutput = new DataOutputStream(socket.getOutputStream());
        } catch(UnknownHostException u) {
            System.out.println(u);
        } catch(IOException i) {
            System.out.println(i);
        }
    }
    
    // establish connection for the GUI
    public void GUIstartConnection(javax.swing.JTextArea theTextArea)
    {
        try
        {
            socket = new Socket(address, port);
            theTextArea.append("Connection Established\n");
            
            //input
            serverInput = new BufferedReader(new InputStreamReader(System.in));
            
            //output
            serverOutput = new DataOutputStream(socket.getOutputStream());
        } catch(UnknownHostException u) {
            System.out.println(u);
        } catch(IOException i) {
            System.out.println(i);
        }
    }
    
    // connection closed
    public void closeConnection()
    {
        try
        {
            System.out.println("Connection Closure.");
            serverInput.close();
            serverOutput.close();
            socket.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }
    
    // connection closed for the GUI
    public void GUIcloseConnection(javax.swing.JTextArea theTextArea, javax.swing.JFrame theFrame)
    {
        JOptionPane errorPane = new JOptionPane("The Server Connection is Being Closed.", JOptionPane.WARNING_MESSAGE);
        JDialog errorDialog = errorPane.createDialog("Closing Connection");
        errorDialog.setAlwaysOnTop(true);
        errorDialog.setVisible(true);
        int option = JOptionPane.showConfirmDialog(theFrame, "Are you sure you want to exit?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(option == JOptionPane.YES_OPTION)
        {
            theFrame.dispatchEvent(new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING));
        }
        try
        {
            theTextArea.append("Connection Closure\n");
            socket.close();
            serverInput.close();
            serverOutput.close();
            theFrame.dispatchEvent(new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING));
        } catch(IOException i) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, i);
        }  catch(Exception e) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    //Signal Server Input
    public void serverInput()
    {
        while(!clientMsg.equals("Done"))
        {
            try
            {
                clientMsg = serverInput.readLine();
                serverOutput.writeUTF(clientMsg);
//                clientMsg = clientOutput.readLine();
//                if(clientMsg != null)
//                {
//                    System.out.println("Client: " + clientMsg);
//                    output.writeUTF(clientMsg);
//                }
            } catch(IOException i) {
                System.out.println(i);
            }
        }
        closeConnection();
    }
    
    //Signal Server Input for the GUI
    public void GUIserverInput(javax.swing.JTextArea theTextArea, String text, javax.swing.JFrame theFrame)
    {
        if(text.equals("Done"))
        {
            GUIcloseConnection(theTextArea, theFrame);
        }
        try
        {
            serverOutput.writeUTF(text);
            theTextArea.append("Client: " + text + "\n\n");
        } catch(IOException i) {
            System.out.println(i);
        }
        
    }
    
    public String getClientMsg(){return clientMsg;}
    
    public void setClientMsg(String theClientMsg){ clientMsg=theClientMsg;}
    
    public int getPort(){return port;}
    
    public void setPort(int thePort){ port=thePort;}
    
    public String getAddress(){return address;}
    
    public void setAddress(String theAddress){ address=theAddress;}
    
    public static void main(String args[])
    {
        SocketClient  socketClient = new SocketClient();
        socketClient.setAddress("127.0.0.1");
        socketClient.setPort(Integer.parseInt("1200"));
        socketClient.startConnection();
        socketClient.serverInput();
    }
    
}
