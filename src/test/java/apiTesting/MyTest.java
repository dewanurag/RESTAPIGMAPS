package apiTesting;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class MyTest {
@Test(dataProvider = "bookData")
public void f(String ISBN, int aisle) {
	System.out.println("hi");
	RestAssured.baseURI = "http://216.10.245.166";
	String response_post = RestAssured.given().header("Content-Type","application/json")
	.body("{\"name\":\"Learn Appium Automation with Java\",\r\n"
			+ "\"isbn\":\""+ISBN+"\",\r\n"
			+ "\"aisle\":\""+aisle+"\",\r\n"
			+ "\"author\":\"John fever\"\r\n"
			+ "}")
	.when()
	.post("/Library/Addbook.php")
	.then().assertThat().statusCode(200)
	.extract().response().asPrettyString();
	System.out.println(response_post);
	JsonPath js = new JsonPath(response_post);
	String bookID = js.get("ID");
	System.out.println(bookID);
	
	String response_delete =RestAssured.given().header("Content-Type","application/json")
	.body("{\n    \"ID\": \""+String.valueOf(bookID)+"\"\n}")
	.when()
	.post("/Library/DeleteBook.php")
	.then().assertThat().statusCode(200)
	.extract().response().asPrettyString();
	System.out.println(response_delete);
}

@Test
public void testStaticJSON() throws IOException {
	RestAssured.baseURI = "http://216.10.245.166";
	String response_post = RestAssured.given().header("Content-Type","application/json")
	.body(PayLoadReader.JSONParser())
	.when()
	.post("/Library/Addbook.php")
	.then().assertThat().statusCode(200)
	.extract().response().asPrettyString();
	System.out.println(response_post);
	JsonPath js = new JsonPath(response_post);
	String bookID = js.get("ID");
	System.out.println(bookID);
	
	String response_delete =RestAssured.given().header("Content-Type","application/json")
	.body("{\n    \"ID\": \""+String.valueOf(bookID)+"\"\n}")
	.when()
	.post("/Library/DeleteBook.php")
	.then().assertThat().statusCode(200)
	.extract().response().asPrettyString();
	System.out.println(response_delete);
}


@DataProvider(name = "bookData")
public Object[][] bookData(){
	return new Object[][]{{"ass",9090},{"cum",6969}};
}


}
