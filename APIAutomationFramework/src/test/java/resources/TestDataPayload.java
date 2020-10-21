package resources;

import java.util.ArrayList;
import java.util.List;

import pojoClasses.AddPlacePojo;
import pojoClasses.Location;

public class TestDataPayload {

	public AddPlacePojo addPlacePayload(String name,String address,String language,int accuracy)
	{
		AddPlacePojo addPlacePojo=new AddPlacePojo();
		addPlacePojo.setAccuracy(accuracy);
		addPlacePojo.setAddress(address);
		addPlacePojo.setLanguage(language);
		addPlacePojo.setPhone_number("9090897807");
		
		List<String> list=new ArrayList<String>();
		list.add("babluoo dukan");
		list.add("deva dukan");
		list.add("babal shop");
		addPlacePojo.setTypes(list);
		
		Location location=new Location();
		location.setLat(-38.65444);
		location.setLng(33.6544);
		addPlacePojo.setLocation(location);
		
		addPlacePojo.setWebsite("google.com");
		addPlacePojo.setName(name);
		return addPlacePojo;
	}
	

	public String deletePlacePayload(String payload)
	{
		return "{\r\n\"place_id\":\""+payload+"\"\r\n}";
		
	}
}
