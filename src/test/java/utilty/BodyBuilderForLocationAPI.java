package utilty;

import java.util.Arrays;

import pojo.GoogleMap;
import pojo.Location;

public class BodyBuilderForLocationAPI {
	public static GoogleMap getBody() {
		GoogleMap gmap = new GoogleMap();
		Location loc = new Location();
		loc.setLat(-38.38);
		loc.setLng(33.789);
		String[] types_ = {"shoe park","shop"};
		gmap.setAccuracy(50);
		gmap.setAddress("Puchchu Desh");
		gmap.setLanguage("Pyar ki bhasha");
		gmap.setName("Puchchu");
		gmap.setLocation(loc);
		gmap.setPhone_number("(+91) 9010 9000 89");
		gmap.setWebsite("http://google.com");
		gmap.setTypes(Arrays.asList(types_));
		return gmap;
	}
	
	public static String deleteMapPayLoad(String location_id) {
		String deletePayLoad = "{\r\n"
				+ "    \"place_id\": \""+location_id+"\"\r\n"
				+ "}";
		return deletePayLoad;
	}
}
