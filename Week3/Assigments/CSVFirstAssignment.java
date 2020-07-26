import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * 1. Write a method named tester that will create your CSVParser and call each of the methods below in parts 2, 3, 4, and 5
 *
 * 2. Write a method named countryInfo that has two parameters, parser is a CSVParser and country is a String.
 * This method returns a string of information about the country
 * or returns “NOT FOUND” if there is no information about the country.
 * The format of the string returned is the country, followed by “: “,
 * followed by a list of the countries’ exports, followed by “: “, followed by the countries export value
 *
 * 3. Write a void method named listExportersTwoProducts that has three parameters,
 * parser is a CSVParser, exportItem1 is a String and exportItem2 is a String.
 * This method prints the names of all the countries that have both exportItem1 and exportItem2 as export items
 *
 * 4. Write a method named numberOfExporters, which has two parameters, parser is a CSVParser, and exportItem is a String.
 * This method returns the number of countries that export exportItem
 *
 * 5. Write a void method named bigExporters that has two parameters, parser is a CSVParser,
 * and amount is a String in the format of a dollar sign, followed by an integer number with a comma separator every three digits from the right.
 * An example of such a string might be “$400,000,000”.
 * This method prints the names of countries and their Value amount for all countries
 * whose Value (dollars) string is longer than the amount string.
 * You do not need to parse either string value as an integer, just compare the lengths of the strings
 *
 * @author (Sajal Agrawal)
 * @since (14/07/2020)
 */

public class CSVFirstAssignment {

    public String countryInfo(CSVParser parser, String country){
        String res;
        for(CSVRecord record : parser){
            String c = record.get("Country");
            if (c.equals(country)){
                res = country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
                return res;
            }
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2))
                System.out.println(record.get("Country"));
            }
        }
    
    public void testListExportersTwoProducts(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Countries that export cotton and flowers: ");
        listExportersTwoProducts(parser, "cotton", "flowers");
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if (exports.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    
    public void testNumberOfExporters(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("No of Countries that export coco: " + numberOfExporters(parser, "cocoa"));
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            String recAmount = record.get("Value (dollars)");
            if (recAmount.length() > amount.length())
                System.out.println(record.get("Country") + " " + recAmount);
        }
    }
    
    public void testBigExporters(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Countries that export > : ");
        bigExporters(parser, "$999,999,999,999");
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Country Info for Germany: " + countryInfo(parser, "Germany"));
        parser = fr.getCSVParser();
        System.out.println("List of exporters who exports gold and diamonds: ");
        listExportersTwoProducts(parser, "gold", "diamonds");
        parser = fr.getCSVParser();
        System.out.println("No of exporters for gold are " + numberOfExporters(parser, "gold"));
        parser = fr.getCSVParser();
        System.out.println("List of economies that export more than $999,999,999");
        bigExporters(parser, "$999,999,999");
    }
    
    public void quiz(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("List of exporters who exports fish and nuts: ");
        listExportersTwoProducts(parser, "fish", "nuts");
        System.out.println("No of exporters for sugar are " + numberOfExporters(parser, "sugar"));
        System.out.println("Country Info for Nauru: " + countryInfo(parser, "Nauru"));
        System.out.println("List of economies that export more than $999,999,999,999");
        bigExporters(parser, "$999,999,999,999");
    }
    
    public static void main(String args[]){
        CSVFirstAssignment csv = new CSVFirstAssignment();
        csv.tester();
        // csv.quiz();
    }
}