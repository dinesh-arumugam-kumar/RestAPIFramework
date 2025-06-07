//Here is where we will keep all data
package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String Name, String Lanugage, String Address) {
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setName(Name);
		p.setPhone_number("(+91) 875 470 2928");
		p.setAddress(Address);
		p.setWebsite("http://google.com");
		p.setLanguage(Lanugage);
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
