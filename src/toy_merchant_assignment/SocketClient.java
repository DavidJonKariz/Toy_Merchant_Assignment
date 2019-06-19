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
public class SocketClient {
    //establish socket connection details, such as input and output streams for reading and displaying
    private Socket socket = null;
    private BufferedReader serverInput = null;
//    BufferedReader clientOutput = null;
    private DataOutputStream serverOutput = null;
    
    // IP address and port number as arguments
    public SocketClient(String address, int port)
    {
        // establish connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connection Established");
            
            //input
//            serverInput = new BufferedReader(new InputStreamReader(System.in));
            serverInput = new BufferedReader(new InputStreamReader(System.in));
//            clientOutput = new BufferedReader(new InputStreamReader(System.in));            
//            input = new DataInputStream(System.in);
            
            //output
            serverOutput = new DataOutputStream(socket.getOutputStream());
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        } catch(UnknownHostException u) {
            System.out.println(u);
        } catch(IOException i) {
            System.out.println(i);
        }
        
        //reads message from input
        String serverMsg = "";
        String clientMsg = "";
        
        //Signal end of Input
        while(!serverMsg.equals("Done"))
        {
            try
            {
                serverMsg = serverInput.readLine();
//                System.out.print("Client ");
                serverOutput.writeUTF(serverMsg);
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
        
        // connection closed
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
    
    public static void main(String args[])
    {
        SocketClient  socketClient = new SocketClient("127.0.0.1", 1200);
    }
    
}
