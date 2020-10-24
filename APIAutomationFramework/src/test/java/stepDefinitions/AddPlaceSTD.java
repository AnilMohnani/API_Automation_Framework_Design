package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoClasses.AddPlacePojo;
import pojoClasses.Location;
import resources.APIResources;
import resources.TestDataPayload;
import resources.Utils;

public class AddPlaceSTD extends Utils{
	RequestSpecification reqSpec;
	Response response=null;
	 static String place_id;
	TestDataPayload testDataPayload=new TestDataPayload();

	@Given("PayLoad is provided to the API request with {string} {string} {string} {int}")
	public void pay_load_is_provided_to_the_api_request_with(String name, String address, String language, Integer accuracy) throws IOException {
	reqSpec=given().spec(requestSpecification()).body(testDataPayload.addPlacePayload(name,address,language,accuracy));
}

	@When("User calls the {string} with {string} Http request")
	public void user_calls_the_with_http_request(String resource, String method){
	APIResources resources=APIResources.valueOf(resource);
	String apiResource=resources.getResource();
	if(method.equalsIgnoreCase("POST"))
	{
	 response=reqSpec.when().post(apiResource);
	}
	else if(method.equalsIgnoreCase("GET"))
	{
	 response=reqSpec.when().get(apiResource);
	}
}
@Then("The Response received is success with status code as {int}")
public void the_response_received_is_success_with_status_code_as(int expectedCode) {
response.then().spec(responseSpecification());
assertEquals(response.getStatusCode(),expectedCode);
}
@Then("{string} in response body is {string}")
public void in_response_body_is(String key, String value) {
	    assertEquals(value,getJsonPath(response,key));
  
}
@Then("Verify {string} {string} {string} {int} in {string} using GET Http Request")
public void verify_in_using_get_http_request(String name, String address, String language, Integer accuracy, String resource) throws IOException {
	place_id=getJsonPath(response, "place_id");
	 System.out.println( place_id);
	reqSpec=given().spec(requestSpecification()).queryParam("place_id", place_id);
	user_calls_the_with_http_request(resource,"GET");
	System.out.println(response.asString());
	String actualName=getJsonPath(response, "name");
	assertEquals(name, actualName);
	assertEquals(address, getJsonPath(response, "address"));
	assertEquals(language, getJsonPath(response, "language"));
	
}
@Given("Delete Payload")
public void delete_payload() throws IOException {
	reqSpec=given().spec(requestSpecification()).body(testDataPayload.deletePlacePayload(place_id));
}
}
