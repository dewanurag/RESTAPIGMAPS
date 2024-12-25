package apiTesting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PayLoadReader {
	public static String JSONParser() throws IOException {
		byte[] byteInfoFromJSONFile = Files.readAllBytes(Paths.get("src\\main\\resources\\payloadresources\\bookbodyPOST.json"));
		String infoFromJSONFile = new String(byteInfoFromJSONFile);
		return infoFromJSONFile;
	}
}
