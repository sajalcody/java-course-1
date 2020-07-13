package StringThirdAssignments;

/**
 * 1. Write the method cgRatio that has one String parameter dna,
 * and returns the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA.
 * For example if the String were “ATGCCATAG” then cgRatio would return 4/9 or .4444444
 *
 * 2. Write a method countCTG that has one String parameter dna,
 * and returns the number of times the codon CTG appears in dna.
 * 
 * @author (Sajal Agrawal)
 * @since (12/07/2020)
*/

public class Part2 {
    public double cgRatio(String dna){
        int l = dna.length();
        int cs = 0, gs = 0;
        for (int i = 0; i < l; i++){
            switch(dna.charAt(i)){
                case 'C': cs++; break;
                case 'G': gs++; break;
            }
        }
        return (cs + gs)/(double)l;
    }
    
    public int countCTG(String dna){
        int currIndex = 0;
        int count = 0;
        while (true){
            currIndex = dna.indexOf("CTG", currIndex);
            if (currIndex == -1)
                return count;
            count++;
            currIndex = currIndex + 3;
        }
    }
    
    public void testCGRatio(){
        String dna = "ATGCCATAG";
        System.out.println("CG Ratio of " + dna + " is " + cgRatio(dna));
    }
    
    public void testCountCTG(){
        String dna = "ATGCTGCCATAGCTG";
        System.out.println("CTG is present " + countCTG(dna) + " time(s) in " + dna);
    }
    
    public static void main(String args[]){
        Part2 p = new Part2();
        p.testCGRatio();
        p.testCountCTG();
    }
}