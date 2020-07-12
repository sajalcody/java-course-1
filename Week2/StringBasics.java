public class StringBasics {
    public static void main(String[] args){
        String s = "dukeprogramming";

        //substring method
        // Why we mention endIndex as 1 extra?
        // So length of resultant string is endIndex-beginIndex
        System.out.println(s.substring(4,7)); // "pro"
        int index = s.indexOf("program");
        String sub = s.substring(index, index+11);
        System.out.println(sub);
        sub = s.substring(index, index+12);
        System.out.println(sub);
    }
}