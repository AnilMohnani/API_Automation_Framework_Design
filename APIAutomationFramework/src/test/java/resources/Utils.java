package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification reqSpec;
	ResponseSpecification responseSpec;
	Properties prop;

	public RequestSpecification requestSpecification() throws IOException {

		if(reqSpec==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		reqSpec = new RequestSpecBuilder().setBaseUri(Utils.getGlobalProperty("baseUrl")).setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return reqSpec;
	}
		return reqSpec;
	}
	
	public ResponseSpecification responseSpecification()
	{
		responseSpec=new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return responseSpec;
	}
	
	public static String getGlobalProperty(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream(new File("src/test/java/resources/global.properties"));
		 Properties prop=new Properties();
		 prop.load(fis);
		 return (String) prop.get(key);
		
		
	}
	
	public String getJsonPath(Response response,String key)
	{
		JsonPath js=new JsonPath(response.asString());
		return js.get(key).toString()
				;
		
	}
	
}
