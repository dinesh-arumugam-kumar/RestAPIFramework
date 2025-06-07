//Here is where we will keep all data
package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload() {
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 875 470 2928");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("Tamil-IN");
		List<String> types_list = new ArrayList<String>();
		types_list.add("shoe park");
		types_list.add("shop");
		p.setTypes(types_list);
		Location lc = new Location();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		p.setLocation(lc);
		
		return p;
	}

}
