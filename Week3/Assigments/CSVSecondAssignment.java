import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * 1. Write a method named coldestHourInFile that has one parameter, a CSVParser named parser. 
 * This method returns the CSVRecord with the coldest temperature in the file and 
 * thus all the information about the coldest temperature, such as the hour of the coldest temperature. 
 * Write a void method named testColdestHourInFile() to test this method 
 * and print out information about that coldest temperature, such as the time of its occurrence.
 * NOTE: Sometimes there was not a valid reading at a specific hour, so the temperature field says -9999.
 * Ignore these bogus temperature values when calculating the lowest temperature.
 * 
 * 2. Write the method fileWithColdestTemperature that has no parameters. 
 * This method should return a string that is the name of the file from selected files that has the coldest temperature.
 * You should also write a void method named testFileWithColdestTemperature() to test this method. 
 * Note that after determining the filename, you could call the method coldestHourInFile to determine the coldest temperature on that day
 * 
 * 3. Write a method named lowestHumidityInFile that has one parameter, a CSVParser named parser. 
 * This method returns the CSVRecord that has the lowest humidity. If there is a tie, then return the first such record that was found.
 * Note that sometimes there is not a number in the Humidity column but instead there is the string “N/A”. 
 * This only happens very rarely. You should check to make sure the value you get is not “N/A” before converting it to a number.
 * Also note that the header for the time is either TimeEST or TimeEDT, depending on the time of year. 
 * You will instead use the DateUTC field at the right end of the data file to get both the date and time of a temperature reading.
 * You should also write a void method named testLowestHumidityInFile() to test this method
 * 
 * 4. Write the method lowestHumidityInManyFiles that has no parameters. 
 * This method returns a CSVRecord that has the lowest humidity over all the files. 
 * If there is a tie, then return the first such record that was found. 
 * Write a void method named testLowestHumidityInManyFiles() to test this method and to print the lowest humidity AND the time the lowest humidity occurred.
 * 
 * 5. Write the method averageTemperatureInFile that has one parameter, a CSVParser named parser. 
 * This method returns a double that represents the average temperature in the file. 
 * Write a void method named testAverageTemperatureInFile() to test this method. 
 * 
 * 6. Write the method averageTemperatureWithHighHumidityInFile that has two parameters, a CSVParser named parser and an integer named value. 
 * This method returns a double that represents the average temperature of only those temperatures when the humidity was greater than or equal to value. 
 * Write a void method named testAverageTemperatureWithHighHumidityInFile() to test this method
 * 
 * @author (Sajal Agrawal)
 * @since (26-07-2020)

 */

public class CSVSecondAssignment {
    
    CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar, String field){
        if(smallestSoFar == null)
            smallestSoFar = currentRow;
        else{
            double current = Double.parseDouble(currentRow.get(field));
            double smallest = Double.parseDouble(smallestSoFar.get(field));
            if ((field.equals("TemperatureF") && current != -9999 && current < smallest) || current < smallest)
                smallestSoFar = currentRow;
            }
            return smallestSoFar;
    }
    
    CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser)
            coldestSoFar = getSmallestOfTwo(currentRow, coldestSoFar, "TemperatureF");
        return coldestSoFar;
    }

    void testColdestHourInFile(){
        // File f = new File("D:\\Programming\\java-course-1\\Week3\\Exercises\\hottestTemp\\data\\2012\\weather-2012-01-01.csv"); // Windows Style
        // FileResource fr = new FileResource(f);
        FileResource fr = new FileResource(); // to prompt user to select a file
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        //System.out.println("coldest temperature in " + f.getName() +" was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEST"));
        // System.out.println("coldest temperature in " + f.getName() +" was " + coldest.get("TemperatureF") + " at " + coldest.get("TimeEDT"));
        System.out.println("coldest temperature was " + coldest.get("TemperatureF") + " at " + coldest.get("DateUTC"));
        }
    
    void fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestSoFar = null;
        File fileWithColdest = null;
        for (File f : dr.selectedFiles()){
            /* 
             * getName: To get Name of a File; 
             * getCanonicalPath: to get path of a File
             try{
                System.out.println("File chosen: " + f.getName() +  " at path: " + f.getCanonicalPath());
            }
            catch (Exception e){
                System.out.println("Exception during getCanonicalPath " + e);
            }
            */
            
            FileResource fr = new FileResource(f);
            CSVRecord coldestInFile = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null){
                coldestSoFar = coldestInFile;
                fileWithColdest = f;
            }
            else{
                double currentTemp = Double.parseDouble(coldestInFile.get("TemperatureF"));
                double coldestSoFarTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp != -9999 && currentTemp < coldestSoFarTemp){
                    coldestSoFar = coldestInFile;
                    fileWithColdest = f;
                }
            }
        }
        System.out.println("Coldest day was in file " + fileWithColdest.getName());
        System.out.println("Coldest temperature on that day was " + coldestSoFar.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were:");
        // String day = (fileWithColdest.getName()).substring(8,18);
        CSVParser parser = (new FileResource(fileWithColdest)).getCSVParser();
        for (CSVRecord record: parser)
            System.out.println(record.get("DateUTC") + ":" + record.get("TemperatureF"));
    }
    
    void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }
    
    CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser){
            String humidity = currentRow.get("Humidity");
            if (humidity.equals("N/A"))
                continue;
            lowestSoFar = getSmallestOfTwo(currentRow, lowestSoFar, "Humidity");
            }
        return lowestSoFar;
    }
    
    void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord lowestInFile = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getSmallestOfTwo(lowestInFile, lowestSoFar, "Humidity");
        }
        return lowestSoFar;
    }
    
    void testLowestHumidityInManyFiles(){
       CSVRecord csv = lowestHumidityInManyFiles();
       System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    double averageTemperatureInFile(CSVParser parser){
        double sum = 0, avg;
        int totalRecords = 0;
        for (CSVRecord record : parser){
            totalRecords++;
            sum += Double.parseDouble(record.get("TemperatureF"));
        }
        avg = sum/totalRecords;
        return avg;
    }
    
    void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average Temperature in file is " + avgTemp);
    }
    
    double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sum = 0, avg;
        int totalRecords = 0;
        for (CSVRecord record : parser){
            String humidity = record.get("Humidity");
            if((!humidity.equals("N/A")) && Double.parseDouble(humidity) >= value){
                totalRecords++;
                sum += Double.parseDouble(record.get("TemperatureF"));
            }
        }
        if (totalRecords == 0)
            return -9999.0;
        avg = sum/totalRecords;
        return avg;
    }
    
    void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTempWithHumidity = averageTemperatureWithHighHumidityInFile(parser, 80);
        if ( avgTempWithHumidity <= -9999.0)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average Temp when high Humidity is " + avgTempWithHumidity);
        }
}