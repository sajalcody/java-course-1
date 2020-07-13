package StringThirdAssignments;

import edu.duke.*;
/**
 * 1. Create a new Java project named StringsThirdAssignments.
 *
 * 2. Create a new Java Class named Part1. Copy and paste the code from your Part1 class in your StringsSecondAssignments project into this class.
 *
 * 3. Make a copy of the printAllGenes method called getAllGenes.
 * Instead of printing the genes found, this method should create and return a StorageResource containing the genes found.
 * Remember to import the edu.duke libraries otherwise you will get an error message cannot find the class StorageResource.
 *
 * 4. Make sure you test your getAllGenes method.
 *
 * @author (Sajal Agrawal)
 * @since (12/07/2020)
*/

public class Part1 {

    public int findStopCodon(String dnaStr, int start, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, start + 3);
        while (currIndex != -1){
            if ((currIndex - start) % 3 == 0)
                return currIndex;
        currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
        }
    return dnaStr.length();
    }

    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1)
            return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length())
            return "";
        return dna.substring(startIndex, minIndex + 3);
    }
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        String gene;
        int startIndex = 0;
        while (true){
            gene = findGene(dna, startIndex);
            if (gene.isEmpty())
                return geneList;
            geneList.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }

    public void testGetAllGenes(){
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println("DNA Strand is " + dna);
        StorageResource sr = getAllGenes(dna);
        for (String s : sr.data())
            System.out.println("Gene: " + s);

        dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA Strand is " + dna);
        sr = getAllGenes(dna);
        for (String s : sr.data())
            System.out.println("Gene: " + s);
    }

    public static void main(String args[]){
        Part1 t = new Part1();
        t.testGetAllGenes();
    }
}