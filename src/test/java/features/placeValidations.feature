Feature: Validating place API's

Scenario: Verify if place is being successfully added using AddPlaceAPI
	Given Add Place payload
	When user calls "AddPlaceAPI" with post http request
	Then the API call is success with status coded 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"