package stepDefinitions;

import static io.restassured.RestAssured.given;

//import java.util.ArrayList;
//import java.util.List;
import static org.junit.Assert.*;

//import java.io.FileNotFoundException;
import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import io.restassured.RestAssured;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.http.ContentType;
//import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
//import pojo.AddPlace;
//import pojo.Location;
import resources.TestDataBuild;
import resources.Utils;


public class StepDefinition extends Utils {
	RequestSpecification request;
	ResponseSpecification res;
	Response response;
	TestDataBuild data = new TestDataBuild(); 
	static String placeID;
	
	@Given("Add Place Payload with {string}, {string} and {string}")
	public void add_place_payload_with_and(String name, String language, String address) throws IOException {
		request = given().spec(requestSpecification()).body(data.addPlacePayLoad(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		//res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
			response = request.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response = request.when().get(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("PUT"))
			response = request.when().put(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("DELETE"))
			response = request.when().delete(resourceAPI.getResource());
		else
			 throw new NullPointerException();
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		assertEquals(getJsonPath(response, key), value);
	}
	@Then("verify place_Id creted maps to {string} using {string}")
	public void verify_place_id_creted_maps_to_using(String expectedName, String resource) throws IOException {
		placeID = getJsonPath(response, "place_id");
	    request = given().spec(requestSpecification()).queryParam("place_id", placeID);
	    user_calls_with_http_request(resource, "get");
	   String actualName = getJsonPath(response, "name");
	   System.out.println(actualName);
	   assertEquals(expectedName, actualName);
	}
	@Given("DeletePlace PayLoad")
	public void delete_place_pay_load() throws IOException {
	    request = given().spec(requestSpecification()).body(data.deletePlacePayLoad(placeID));
	}
}
