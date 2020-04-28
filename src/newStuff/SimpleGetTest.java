package newStuff;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SimpleGetTest {

	@Test
	public void GetWeatherDetails()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";  
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET,"/Hyderabad");
    	String responseBody = response.getBody().asString();
		System.out.println("Response body is => " + responseBody);
		
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200, "Correct");
		
		String statusline = response.getStatusLine();
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK", "Right");
		
		String contentType = response.header("Content-type");
		System.out.println("Content-Type value: " + contentType);
		Assert.assertEquals(contentType  , "application/json"   );
		
		String serverType  = response.header("Server");
		System.out.println("Server value: " + serverType);
		Assert.assertEquals(serverType, "nginx" );
		
		String contentEncoding  = response.header("Content-Encoding");
		System.out.println("Content-Encoding: " + contentEncoding );
		Assert.assertEquals(contentEncoding, "gzip"); 
		
		Headers allHeaders = response.headers();
		for(Header header: allHeaders ) {
			System.out.println("Key: " +header.getName() + " Value: " +header.getValue() );
		}
		
	}
	
	@Test
	public void WeatherMessageBody(){
		
		RestAssured.baseURI =("http://restapi.demoqa.com/utilities/weather/city");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
		
		// Retrieve the body of the response
		
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("/Hyderabad"), true); 
	}
}
