public class TagFinderWhile{
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
            return "";
        int currIndex = dna.indexOf("TAA", startIndex + 3);
        while (currIndex != -1){
            if ((startIndex - currIndex) % 3 == 0){
                return dna.substring(startIndex, currIndex + 3);
            }
            currIndex = dna.indexOf("TAA", currIndex + 3);
        }
        return "";
    }

    public void testTagFinderWhile(){
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
        TagFinderWhile t = new TagFinderWhile();
        t.testTagFinderWhile();
    }
}