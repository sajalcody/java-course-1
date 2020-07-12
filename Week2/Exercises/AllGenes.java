public class AllGenes {
    public int findStopCodon(String dnaStr, int start, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, start + 3);
        while (currIndex != -1){
            if ((currIndex - start) % 3 == 0)
                return currIndex;
        currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
        }
    return -1;
    }

    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1)
            return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex;
        if (taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex))
            minIndex = tagIndex;
        else
            minIndex = taaIndex;
        if (minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex))
            minIndex = tgaIndex;
        if (minIndex == -1)
            return "";
        return dna.substring(startIndex, minIndex + 3);
    }

    public void printAllGenes(String dna){
        String gene;
        int startIndex = 0;
        while (true){
            gene = findGene(dna, startIndex);
            if (gene.isEmpty())
                break;
            System.out.println("Gene: " + gene);
            // where = where + gene.length(); -> WRONG
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    public void testFindGene(){
        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println("DNA Strand is " + dna);
        printAllGenes(dna);

        dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA Strand is " + dna);
        printAllGenes(dna);

        dna = "CGATGGTTGATAAGCCTAAGCTAAA";
        System.out.println("DNA Strand is " + dna);
        printAllGenes(dna);
    }

    public static void main(String args[]){
        AllGenes t = new AllGenes();
        t.testFindGene();
    }
}