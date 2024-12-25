package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;
import jdk.internal.org.jline.utils.Log;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		System.out.println("Im in hook");
		String place_id = StepDefinitions.place_id;
		if(place_id==null) {
			System.out.println("pace id was null");
			StepDefinitions sd = new StepDefinitions();
			sd.i_have_add_location_payload();
			sd.i_call_with_post_http_request("addPlaceAPI");
		}
		
	}

}
