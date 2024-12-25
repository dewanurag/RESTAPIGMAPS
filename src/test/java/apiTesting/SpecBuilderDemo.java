package apiTesting;

import java.util.Arrays;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoogleMap;
import pojo.Location;

public class SpecBuilderDemo {

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
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				.build();
		
		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();
		
//		RestAssured.given().queryParam("key", "qaclick123").body(gmap).log().all()
//		.when().post(resource).then().statusCode(200);
		
		Response overallResp =  RestAssured.given().spec(req).body(gmap).log().all()
		.when().post(resource).then().spec(res).extract().response();
		
		System.out.println(overallResp.asPrettyString());
	}

}
