package resources; // This class is used to set up common utilities for API testing

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req; // Static variable to hold the RequestSpecification object, We can use static variable to avoid re-initialization of request specification object
	
	public String getGlobalValue(String key) throws Exception { // This method retrieves values from a properties file
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
		prop.load(fis);
		prop.getProperty(key);
		return prop.getProperty(key);
	}
	
	public RequestSpecification requestSpecification() throws Exception {
		
		if(req==null) { // Check if req is null to avoid re-initialization, Making sure we only create the RequestSpecification once by using a static variable
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt")); // Initialize PrintStream to log requests & creating new file in run time& we have to add throws declaration
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")) // Get base URL from global.properties file
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log)) // Log requests to console or file loggingfilter
					.addFilter(ResponseLoggingFilter.logResponseTo(log)) // Log responses to console or file loggingfilter
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}
	
	public String getJsonPath(Response response, String key) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		return js.get(key).toString(); 
	}

}
