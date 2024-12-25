import java.nio.file.Files;
import java.nio.file.Paths;

public class PayLoadReader {
	public String JSONParser() {
		byte[] byteInfoFromJSONFile = Files.readAllBytes(Paths.get("bookbodyPOST.json"));
		String infoFromJSONFile = new String(byteInfoFromJSONFile);
		return infoFromJSONFile;
	}
}
