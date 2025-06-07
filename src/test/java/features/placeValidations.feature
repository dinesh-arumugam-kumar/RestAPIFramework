Feature: Validating place API's

@Test @AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI and verify the place is created successfully using GetPlaceAPI
#Driving data dynamically
	Given Add Place payload with "<Name>" "<Language>" "<Address>" 
	When user calls "AddPlaceAPI" with "post" http request
	Then the API call is success with status coded 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created that maps to "<Name>" using "GetPlaceAPI"
	
Examples:
  | Name           				| Language   | Address            |
  | Dinesh Dreamers House | Tamil-IN 	 | World Cross Center |
  #| Dreamers House 				| English-IN | Sea Cross Center		|

@Test @DeletePlace
Scenario: verify if we are able to delete place using DeletePlaceAPI
	Given Delete Place payload
	When user calls "DeletePlaceAPI" with "post" http request
	Then the API call is success with status coded 200
	And "status" in response body is "OK"