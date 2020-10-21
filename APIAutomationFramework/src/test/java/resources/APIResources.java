package resources;

public enum APIResources {

	AddPlaceApi("maps/api/place/add/json"),
	GetPlaceApi("maps/api/place/get/json"),
	DeletePlaceApi("maps/api/place/delete/json");
	private String resource; 
	
	public String getResource() {
		return resource;
	}


	APIResources(String resource)
	{
		this.resource=resource;
	}
}
