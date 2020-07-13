package StringThirdAssignments;

import StringThirdAssignments.*;
import edu.duke.*;
import java.io.*;

/**
 * 1. Write the void method processGenes that has one parameter sr, which is a StorageResource of strings.
 * a. print all the Strings in sr that are longer than 9 characters
 * b. print the number of Strings in sr that are longer than 9 characters
 * c. print the Strings in sr whose C-G-ratio is higher than 0.35
 * d. print the number of strings in sr whose C-G-ratio is higher than 0.35
 * e. print the length of the longest gene in sr
 *
 * 2. Write a method testProcessGenes.
 *
 * 3. Download the file brca1line.fa from the DukeLearnToProgram Project Resources page.
 * Modify your processGenes method so that it prints all the Strings that are longer than 60 characters
 * and prints the number of Strings that are longer than 60 characters
 * Modify the method testProcessGenes to call processGenes with a StorageResource of the genes found in the file brca1line.fa.
 *
 * @author (Sajal Agrawal)
 * @since (12/07/2020)
*/

public class Part3 {
    
    public void processGenes(StorageResource sr){
        System.out.println("Strings longer than 60 characters");
        int count9 = 0;
        for (String s: sr.data()){
            if (s.length() > 60){
                System.out.println(s);
                count9++;
            }
        }
        System.out.println("No of strings longer than 60 characters " + count9);
        Part2 p = new Part2();
        int countcgr = 0;
        System.out.println("Strings with C-G-ratio > 0.35");
        for (String s: sr.data()){
            double cgr = p.cgRatio(s);
            if (cgr > 0.35){
                System.out.println(s);
                countcgr++;
            }
        }
        System.out.println("No of Strings with C-G-ratio > 0.35 are " + countcgr);
        String longestGene = "";
        for (String s: sr.data()){
            if (s.length() > longestGene.length())
                longestGene = s;
        }
        System.out.println("Longest Gene: " + longestGene + "\nwith length " + longestGene.length());
    }
    
    public void testProcessGenes(){
        StorageResource sr = new StorageResource();
        sr.add("ATGGTCTAATAG");
        sr.add("CCTGGCTAACGCTAA");
        sr.add("ATGTAA");
        Part1 p = new Part1();
        Part2 p2 = new Part2();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String dna = (fr.asString()).toUpperCase();
            System.out.println("read " + dna.length() + " characters");
            StorageResource allGenes = p.getAllGenes(dna);
            System.out.println("Total no of genes: " + allGenes.size());
            System.out.println("No of CTG: " + p2.countCTG(dna));
            processGenes(allGenes);
        }
    }
    
    public static void main(String args[]){
        Part3 p = new Part3();
        p.testProcessGenes();
        
    }
}