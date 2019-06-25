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
            theOutput = "Toy code\n";
        }
        if(count == 1){
            theOutput = "Toy name\n";
        }
        else if(count == 2){
            theOutput = "Toy Description\n";
        }
        else if(count == 3){
            theOutput = "Toy Price\n";
        }
        else if(count == 4){
            theOutput = "Date of Manufacture\n";
        }
        else if(count == 5){
            theOutput = "Batch Number\n";
        }
        else if(count == 6){
            theOutput = "Company Name\n";
        }
        else if(count == 7){
            theOutput = "Street Adress\n";
        }
        else if(count == 8){
            theOutput = "Zip Code\n";
        }
        else if(count == 9){
            theOutput = "Country\n";
        }
        else if(count == 10){
            theOutput = "Message\n";
        }
        else if(count == 11){
            theOutput = "Thank You for Your Input.\n";
        }        
        count = count + 1;
        return theOutput;
    }
    
}
