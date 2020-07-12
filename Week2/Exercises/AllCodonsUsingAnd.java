public class AllCodonsUsingAnd {
    public int findStopCodon(String dnaStr, int start, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, start + 3);
        while (currIndex != -1){
            if ((currIndex - start) % 3 == 0)
                return currIndex;
        currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
        }
    return -1;
    }

    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
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

    public void testFindGene(){
        String dna = "AATGCGTAATTAATCG";
        System.out.println("DNA Strand is " + dna);
        String gene = findGene(dna);
        System.out.println("Gene is " + gene);

        dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA Strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is " + gene);

        dna = "CGATGGTTGATAAGCCTAAGCTAAA";
        System.out.println("DNA Strand is " + dna);
        gene = findGene(dna);
        System.out.println("Gene is " + gene);
    }

    public static void main(String args[]){
        AllCodonsUsingAnd t = new AllCodonsUsingAnd();
        t.testFindGene();
    }
}