package stepDefinition;

import java.io.FileNotFoundException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforescenario() throws FileNotFoundException{
		
		stepdefination s = new stepdefination();
		
		if(stepdefination.place_id==null)
		{
		s.add_Place_Payload_with("bibek", "french", "asia");
		s.user_calls_with_http_request("AddPlaceApi", "POST");
		s.verify_place_Id_created_maps_to_using("bibek", "getPlaceAPI");
		}
	}

}
