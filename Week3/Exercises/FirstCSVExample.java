package firstCSV;

/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstCSVExample {
	public void readFood() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		for (CSVRecord record : parser){
			System.out.print(record.get("Name") + " ");
			System.out.print(record.get("Favorite Color") + " ");
			// String num = record.get("Favorite Number"); // <- No such column
			System.out.println(record.get("Favorite Food"));
		}
	}
}
