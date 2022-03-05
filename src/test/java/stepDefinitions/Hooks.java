package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		// need to run only when running only DeletePlace API scenario
		// execute this only when place id is null - required for delete place api
		// write a code that will give you place_id
		StepDefinition sd = new StepDefinition();
		if (StepDefinition.placeID == null) {
			sd.add_place_payload_with_and("Amit", "English", "UK");
			sd.user_calls_with_http_request("AddPlaceAPI", "Post");
			sd.verify_place_id_creted_maps_to_using("Amit", "GetPlaceAPI");
		}
	}
}
