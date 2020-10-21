package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	
	@Before("@DeletePlace")
	public void setupForDeleteApiIndependently() throws IOException
	{
		if(AddPlaceSTD.place_id==null)
		{
		AddPlaceSTD object=new AddPlaceSTD();
		object.pay_load_is_provided_to_the_api_request_with("Anil Mohnani", "Mumbai","English", 99);
		object.user_calls_the_with_http_request("AddPlaceApi","POST");
		object.verify_in_using_get_http_request("Anil Mohnani","Mumbai", "English", 99, "GetPlaceApi");
		}
	}
}
