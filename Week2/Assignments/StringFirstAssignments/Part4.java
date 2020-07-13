package StringFirstAssignments;

import java.io.*;
import edu.duke.*; // Required for URLResource

/**
 * Write a program that reads the lines from the file at this URL location,
 * http://www.dukelearntoprogram.com/course2/data/manylinks.html, and prints each URL on the page that is a link to youtube.com.
 * Assume that a link to youtube.com has no spaces in it and would be in the format (where [stuff] represents characters that are not verbatim): “http:[stuff]youtube.com[stuff]”
 *
 * 1. Create a new Java Class named Part4 in the StringsFirstAssignments project.
 *
 * 2. Use URLResource to read the file at http://www.dukelearntoprogram.com/course2/data/manylinks.html word by word.
 *
 * 3. For each word, check to see if “youtube.com” is in it. 
 * If it is, find the double quote to the left and right of the occurrence of “youtube.com” to identify the beginning and end of the URL.
 *
 * @author (Sajal Agrawal)
 * @version (10/07/2020)
 */

public class Part4 {
    public void getLinks(){
        int count = 0;
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s : ur.words()){
            //System.out.println(s);
            String simpleWord =s.toLowerCase();
            int pos = simpleWord.indexOf("youtube.com");
            if (pos == -1)
                continue;
            count++;
            int startQuote = s.lastIndexOf("\"", pos);
            int endQuote = s.indexOf("\"", pos+11);
            System.out.println(s.substring(startQuote+1, endQuote));
        }
        System.out.println("No of YouTube Links Found: " + count);
    }
    public static void main(String args[]){
        Part4 p = new Part4();
        p.getLinks();
    }
}
