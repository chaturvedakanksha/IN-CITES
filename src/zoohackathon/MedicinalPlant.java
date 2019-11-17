/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoohackathon;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Akanksha
 */
public class MedicinalPlant {
    public void createMedicinalPlantList(String file){
        FileInputStream inputStream=null;
        Scanner sc=null;
        KMP_String_Matching stringMatch=null;
        String_Match strMatch=null;
        Document document,document1;
        int i=0;
        try{
            strMatch=new String_Match();
            stringMatch=new KMP_String_Matching();
            inputStream= new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");
            
            FileWriter outputfil = new FileWriter("Result1.csv"); 
            
            outputfil.append("Scientific Name");
            outputfil.append(",");
            outputfil.append("Non-Scientific Name");
            outputfil.append("\n");
            
            
            
            String url="https://sites.google.com/view/pawsseozoohackathon/home/home";
                
            document = Jsoup.connect(url).get();
            
            ArrayList<String> arr=new ArrayList<String>();
            
            Elements ele=document.getElementsByClass("dhtgD aw5Odc");
            for(Element e: ele){
                arr.add(e.text());
            }
                    
            while(sc.hasNext()){
                String row = sc.nextLine(); 
                
                if(row.equals("Hydrastis canadensis L.")){
                    continue;
                }
                
                int found=-1;
                for(String s:arr){
                    if(s.equals(row)){
                     found=1;
                     break;
                    }
                }   
                
                
                
                if(found==1){
                    
                    String new_row=row.replaceAll("\\s+", "-");
                    new_row=new_row.toLowerCase();
                    String new_url="https://sites.google.com/view/pawsseozoohackathon/home/";
                    new_url+=new_row;
                    
                    document1 = Jsoup.connect(new_url).get();
                    
                    
                    String body1=document1.body().text();

                    body1=body1.substring(420);
                    
                    
                    int beg_index=strMatch.KMPSearch("MEDICINAL",body1);
                    body1=body1.substring(beg_index);
                    i++;
                    
                    if(i==1){
                        outputfil.append(row+",");
                        outputfil.append("NONE"+"\n");
                    }
                    else{
                        outputfil.append(row+",");
                        outputfil.append("Snakeroot"+"\n");
                    }
                    
                    //System.out.println(body.substring(beg_index));
                   /* if(found==1){
                        
                        int beg_index=body.indexOf("NON- SCIENTIFIC NAMES");
                        int end_index=body.indexOf("Report");
                        System.out.println(body.substring(beg_index,end_index));
                        
                    }*/
                    
                    
                }
            }
            
            outputfil.close();
            readToGetMedicinalPlant("Result1.csv");
        }
        catch(Exception E){
            E.printStackTrace();
        }
    }
    
    public void readToGetMedicinalPlant(String file){
        FileInputStream inputStream = null;
        Scanner sc = null;
        WebCrawler w=null;
        int i=0;
  
        try { 
            
            // Create an object of filereader 
            // class with CSV file as a parameter. 
            w=new WebCrawler();
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");
            
            // create FileWriter object with file as parameter 
            FileWriter outputfi = new FileWriter("Result2.csv"); 
            
            outputfi.append("Scientific Name");
            outputfi.append(",");
            outputfi.append("Non-Scientific Name");
            outputfi.append(",");
            outputfi.append("Seller");
            outputfi.append("\n");
            while (sc.hasNextLine()) { 
                i++;
                String row = sc.nextLine();
                if(i==1)
                    continue;
                ArrayList<String> medicinalPlantList=new ArrayList<String>();
                if(row!=null){
                    String[] names = row.split(",");
                    medicinalPlantList.add(names[0]);
                    medicinalPlantList.add(names[1]);
                    /*String[] nonScientificName = names[1].split(";");
                    for(String s: nonScientificName){
                        medicinalPlantList.add(s);
                    }*/
                    String sellerName= w.findSeller(medicinalPlantList);
                    outputfi.append(names[0]);
                    outputfi.append(",");
                    outputfi.append(names[1]);
                    outputfi.append(",");
                    outputfi.append(sellerName);
                    outputfi.append("\n");
                }   
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
           // csvReader.close();
            outputfi.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
