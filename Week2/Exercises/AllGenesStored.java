import edu.duke.*;

public class AllGenesStored{
    public StorageResource getAllGenes(String dna){
        StorageResource geneList = new StorageResource();
        AllGenes ag = new AllGenes();
        String gene;
        int startIndex = 0;
        while (true){
            gene = ag.findGene(dna, startIndex);
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
        AllGenesStored t = new AllGenesStored();
        t.testGetAllGenes();
    }
}