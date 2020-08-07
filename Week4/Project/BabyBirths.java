import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * Mini Project
 * 
 * @author (Sajal Agrawal)
 * @since (27-07-2020)
 */

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0, totalNames = 0;
        int totalBoys = 0, totalBoysNames = 0;
        int totalGirls = 0, totalGirlsNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames++;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoysNames++;
            }
            else {
                totalGirls += numBorn;
                totalGirlsNames++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("total Names = " + totalNames);
        System.out.println("total girls names = " + totalGirlsNames);
        System.out.println("total boys names = " + totalBoysNames);
    }

    public void testTotalBirths () {
        FileResource fr = new FileResource();
        //FileResource fr = new FileResource("..\\data\\us_babynames_by_year\\yob2014.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        String file = "..\\data\\us_babynames_by_year\\yob" + Integer.toString(year) + ".csv";
        int rank = 1;
        FileResource fr = new FileResource(file);
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender) && rec.get(0).equals(name))
                return rank;
            else if (rec.get(1).equals(gender))
                rank++;
            }
        return -1;
    }
    
     public int getRank(String name, String gender, FileResource fr){
        int rank = 1;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender) && rec.get(0).equals(name))
                return rank;
            else if (rec.get(1).equals(gender))
                rank++;
            }
        return -1;
    }
    
    public void testGetRank(){
        int rank = getRank(2012, "Mason", "F");
        if (rank == -1)
            System.out.println("Mason not found in 2012 as a girl");
        else
            System.out.println("Mason as a girl was ranked " + rank + " in 2012");
        rank = getRank(2012, "Dyana", "M");
        if (rank == -1)
            System.out.println("Dyana not found in 2012 as a boy");
        else
            System.out.println("Dyana as a boy was ranked " + rank + " in 2012");
        rank = getRank("Mason", "M", new FileResource("..\\data\\us_babynames_test\\yob2012short.csv"));
        if (rank == -1)
            System.out.println("Mason not found in 2012 short as a boy");
        else
            System.out.println("Mason as a boy was ranked " + rank + " in 2012 short");
    }
    
    public String getName(int year, int rank, String gender){
        String file = "..\\data\\us_babynames_by_year\\yob" + Integer.toString(year) + ".csv";
        int currRank = 1;
        FileResource fr = new FileResource(file);
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender) && currRank == rank)
                return rec.get(0);
            else if (rec.get(1).equals(gender))
                currRank++;
            }
        return "NO NAME";
    }
    
     public String getName(int rank, String gender, FileResource fr){
        int currRank = 1;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender) && currRank == rank)
                return rec.get(0);
            else if (rec.get(1).equals(gender))
                currRank++;
            }
        return "NO NAME";
    }
    
     public void testGetName(){
        String name = getName(2012, 2300, "F");
        System.out.println("For rank 2300, gender as a girl in year 2012, name is " + name);
        name = getName(2300, "F",  new FileResource("..\\data\\us_babynames_test\\yob2012short.csv"));
        System.out.println("For rank 2300, gender as a girl in year 2012 short, name is " + name);
    }
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        return getName(newYear, rank, gender);
    }
    
    public void testWhatIsNameInYear(){
        System.out.println("Isabella born in 2012 would be " +  whatIsNameInYear("Isabella", 2012, 2014, "F") + " if she was born in 2014.");
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRankSoFar = -1, year = -1;
        for (File f : dr.selectedFiles()){
            int rank = getRank(name, gender, new FileResource(f));
            System.out.println(f.getName() + rank + " " + highestRankSoFar);
            if (rank > highestRankSoFar){
                year = Integer.parseInt((f.getName()).substring(3, 7));
                highestRankSoFar = rank;
            }
        }
        return year;
    }
    
    public void testYearOfHighestRank(){
        System.out.println("Highest rank of Mason as a boy in selected files was in year " + yearOfHighestRank("Mason", "M"));
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double sum = 0.0;
        int occurrence = 0;
        boolean found = false;
        for (File f : dr.selectedFiles()){
            int rank = getRank(name, gender, new FileResource(f));
            occurrence++;
            //sum = sum + (rank == -1 ? 0 : rank);
            if (rank != -1){
                found = true;
                sum += rank;
            }
        }
        if (found)
            return sum/occurrence;
        return -1.0;
    }
    
    public void testGetAverageRank(){
        System.out.println("Average rank of Mason as a boy in selected files was in year " + getAverageRank("Mason", "M"));
        System.out.println("Average rank of Jacob as a boy in selected files was in year " + getAverageRank("Jacob", "M"));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int total = 0;
        String file = "..\\data\\us_babynames_by_year\\yob" + Integer.toString(year) + ".csv";
        FileResource fr = new FileResource(file);
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender) && rec.get(0).equals(name))
                return total;
            else if (rec.get(1).equals(gender))
                total += Integer.parseInt(rec.get(2));
            }
        return total;
    }
    
    public int getTotalBirthsRankedHigher(String name, String gender, FileResource fr){
        int total = 0;
        for (CSVRecord rec: fr.getCSVParser(false)){
            if (rec.get(1).equals(gender) && rec.get(0).equals(name))
                return total;
            else if (rec.get(1).equals(gender))
                total += Integer.parseInt(rec.get(2));
            }
        return total;
    }
    
    void testGetTotalBirthsRankedHigher(){
        System.out.println("Total Births higher than Ethan as a boy in year 2012 test " 
            + getTotalBirthsRankedHigher("Ethan", "M", new FileResource("..\\data\\us_babynames_test\\yob2012short.csv")));
    }
    
    void quiz(){
        /*
        testTotalBirths();
        testTotalBirths();
        System.out.println("Rank of girl Emily in 1960 " + getRank(1960, "Emily", "F"));
        System.out.println("Rank of boy Frank in 1971 " + getRank(1971, "Frank", "M"));
        System.out.println("Name of girl at rank 350 in 1980 " + getName(1980, 350, "F"));
        System.out.println("Name of boy at rank 450 in 1982 " + getName(1982, 450, "M"));
        System.out.println("Susan born in 1972 would be " +  whatIsNameInYear("Susan", 1972, 2014, "F") + " if she was born in 2014.");
        System.out.println("Owen born in 1974    would be " +  whatIsNameInYear("Owen", 1974, 2014, "M") + " if she was born in 2014.");
        */
        System.out.println("Highest rank of Genevieve as a girl in selected files was in year " + yearOfHighestRank("Genevieve", "F"));
        System.out.println("Highest rank of Mich as a boy in selected files was in year " + yearOfHighestRank("Mich", "M"));
        /*
        System.out.println("Average rank of Susan as a girl in selected files was in year " + getAverageRank("Susan", "F"));
        System.out.println("Average rank of Robert as a boy in selected files was in year " + getAverageRank("Robert", "M"));
        System.out.println("Total Births higher than Emily as a girl in year 1990 test " 
            + getTotalBirthsRankedHigher(1990, "Emily", "F"));
        System.out.println("Total Births higher than Drew as a boy in year 1990 test " 
            + getTotalBirthsRankedHigher(1990, "Drew", "M")); */
    }
}
