package apiTesting;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.ECommerce_Login;

public class Ecommerce {
	public static void main(String[] args) {
	RequestSpecification req_login = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.setContentType(ContentType.JSON)
			.build();
	String body_login = "{\r\n"
			+ "    \"userEmail\": \"pyarababu@gmail.com\", \r\n"
			+ "    \"userPassword\": \"Qwerty7890\"\r\n"
			+ "}";
	ResponseSpecification res_login = new ResponseSpecBuilder().expectStatusCode(200).build();
	ECommerce_Login loginResponse = RestAssured.given()
			.spec(req_login)
			.body(body_login).log().all()
			.when().post("/api/ecom/auth/login").then().log().all()
			.spec(res_login).extract().as(ECommerce_Login.class);
	System.out.println(loginResponse.getToken());
	String token = loginResponse.getToken();
	String userID = loginResponse.getUserId();
	//Add a product
	
	RequestSpecification addProductreq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
											.addHeader("Authorization",token)
											.build();
	RequestSpecification add_product_request = RestAssured.given().log().all()
			.spec(addProductreq).param("productName", "Lal Chaddi")
			.param("productAddedBy", userID)
			.param("productCategory", "fashion")
			.param("productSubCategory", "under garments")
			.param("productPrice", 30)
			.param("productDescription", "kis colour ki peheni hai?")
			.param("productFor", "men")
			.multiPart("productImage",new File("C:\\Users\\dewan\\Downloads\\undie.jpeg"));
	
	String addAProduct = add_product_request.when().post("/api/ecom/product/add-product").then().extract().asPrettyString();
	System.out.println(addAProduct);
	JsonPath js = new JsonPath(addAProduct);
	String productId = js.getString("productId");
	
	//place an order
	RequestSpecification placeOrderReqBase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.setContentType(ContentType.JSON).addHeader("Authorization", token)
			.build();
	
	RequestSpecification placeOrderRequest = RestAssured.given().log().all().spec(placeOrderReqBase);
	
	String orderPlaced = placeOrderRequest.body("{\"orders\":[{\"country\":\"Australia\",\"productOrderedId\":\""+productId+"\"}]}")
					.when().post("/api/ecom/order/create-order")
					.then().log().all().extract().asPrettyString();
	
	System.out.println(orderPlaced);
	
	//delete the product
	
	String deleteResource = "/api/ecom/product/delete-product/{productId}";
	RequestSpecification deleteReqBase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addHeader("Authorization", token)
			.setContentType(ContentType.JSON)
			.build();
	RequestSpecification deleteRequest = RestAssured.given().spec(deleteReqBase).pathParam("productId", productId).log().all();
	String deleteResponse = deleteRequest.when().delete(deleteResource)
			.then().extract().asPrettyString();
	
	System.out.println(deleteResponse);
	}}
