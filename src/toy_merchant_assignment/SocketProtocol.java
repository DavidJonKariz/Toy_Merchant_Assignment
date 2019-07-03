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
            theOutput = "Submit a Toy code\n";
        }
        if(count == 1){
            theOutput = "Submit a Toy name\n";
        }
        else if(count == 2){
            theOutput = "Submit the Toy Description\n";
        }
        else if(count == 3){
            theOutput = "Submit the Toy Price\n";
        }
        else if(count == 4){
            theOutput = "Submit the Date of Manufacture\n";
        }
        else if(count == 5){
            theOutput = "Submit the Batch Number\n";
        }
        else if(count == 6){
            theOutput = "Submit the Company Name\n";
        }
        else if(count == 7){
            theOutput = "Submit the Street Address\n";
        }
        else if(count == 8){
            theOutput = "Submit the Zip Code\n";
        }
        else if(count == 9){
            theOutput = "Submit the Country of Location\n";
        }
        else if(count == 10){
            theOutput = "Submit a Message\n";
        }
        else if(count == 11){
            theOutput = "Thank You for Your Input.\n";
        }        
        count = count + 1;
        return theOutput;
    }
    
}
