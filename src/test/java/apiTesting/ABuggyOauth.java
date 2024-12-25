 package apiTesting;

import java.util.List;
import java.util.ListIterator;

import org.bouncycastle.jcajce.provider.asymmetric.RSA;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.Course;

public class ABuggyOauth {
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String tokenResponse = RestAssured.given().
				formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParams("grant_type", "client_credentials")
				.formParams("scope", "trust").
				when().log().all().post("/oauthapi/oauth2/resourceOwner/token")
				.then().log().all().statusCode(200).extract().asPrettyString();
	JsonPath js = new JsonPath(tokenResponse);
	String access_token = js.getString("access_token");
	
	String courseDetails = RestAssured.given().queryParam("access_token", access_token)
	.when().get("/oauthapi/getCourseDetails")
	.then().log().all().extract().asPrettyString();
	
	pojo.RSA response = RestAssured.given().queryParam("access_token", access_token)
	.when().get("/oauthapi/getCourseDetails")
	.then().log().all().extract().as(pojo.RSA.class);
	
	System.out.println("LinkedIN: "+response.getLinkedIn());
	System.out.println("instructor: "+response.getInstructor());
	System.out.println("url: "+response.getUrl());
	System.out.println("expertise: "+response.getExpertise());
	
	List<Course> apiCourses = response.getCourses().getApi();
	for (Course course : apiCourses) {
		String course_title = course.getCourseTitle();
		int course_price = course.getPrice();
		System.out.println("course name: "+ course_title+" , course price is: "+course_price);
	}
	
	List<Course> webAutomationCourses = response.getCourses().getWebAutomation();
	ListIterator<Course> it = webAutomationCourses.listIterator();
	while(it.hasNext()) {
		System.out.println(it.next().getCourseTitle());
	}
	
	}
	
	
	
}
