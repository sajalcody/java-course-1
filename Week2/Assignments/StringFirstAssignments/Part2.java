import java.io.*;

/**
 * Part 2: Finding a Gene - Using the Simplified Algorithm Reorganized
 * This assignment will determine if a DNA strand has a gene in it by using the simplified algorithm from the lesson, but organizing the code in a slightly different way.
 * You will modify the method findSimpleGene to have three parameters, one for the DNA string, one for the start codon and one for the stop codon.
 *
 * 1. Create a new Java Class named Part2 in the StringsFirstAssignments project.
 *
 * 2. Copy and paste the two methods findSimpleGene and testSimpleGene from the Part1 class into the Part2 class.
 *
 * 3. The method findSimpleGene has one parameter for the DNA string named dna.
 * Modify findSimpleGene to add two additional parameters, one named startCodon for the start codon and one named stopCodon for the stop codon.
 *
 * 4. Modify the findSimpleGene method to work with DNA strings that are either all uppercase letters such as “ATGGGTTAAGTC” or all lowercase letters such as “gatgctataat”.
 * Calling findSimpleGene with “ATGGGTTAAGTC” should return the answer with uppercase letters, the gene “ATGGGTTAA”,
 * and calling findSimpleGene with “gatgctataat” should return the answer with lowercase letters, the gene “atgctataa”.
 * If dna is the string “ATGTAA” then dna.toLowerCase() results in the string “atgtaa”.
 *
 * @author (Sajal Agrawal)
 * @version (10/07/2020)
 */

public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String endCodon){
        String simpleDna = dna.toUpperCase();
        int startPos = simpleDna.indexOf(startCodon);
        if (startPos == -1)
            return "";
        int endPos = simpleDna.indexOf(endCodon, startPos + 3);
        if (endPos == -1)
            return "";
        int geneLength = endPos - startPos + 3;
        if (geneLength % 3 == 0)
            return dna.substring(startPos, startPos + geneLength);
        return "";
    }
    
    public void testSimpleGene(){
        String dnas[] = new String[5];
        String genes[] = new String[5];
        String startCodon = "ATG";
        String endCodon = "TAG";
        dnas[0]="CCCATGGGGTTTAAATAATAATAGGAGAGAGAGAGAGAGTTT"; genes[0]="ATGGGGTTTAAATAATAATAG";
        dnas[1]="ATGCCTAG"; genes[1]="";
        dnas[2]="ATGCCCTAG"; genes[2]= "ATGCCCTAG";
        dnas[3]="cccatggggtttaaataataataggagagagagagagagttt"; genes[3]="atggggtttaaataataatag";
        for (int i = 0; i < 4; i++){
            System.out.println("DNA: " + dnas[i]);
            String g = findSimpleGene(dnas[i], startCodon, endCodon);
            if (g.equals(genes[i]))
                System.out.println("You method works: " + g);
            else
                System.out.println("Your gene " + g + " does not match actual gene " + genes[i]);
            }
        }
    public static void main(String args[]){
        Part2 p = new Part2();
        p.testSimpleGene();
    }
}
