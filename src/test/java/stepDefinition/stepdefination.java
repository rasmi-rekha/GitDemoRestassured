package stepDefinition;

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
import static org.junit.Assert.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Utils;

public class stepdefination extends Utils {
          
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
    TestDataBuilder data=new TestDataBuilder();
    static String place_id;
    JsonPath js;
      
    

    	@Given("Add Place Payload with {string} {string} {string}")
    	public void add_Place_Payload_with(String name, String language, String address) throws FileNotFoundException {
    	    // Write code here that turns the phrase above into concrete actions

		        
		    
		     res=given().spec(requestSpecification())
		    .body(data.addplacepayload(name,language,address)); 
		    
		    
			}



		@When("user calls {string} with {string} http request")
		public void user_calls_with_http_request(String resource, String method) {
		    // Write code here that turns the phrase above into concrete actions
			
			APIResources resourceAPI=APIResources.valueOf(resource);
			System.out.println(resourceAPI.getResource());
					
			resspec =new ResponseSpecBuilder().build();
						
			if(method.equalsIgnoreCase("POST"))
				response =res.when().post(resourceAPI.getResource());
		    	
			else if(method.equalsIgnoreCase("GET"))
				response =res.when().get(resourceAPI.getResource());
			
		}
		@Then("the API call got success with status code {int}")
		public void the_API_call_got_success_with_status_code(Integer int1) {
		    // Write code here that turns the phrase above into concrete actions
		  assertEquals(response.getStatusCode(),200);
		  
		}
		@Then("{string} in response body is {string}")
		public void in_response_body_is(String keyvalue, String Expectedvalue) {
		    // Write code here that turns the phrase above into concrete actions
			assertEquals(getJsonPath(response, keyvalue),Expectedvalue);

		}

		
			@Then("verify place_Id created maps to {string} using {string}")
			public void verify_place_Id_created_maps_to_using(String Expectedname, String resource) throws FileNotFoundException {
			    // Write code here that turns the phrase above into concrete actions
				
				
				  place_id=getJsonPath(response, "place_id");

				 res=given().spec(requestSpecification()).queryParam("place_id", place_id);
				 
				 user_calls_with_http_request(resource,"GET");
				 String actualname=getJsonPath(response, "name");
				 assertEquals(actualname,Expectedname);
				 
				System.out.println("git test");
			    			}


				@Given("DeletePlace Payload")
				public void deleteplace_Payload() throws FileNotFoundException {
				    // Write code here that turns the phrase above into concrete actions
				    
					res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));	
					
					
					
				}





	
}
