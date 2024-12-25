package apiTesting;

import java.util.Arrays;

import io.restassured.RestAssured;
import net.bytebuddy.NamingStrategy.Suffixing.BaseNameResolver.ForGivenType;
import pojo.GoogleMap;
import pojo.Location;

public class GoogleMaps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String resource = "/maps/api/place/add/json";
		
		RestAssured.given().queryParam("key", "qaclick123").body(gmap).log().all()
		.when().post(resource).then().statusCode(200);
		
	}

}
