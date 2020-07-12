package Assignments.ManyGenes;

/** Part 2: HowMany - Finding Multiple Occurrences
 * This assignment will write a method to determine how many occurrences of a string appear in another string.
 * 1. Create a new Java Class named Part2 in the StringsSecondAssignments project.
 *
 * 2. Write the method named howMany that has two String parameters named stringa and stringb.
 * - This method returns an integer indicating how many times stringa appears in stringb, where each occurrence of stringa must not overlap with another occurrence of it. 
 * - For example, the call howMany(“GAA”, “ATGAACGAATTGAATC”) returns 3 as GAA occurs 3 times.
 * - The call howMany(“AA”, “ATAAAA”) returns 2. Note that the AA’s found cannot overlap.
 *
 * 3.Write the void method testHowMany has no parameters. 
 * - Add code in here to call howMany with several examples and print the results. 
 * - Think carefully about what types of examples would be good to test to make sure your method works correctly.
 *
 * @author (Sajal Agrawal)
 * @version (12/07/2020)
 */

public class Part2{
    public int howMany(String stringa, String stringb){
        int occurrences = 0;
        int currIndex = 0;
        while (true){
            currIndex = stringb.indexOf(stringa, currIndex);
            if (currIndex == -1)
                return occurrences;
            occurrences++;
            currIndex = currIndex + stringa.length();
        }
    }

    public void testHowMany(){
        String stringa = "GAA", stringb = "ATGAACGAATTGAATC";
        System.out.println(stringa+" occurs " + howMany(stringa, stringb) + " times in " + stringb);
        stringa = "AA";
        stringb = "ATAAAA";
        System.out.println(stringa+" occurs " + howMany(stringa, stringb) + " times in " + stringb);
    }
    public static void main(String args[]){
        Part2 t = new Part2();
        t.testHowMany();
    }
}