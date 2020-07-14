/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        //for each row in the CSV File
        for (CSVRecord record: parser){
            //Look at the "Exports" column
            String exports = record.get("Exports");
            //Check if it contains exportOfInterest
            if (exports.indexOf(exportOfInterest) != -1){
            // if (exports.contains(exportOfInterest)){ // <- OR
                //If so, write down the "Country" from that row
                System.out.println(record.get("Country"));
            }
            }
        }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExporters(parser, "coffee");
    }
}
