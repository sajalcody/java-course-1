package Assignments.Debugging;

public class Part1 {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            // if (index == -1) { // -> BUG 1
            if (index == -1 || index > input.length() - 4) {
                break;
            }
            // System.out.println("index+1: " + (index+1) + "index+4:" + (index+4));
            System.out.println("index: " + index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            // index = input.indexOf("abc", index+4); // -> BUG 2
            index = input.indexOf("abc", index+3);
            // System.out.println("index after updating: " + index);
        }
    }
    public void test() {
        // findAbc("abcd");
        // findAbc("abcdabc");
        //       01234567890123456789012345678901234567890123
        // findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        findAbc("abcabcabcabca");
    }
    public static void main(String args[]){
        Part1 p = new Part1();
        p.test();
    }
}