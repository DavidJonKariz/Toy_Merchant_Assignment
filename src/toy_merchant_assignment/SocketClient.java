/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toy_merchant_assignment;

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
    // Customer Details
    ArrayList<String> info = new ArrayList<String>();
    
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
    public void GUIstartConnection(javax.swing.JTextArea theTextArea, javax.swing.JFrame theFrame)
    {
        try
        {
            socket = new Socket(address, port);
            appendText(theTextArea, "Connection Established\n");
            
            //input
            serverInput = new BufferedReader(new InputStreamReader(System.in));
            
            //output
            serverOutput = new DataOutputStream(socket.getOutputStream());
        } catch(UnknownHostException u) {
            System.out.println(u);
            JOptionPane.showMessageDialog(theFrame, "Error", u.getMessage(), JOptionPane.WARNING_MESSAGE);
        } catch(IOException i) {
            System.out.println(i);
            JOptionPane.showMessageDialog(theFrame,  "Error", i.getMessage(), JOptionPane.WARNING_MESSAGE);
        } catch(NumberFormatException nfe) {
            System.out.println(nfe);
            JOptionPane.showMessageDialog(theFrame,  "Error", nfe.getMessage(), JOptionPane.WARNING_MESSAGE);
        } catch(Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(theFrame,  "Error", e.getMessage(), JOptionPane.WARNING_MESSAGE);
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
        int option = JOptionPane.showConfirmDialog(theFrame, "Are you sure you want to exit?",
                "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(option == JOptionPane.YES_OPTION)
        {
            try
            {
                appendText(theTextArea, "Connection Closure\n");
                socket.close();
                serverInput.close();
                serverOutput.close();
                theFrame.dispatchEvent(new WindowEvent(theFrame, WindowEvent.WINDOW_CLOSING));
            } catch(IOException i) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, i);
                JOptionPane.showMessageDialog(theFrame, "Error", i.getMessage(), JOptionPane.WARNING_MESSAGE);
            }  catch(Exception e) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, e);
                JOptionPane.showMessageDialog(theFrame, "Error", e.getMessage(), JOptionPane.WARNING_MESSAGE);
            }
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
            detailsPopup(theFrame, info);
            GUIcloseConnection(theTextArea, theFrame);
        }
        try
        {
            serverOutput.writeUTF(text);
            info.add(text);
        } catch(IOException i) {
            System.out.println(i);
            JOptionPane.showMessageDialog(theFrame, "Error", i.getMessage(), JOptionPane.WARNING_MESSAGE);
        } catch(Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(theFrame, "Error", e.getMessage(), JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public void detailsPopup(javax.swing.JFrame theFrame, ArrayList<String> detailsInfo)
    {
        String details = "Customer Details Received\n";
        for(int i=0; i<detailsInfo.size(); i++)
        {
            if (i==0) {
                details += "Toy Code:  ";
                details += detailsInfo.get(i);
            } else if(i==1) {
                details += "Toy Name:  ";
            } else if(i==2) {
                details += "Toy Description:  ";
            } else if(i==3) {
                details += "Toy Price:  ";
            } else if(i==4) {
                details += "Date of Manufacture:  ";
            } else if(i==5) {
                details += "Batch Number:  ";
            } else if(i==6) {
                details += "Company Name:  ";
            } else if(i==7) {
                details += "Street Address:  ";
            } else if(i==8) {
                details += "Zip Code:  ";
            } else if(i==9) {
                details += "Country of Location:  ";
            } else if(i==10) {
                details += "Message:  ";
            }
            details += detailsInfo.get(i);
            details += "\n";
        }
        JOptionPane.showMessageDialog(theFrame, details);
    }
    
    public String getClientMsg(){return clientMsg;}
    
    public void setClientMsg(String theClientMsg){ clientMsg=theClientMsg;}
    
    public int getPort(){return port;}
    
    public void setPort(int thePort){ port=thePort;}
    
    public ArrayList<String> getInfo(){return info;}
    
    public void setInfo(ArrayList<String> infoArray){ info=infoArray;}
    
    public String getAddress(){return address;}
    
    public void setAddress(String theAddress){ address=theAddress;}
    
    public void appendText(javax.swing.JTextArea theTextArea, String str)
    {
        theTextArea.append(str);
        theTextArea.setCaretPosition(theTextArea.getText().length());
    }
    
    public static void main(String args[])
    {
        SocketClient  socketClient = new SocketClient();
        socketClient.setAddress("127.0.0.1");
        socketClient.setPort(Integer.parseInt("1200"));
        socketClient.startConnection();
        socketClient.serverInput();
    }
    
}
