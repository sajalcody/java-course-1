import java.io.*;

/**
 * Part 3: Problem Solving with Strings
 * 1. Create a new Java Class named Part3 in the StringsFirstAssignments project.
 *
 * 2. Write the method named twoOccurrences that has two String parameters named stringa and stringb.
 * This method returns true if stringa appears at least twice in stringb, otherwise it returns false.
 * For example, the call twoOccurrences(“by”, “A story by Abby Long”) returns true as there are two occurrences of “by”,
 * the call twoOccurrences(“a”, “banana”) returns true as there are three occurrences of “a” so “a” occurs at least twice,
 * the call twoOccurrences(“atg”, “ctgtatgta”) returns false as there is only one occurence of “atg”.
 *
 * 3. Write the void method named testing that has no parameters.
 * This method should call twoOccurrences on several pairs of strings and print the strings and the result of calling twoOccurrences (true or false) for each pair.
 * Be sure to test examples that should result in true and examples that should result in false.
 *
 * 4. Write the method named lastPart that has two String parameters named stringa and stringb.
 * This method finds the first occurrence of stringa in stringb, and returns the part of stringb that follows stringa.
 * If stringa does not occur in stringb, then return stringb.
 * For example, the call lastPart(“an”, “banana”) returns the string “ana”, the part of the string after the first “an”.
 * The call lastPart(“zoo”, “forest”) returns the string “forest” since “zoo” does not appear in that word.
 *
 * 5. Add code to the method testing to call the method lastPart with several pairs of strings.
 * For each call print the strings passed in and the result.
 * For example, the output for the two calls above might be:
 * The part of the string after an in banana is ana.
 * The part of the string after zoo in forest is forest.
 *
 * @author (Sajal Agrawal)
 * @version (10/07/2020)
 */

public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int occ1 = stringb.indexOf(stringa);
        if (occ1 == -1)
            return false;
        int occ2 = stringb.indexOf(stringa, occ1 + stringa.length());
        if (occ2 == -1)
            return false;
        return true;
    }
    
    public void testingTwoOccurrences(){
        String As[] = new String[5];
        String Bs[] = new String[5];
        As[0]="by"; Bs[0]="A story by Abby Long";
        As[1]="a"; Bs[1]="banana";
        As[2]="atg"; Bs[2]="ctgtatgta";
        As[3]=""; Bs[3]="";
        for(int i = 0; i < 4; i++){
            boolean occurTwice = twoOccurrences(As[i], Bs[i]);
            if (occurTwice)
                System.out.println("\"" + As[i]+"\" occurs twice in \""+Bs[i]+" \"");
            else
                System.out.println("\"" + As[i]+"\" does not occur twice in \""+Bs[i]+" \"");
            }
        }
        
    public String lastPart(String stringa, String stringb){
        int posa = stringb.indexOf(stringa);
        if(posa == -1)
            return stringb;
        return stringb.substring(posa + stringa.length());
    }
    
    public void testingLastPart(){
        String As[] = new String[5];
        String Bs[] = new String[5];
        As[0]="an"; Bs[0]="banana";
        As[1]="zoo"; Bs[1]="";
        As[2]=""; Bs[2]="";
        for(int i = 0; i < 3; i++)
           System.out.println("Last Part of \"" + As[i] + "\" in \"" + Bs[i] + "\" is " + lastPart(As[i], Bs[i]));
        }
   
    public static void main(String args[]){
        Part3 p = new Part3();
        p.testingTwoOccurrences();
        p.testingLastPart();
    }
}
