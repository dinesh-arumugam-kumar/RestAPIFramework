Feature: Validating place API's

Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
#Driving data dynamically
	Given Add Place payload with "<Name>" "<Language>" "<Address>" 
	When user calls "AddPlaceAPI" with "post" http request
	Then the API call is success with status coded 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
Examples:
  | Name           				| Language   | Address            |
  | Dinesh Dreamers House | Tamil-IN 	 | World Cross Center |
  | Dreamers House 				| English-IN | Sea Cross Center		|