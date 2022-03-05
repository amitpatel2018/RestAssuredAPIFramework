package resources;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

//import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {
		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			// RestAssured.baseURI = "https://rahulshettyacademy.com";
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).addHeader("Content-Type", "application/json")
					.build();
			return req;
		} else {
			return req;
		}
	}

	public String getGlobalValue(String key) throws IOException {
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\amit\\javaprojects\\RestAssuredFramework\\src\\test\\java\\resources\\global.properties");
		p.load(fis);
		return p.getProperty(key);
	}
	
	public String getJsonPath(Response response, String key) {
		String responseString = response.asString();
	    JsonPath js = new JsonPath(responseString);
	    return js.get(key).toString();
	}
}
