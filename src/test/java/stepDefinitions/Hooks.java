package stepDefinitions; // This class is used for hooks, which can be used to set up or tear down tests

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Exception {
		//write code that will give place_id 
		//also execute only if place_id is null
		stepDefinition sd = new stepDefinition();
		if(stepDefinition.place_id == null) {
			sd.add_place_payload("AnyName", "Tamil", "Coimbatore, Tamil Nadu, India");
			sd.user_calls_with_http_request("AddPlaceAPI", "post");
			sd.verify_place_id_created_that_maps_to_using("AnyName", "GetPlaceAPI");
		}
	}

}
