package utilty;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResponseReaderJSON {
	private JsonPath js;
	
	public ResponseReaderJSON(String responseString) {
		js = new JsonPath(responseString);
	}
	
	public ResponseReaderJSON(Response response) {
		String stringResponse = response.asPrettyString();
		js = new JsonPath(stringResponse);
	}
	
	public String getMeValueOf(String key) {
		return js.getString(key);
	}
}
