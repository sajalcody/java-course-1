package Assignments.ManyGenes;

/** Part 1: Finding many Genes
 * 1. Write the method findStopCodon that has three parameters,
 * - a String parameter named dna,
 * - an integer parameter named startIndex that represents where the first occurrence of ATG occurs in dna,
 * - a String parameter named stopCodon.
 * This method returns the index of the first occurrence of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex.
 * If there is no such stopCodon, this method returns the length of the dna strand.
 *
 * 2. Write the void method testFindStopCodon
 * that calls the method findStopCodon with several examples
 * and prints out the results to check if findStopCodon is working correctly
 *
 * 3. Write the method findGene that has one String parameter dna, representing a string of DNA. In this method you should do the following:
 * Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.
 * Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
 * Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
 * Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
 * Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away.
 * If there is no valid stop codon and therefore no gene, return the empty string.
 *
 * 4. Write the void method testFindGene that has no parameters.
 * Print the DNA string.
 * Calculate the gene by sending this DNA string as an argument to findGene.
 * If a gene exists following our algorithm above, then print the gene, otherwise print the empty string.
 *
 * 5. Write the void method printAllGenes that has one String parameter dna, representing a string of DNA.
 * In this method you should repeatedly find genes and print each one until there are no more genes
 *
 * @author (Sajal Agrawal)
 * @version (12/07/2020)
 */

public class Part1 {
    public int findStopCodon(String dnaStr, int start, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, start + 3);
        while (currIndex != -1){
            if ((currIndex - start) % 3 == 0)
                return currIndex;
        currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
        }
    return dnaStr.length(); // To ensure, I get maximum value
    }

    public void testFindStopCodon(){
        String dna = "ATGTCGTAATGGTAG";
        String stopCodon = "TAA";
        int index = findStopCodon(dna, 0, stopCodon);
        if (index == dna.length())
            System.out.println("No Gene with stopCodon " + stopCodon + " Found in " + dna);
        else
            System.out.println("Dna: " + dna + "\nGene: " + dna.substring(0, index + 3));
        stopCodon = "TAG";
        index = findStopCodon(dna, 0, stopCodon);
        if (index == dna.length())
            System.out.println("No Gene with stopCodon " + stopCodon + " Found in " + dna);
        else
            System.out.println("Dna: " + dna + "\nGene: " + dna.substring(0, index + 3));
        stopCodon = "TGA";
        index = findStopCodon(dna, 0, stopCodon);
        if (index == dna.length())
            System.out.println("No Gene with stopCodon " + stopCodon + " Found in " + dna);
        else
            System.out.println("Dna: " + dna + "\nGene: " + dna.substring(0, index + 3));
    }

    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
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

    // OverLoading findGene to suit printAllGenes
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

    public void testFindGene(){
        String dna = "AATGCGTAATTAATCG";
        // System.out.println("DNA Strand is " + dna);
        // String gene = findGene(dna);
        // System.out.println("Gene is " + gene);

        // dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        // System.out.println("DNA Strand is " + dna);
        // gene = findGene(dna);
        // System.out.println("Gene is " + gene);

        // dna = "CGATGGTTGATAAGCCTAAGCTAAA";
        // System.out.println("DNA Strand is " + dna);
        // gene = findGene(dna);
        // System.out.println("Gene is " + gene);


        String assdna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("DNA Strand is " + assdna);
        String assgene = findGene(assdna);
        System.out.println("Gene is " + assgene);
    }

    public void printAllGenes(String dna){
        String gene;
        int startIndex = 0;
        System.out.println("DNA: " + dna);
        while (true){
            gene = findGene(dna, startIndex);
            if (gene.isEmpty())
                break;
            System.out.println("Gene: " + gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    public static void main(String args[]){
        Part1 part = new Part1();
        // part.testFindStopCodon();
        part.testFindGene();
        // part.printAllGenes("ATGTGCTGAATGTAACCATGCTCTGATAA");
    }
}