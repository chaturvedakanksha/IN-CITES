/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoohackathon;

/**
 *
 * @author Akanksha
 */

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static java.lang.System.out;
import java.net.*;
import java.util.ArrayList;

/**
 *
 * scrapping for the e-commerce website
 */
public class WebCrawler {

    /**
     * @param args the command line arguments
     */
    public String findSeller(ArrayList<String> medicinalPlantList) {
        // TODO code application logic here
        Document document;
        KMP_String_Matching stringMatch=null;
	try {
            stringMatch=new KMP_String_Matching();
            for(String searchName: medicinalPlantList){
                String url="https://sites.google.com/view/pawszoohackathon/home";
                
                //connect to url
                document = Jsoup.connect(url).get();
                
                //printing our body now search for pattern
                String body=document.body().text();
                
                int found=stringMatch.KMPSearch(searchName, body);
                if(found==1){
                    searchName=searchName.toLowerCase();
                    String new_url="https://sites.google.com/view/pawszoohackathon/home/";
                    new_url+=searchName;
                    
                    //connect to url
                    document = Jsoup.connect(new_url).get();
                    System.out.println("Amazon");
                    return "Amazon";
                    
                }
                
                
            }
        }catch(Exception e){
            
            e.printStackTrace();
        }
        return null;
    }
}
			
                    
                        
                        
                        
                        //getting element by id
                       /* Element e_id=document.getElementById("<your element name> ");
                        out.println("Element BY id"+e_id.val());
                        
                        
                       //getting element by class 
                       Elements ele=document.getElementsByClass("<your class name>");
                       
                       //entire class name
                       for(Element e:ele)
                       {
                           out.println(e.val());
                       }
                       */
                       //getting outer details
			/*String title = document.title(); //Get title
                        
			print("  Title: " + title); //Print title.

                        
                       
                        
                       out.println("\n"+body);
                    
		} catch (Exception e) {
			e.printStackTrace();
		}
		print("done");
	}

	public static void print(String string) {
		System.out.println(string);
	}
}*/