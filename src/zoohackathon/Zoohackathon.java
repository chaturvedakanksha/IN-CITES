/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoohackathon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Akanksha
 */
import java.util.Scanner;

public class Zoohackathon {

    /**
     * @param args the command line arguments
     */
    public static void readDataLineByLine(String file) 
    { 
        FileInputStream inputStream = null;
        Scanner sc = null;
  
        try { 
  
            // Create an object of filereader 
            // class with CSV file as a parameter. 
            //FileReader filereader = new FileReader(file); 
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");
            
            // create FileWriter object with file as parameter 
            FileWriter outputfile = new FileWriter("Result.csv"); 
            
            outputfile.append("Scientific Name");
            outputfile.append("\n");
            long i=0;
            while (sc.hasNextLine()) { 
                i++;
                
                String row = sc.nextLine();
                if(i>=5885 || i==1){
                    if(row!=null){
                        String[] data = row.split(",");
                        if(data.length>=9){
                            if(data[1].equals("Plantae")){
                                outputfile.append(data[9]);
                                outputfile.append("\n");
                            }
                        }
                    }
                }
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
           // csvReader.close();
            outputfile.close();
            MedicinalPlant m=new MedicinalPlant();
            m.createMedicinalPlantList("Result.csv");
           // m.readToGetMedicinalPlant("Result_2.csv");
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
    
    public static void main(String[] args) {
        // TODO code application logic here
       // String file="C:\\Users\\Akanksha\\Desktop\\Zoohackathon\\cites_listings.csv";
        //readDataLineByLine(file);
        new Dashboard();
    }
    
}
