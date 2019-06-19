/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toy_merchant_assignment;

/**
 *
 * @author djkar
 */
public class SocketProtocol {
    int count = 0;
    public String processInput(String theInput) {
        String theOutput = null;
        if(theInput == null){
            theOutput = "Toy code";
        }
        if(count == 1){
            theOutput = "Toy name";
        }
        else if(count == 2){
            theOutput = "Toy Description";
        }
        else if(count == 3){
            theOutput = "Toy Price";
        }
        else if(count == 4){
            theOutput = "Date of Manufacture";
        }
        else if(count == 5){
            theOutput = "Batch Number";
        }
        else if(count == 6){
            theOutput = "Company Name";
        }
        else if(count == 7){
            theOutput = "Street Adress";
        }
        else if(count == 8){
            theOutput = "Zip Code";
        }
        else if(count == 9){
            theOutput = "Country";
        }
        else if(count == 10){
            theOutput = "Message";
        }
        else if(count == 11){
            theOutput = "Bye.";
        }        
        count = count + 1;
        return theOutput;
    }
    
}
