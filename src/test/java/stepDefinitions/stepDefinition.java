package stepDefinitions;

import static io.restassured.RestAssured.given;

import org.junit.Assert;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefinition extends Utils { // extending Utils to use the requestSpecification & other common method
	ResponseSpecification res;
	RequestSpecification given_response;
	Response response;
	AddPlace ap;
	TestDataBuild data = new TestDataBuild(); //Calling the data class to get the payload
	

	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload(String Name, String lang, String Address) throws Exception {
//		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		given_response = given().spec(requestSpecification()).body(data.addPlacePayload(Name, lang, Address));

	}

	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) {
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (string.equalsIgnoreCase("AddPlaceAPI")) {
			response = given_response.when().post("maps/api/place/add/json").then().spec(res).extract().response();
		}
	}

	@Then("the API call is success with status coded {int}")
	public void the_api_call_is_success_with_status_coded(Integer int1) {
		int statusCode = response.getStatusCode();
		System.out.println("Status code "+statusCode);
		assertEquals(response.getStatusCode(), 200);
//		assertSame(statusCode, int1);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		String keys = js.get(key).toString();
		Assert.assertEquals(keys, value);
	}

}
