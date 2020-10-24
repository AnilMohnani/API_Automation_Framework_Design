package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import static io.restassured.RestAssured.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.ExcelDataDriven;
import resources.Utils;

public class AddBookSTD extends Utils {
	ArrayList<String> list;
	RequestSpecification reqSpec;
	Response response;
	HashMap<String, Object> map = new HashMap<String, Object>();

	@Given("Add Book Payload")
	public void add_book_payload() throws IOException {
		list = ExcelDataDriven.getTestDataUsingExcel("Sheet1", "AddBook");
		map.put("name", list.get(1));
		map.put("isbn", list.get(2));
		map.put("aisle", list.get(3));
		map.put("author", list.get(4));

		reqSpec = given().spec(getRequestSpecForAddBook()).body(map);

	}

	@When("User Calls {string} using {string} method")
	public void user_calls_using_method(String resource, String method) {
		APIResources apiresource = APIResources.valueOf(resource);
		String apiResource = apiresource.getResource();
		if (method.equalsIgnoreCase("POST"))
			response = reqSpec.when().post(apiResource);
		else if (method.equalsIgnoreCase("GET"))
			response = reqSpec.when().get(apiResource);

	}

	@Then("Response is received with success with status code {int}")
	public void response_is_received_with_success_with_status_code(Integer int1) {

	}

	@Then("{string} in response boyd is {string}")
	public void in_response_boyd_is(String string, String string2) {

	}

	@Then("{string} is generated")
	public void is_generated(String string) {

	}
}
