import java.io.*;

/**
 * 1. Write the method findSimpleGene that has one String parameter dna, representing a string of DNA. 
 * This method does the following:
 * - Finds the index position of the start codon “ATG”. If there is no “ATG”, return the empty string.
 * - Finds the index position of the first stop codon “TAA” appearing after the “ATG” that was found. 
 *   If there is no such “TAA”, return the empty string.
 * - If the length of the substring between the “ATG” and “TAA” is a multiple of 3, then return the substring that starts with that “ATG” and ends with that “TAA”.
 *
 * 2. Write the void method testSimpleGene that has no parameters. 
 * You should create five DNA strings. 
 * The strings should have specific test cases, such as: DNA with no “ATG”, DNA with no “TAA”, DNA with no “ATG” or “TAA”, DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene), and DNA with ATG, TAA and the substring between them is not a multiple of 3. 
 * For each DNA string you should:
 * - Print the DNA string.
 * - See if there is a gene by calling findSimpleGene with this string as the parameter. 
 *   If a gene exists following our algorithm above, then print the gene, otherwise print the empty string.
 * 
 * @author (Sajal Agrawal) 
 * @version (10/07/2020)
 */

public class Part1 {
    public String findSimpleGene (String dna){
        int startPos = dna.indexOf("ATG");
        if (startPos == -1)
            return "";
        int endPos = dna.indexOf("TAG", startPos + 3);
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
        dnas[0]="CCCATGGGGTTTAAATAATAATAGGAGAGAGAGAGAGAGTTT"; genes[0]="ATGGGGTTTAAATAATAATAG";
        dnas[1]="ATGCCTAG"; genes[1]="";
        dnas[2]="AAATGCCCTAACTAGATTAAGAAACC"; genes[2]= "ATGCCCTAG";
        for (int i = 0; i < 3; i++){
            System.out.println("DNA: " + dnas[i]);
            String g = findSimpleGene(dnas[i]);
            if (g.equals(genes[i]))
                System.out.println("You method works: " + g);
            else
                System.out.println("Your gene " + g + " does not match actual gene " + genes[i]);
            }
        }
    public static void main(String args[]){
        Part1 p = new Part1();
        p.testSimpleGene();
    }
}
