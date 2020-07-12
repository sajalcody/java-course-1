package Assignments.ManyGenes;

import Assignments.ManyGenes.Part1;
/**
 * Part 3: How Many Genes?
 * Write a program to count how many genes are in a strand of DNA.
 * Copy your methods from Part1 to find one gene and print all genes.
 * 1. Write the method named countGenes that has a String parameter named dna representing a string of DNA.
 * This method returns the number of genes found in dna.
 * For example the call countGenes(“ATGTAAGATGCCCTAGT”) returns 2, finding the gene ATGTAA first and then the gene ATGCCCTAG.
 *
 * 2. Write the void method named testCountGenes that has no parameters.
 * This method calls countGenes with many example strings and prints the result for each.
 *
 * @author (Sajal Agrawal)
 * @version (12/07/2020)
 */

public class Part3 {

    public int countGenes(String dna){
        Part1 p = new Part1();
        String gene;
        int count = 0;
        int startIndex = 0;
        while (true){
            gene = p.findGene(dna, startIndex);
            if (gene.isEmpty())
                return count;
            count++;
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    public void testCountGenes (){
        String dna = "ATGTGCTGAATGTAACCATGCTCTGATAA";
        System.out.println(countGenes(dna) + " gene(s) are found in DNA Strand " + dna);
        dna = "ATGTCTGAATGTAACCATGTCTGATAA";
        System.out.println(countGenes(dna) + " gene(s) are found in DNA Strand " + dna);
    }

    public static void main(String args[]){
        Part3 part = new Part3();
        part.testCountGenes();
    }
}