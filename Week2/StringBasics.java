package Week2;

public class StringBasics {
    public static void main(String[] args){
        String s = "dukeprogramming";

        //substring method
        // Why we mention endIndex as 1 extra?
        // So length of resultant string is endIndex-beginIndex
        System.out.println(s.substring(4,7)); // "pro"

    }
}