package apiTesting;

import io.restassured.RestAssured;

public class JiraBug {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://dewanurag23.atlassian.net";
		String postBugResponse = RestAssured.given().header("Authorization","Basic ZGV3YW51cmFnMjNAZ21haWwuY29tOkFUQVRUM3hGZkdGMFNaeFVhbUpqTGEyeGlGVVduVlhMSjJ5RGlPY1BZd005T0xuWkN6UjBOOXEzcXZHY2c5eFVaRFQ3WW5CVG9uNUh1c0tRamJET19nbmRGa2JBcWhtVUlwY1J4cnZhQzI0UGExMDMzVG5GaWZCckJtcDJaLWFsUFdsdURteWduY3VfUXhzNXFRbExmNFNZRlByUndyMXozYnd2RUY4T1lLSkVkS0hINTR2cGlmcz04QjI4MDY5RQ==")
			.header("Content-Type", "application/json")
			.body("{\r\n"
					+ "\"fields\": {\r\n"
					+ "\"project\": {\r\n"
					+ "\"key\": \"SCRUM\"\r\n"
					+ "},\r\n"
					+ "\"issuetype\": {\r\n"
					+ "      \"id\": \"10001\"\r\n"
					+ "    },\r\n"
					+ "\"summary\": \"Code is not working hell yeahhh2\",\r\n"
					+ "\"description\": [\"value\",\"This is test description\"]\r\n"
					+ "}\r\n"
					+ "}")
			.log().all().when().post("/rest/api/3/issue")
			.then().log().all().statusCode(201).contentType("application/json").extract().asPrettyString();
	}

}
