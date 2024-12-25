package utilty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utilities {
	RequestSpecification req;
	ResponseSpecification res;
	Properties prop;
	FileInputStream fis;
	PrintStream log;
	public Utilities() throws IOException {
		this.prop = new Properties();
		this.fis = new FileInputStream("./Configuration/configuration.properties");
		prop.load(fis);
		fis.close();
		log = new PrintStream(new FileOutputStream("loggedInfo.txt",true));
	}
	
	public String getValueFromPropertiesFile(String input) {
		return prop.getProperty(input);
	}
	
	public Properties getPropertyObj() {
		return prop;
	}

	public RequestSpecification getRequestSpecBuilder(String baseURI, String queryParamKey, String queryParamValue) {
		req = new RequestSpecBuilder().setBaseUri(baseURI).addQueryParam(queryParamKey, queryParamValue)
				.addFilter(RequestLoggingFilter.logRequestTo(log))	
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
	}
	
	public RequestSpecification getRequestSpecBuilder() {
		req = new RequestSpecBuilder().setBaseUri(prop.getProperty("base_url")).
		addQueryParam("key", prop.getProperty("key"))
		.addFilter(RequestLoggingFilter.logRequestTo(log))	
		.addFilter(ResponseLoggingFilter.logResponseTo(log))
		.build();
		return req;
	}
	
	public ResponseSpecification getResponse(int statusCodeExpected) {
		res = new ResponseSpecBuilder().expectStatusCode(statusCodeExpected).build();
		return res;
	}
	
	public ResponseSpecification getResponse() {
		res = new ResponseSpecBuilder().build();
		return res;
	}
	
	
	
}
