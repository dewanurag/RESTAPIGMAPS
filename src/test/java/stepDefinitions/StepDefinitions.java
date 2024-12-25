package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.junit.Assert;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoogleMap;
import pojo.Location;
import utilty.APIResources;
import utilty.BodyBuilderForLocationAPI;
import utilty.ResponseReaderJSON;
import utilty.Utilities;

public class StepDefinitions extends Utilities {
	RequestSpecification req;
	ResponseSpecification res;
	RequestSpecification request;
	Response response;
	ResponseReaderJSON rsp;
	static String place_id;
	public StepDefinitions() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Given("I have add location payload")
	public void i_have_add_location_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    req = getRequestSpecBuilder();
	    request = RestAssured.given().spec(req).body(BodyBuilderForLocationAPI.getBody());
	    res = getResponse();
	    
	}
	
//	@Given("I set payload with {string}, {string}, {string}, {string}, {string}, {string}")
//	public void i_set_payload_with(String lat, String lng, String accuracy, String address, String language, String name) {
//	    // Write code here that turns the phrase above into concrete actions
//	    GoogleMap gmap = new GoogleMap();
//	    gmap.setAccuracy(0);
//	}
	@Given("I set payload with {double}, {double}, {int}, {string}, {string}, {string}")
	public void i_set_payload_with(Double lat, Double lng, Integer accuracy, String address, String language, String name) {
	    // Write code here that turns the phrase above into concrete actions
		req = getRequestSpecBuilder();
		GoogleMap gmap = new GoogleMap();
		Location loc = new Location();
		loc.setLat(lat);
		loc.setLng(lng);
		gmap.setLocation(loc);
		gmap.setAccuracy(accuracy);
		gmap.setAddress(address);
		gmap.setName(name);
		gmap.setLanguage(language);
		gmap.setPhone_number("(+91) 9010 9000 89");
		gmap.setWebsite("http://google.com");
		String[] types_ = {"shoe park","shop"};
		gmap.setTypes(Arrays.asList(types_));
		//req = getRequestSpecBuilder();
	    request = RestAssured.given().spec(req).body(gmap);
	    res = getResponse();
	}
	
	@When("I call {string} with POST HTTP request")
	public void i_call_with_post_http_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resource =  APIResources.valueOf(string);
		String whatToSend = resource.getResource();
	   response = request.when().post(getPropertyObj().getProperty("create_location_resource"))
			   .then().spec(res).extract().response();
		rsp = new ResponseReaderJSON(response);
		StepDefinitions.place_id = rsp.getMeValueOf("place_id");
		System.out.println("i_call_with_post_http_request");
	}
	@Then("I get success message with status code {int}")
	public void i_get_success_message_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	   boolean flag = int1==response.getStatusCode() ;
	   assertTrue(flag);
	}
	@Then("value of {string} in response body is {string}")
	public void value_of_in_response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
//	    String respString = response.asPrettyString();
//	    JsonPath js = new JsonPath(respString);
	    String actualValue = rsp.getMeValueOf(key);
	    //assertEquals(actualValue, value);
	    assertEquals(actualValue, value);
	}
	
	@When("I call {string} with {string} HTTP request")
	public void i_call_with_http_request(String requestReq, String httpMethod) {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resource =  APIResources.valueOf(requestReq);
		String whatToSend = resource.getResource();
//	   response = request.when().post(getPropertyObj().getProperty("create_location_resource"))
//			   .then().spec(res).extract().response();
	    switch (httpMethod) {
		case "POST":
			response = request.when().post(whatToSend).then().spec(res).extract().response();
			break;
		case "GET":
			response = request.queryParam("key", getPropertyObj().getProperty("key"))
			.queryParam("place_id", rsp.getMeValueOf("place_id"))
			.when().get(whatToSend).then().spec(res).extract().response();
			System.out.println("get call");
			break;
			
		case "DELETE":
			response = request.when().delete(whatToSend).then().spec(res).extract().response();
			break;	
			
		case "PUT":
			response = request.when().put(whatToSend).then().spec(res).extract().response();
			break;
			
		default:
			break;
		}
	    rsp = new ResponseReaderJSON(response);
	    StepDefinitions.place_id = rsp.getMeValueOf("place_id");
	    
	}
	
	@Then("I verify placeId created with {string} using {string}")
	public void i_verify_place_id_created_with_using(String attribute, String requestType) {
	    // Write code here that turns the phrase above into concrete actions
//		Response responseForValidation;
		APIResources resource =  APIResources.valueOf(requestType);
		String whatToSend = resource.getResource();
		if (requestType.contains("get")) {
			response = request.queryParam("key", getPropertyObj().getProperty("key"))
					.queryParam("place_id", rsp.getMeValueOf("place_id"))
					.when().get(whatToSend).then().spec(res).extract().response();
		}
		ResponseReaderJSON rs = new ResponseReaderJSON(response);
		String attributeActualValue = rs.getMeValueOf("name");
		Assert.assertEquals(attributeActualValue, attribute);
		
			
	}
	
	@Given("I have delete location payload")
	public void i_have_delete_location_payload() {
	    // Write code here that turns the phrase above into concrete actions
		req = getRequestSpecBuilder();
	    request = RestAssured.given().spec(req).body(BodyBuilderForLocationAPI.deleteMapPayLoad(StepDefinitions.place_id));
	    res = getResponse();
	    System.out.println("delete method");
	}


}
