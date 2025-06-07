package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class stepDefinition {
	ResponseSpecification res;
	RequestSpecification given_response;
	Response response;
	AddPlace ap;

	@Given("Add Place payload")
	public void add_place_payload() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 875 470 2928");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("Tamil-IN");
		List<String> types_list = new ArrayList<String>();
		types_list.add("shoe park");
		types_list.add("shop");
		p.setTypes(types_list);
		Location lc = new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		p.setLocation(lc);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		given_response = given().spec(req).body(p);

	}

	@When("user calls {string} with post http request")
	public void user_calls_with_post_http_request(String string) {
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
