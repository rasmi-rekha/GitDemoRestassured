Feature: Validating place Api's
@AddPlace
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
          Given Add Place Payload with "<name>" "<language>" "<address>"
          When user calls "AddPlaceApi" with "POST" http request
          Then the API call got success with status code 200
          And "status" in response body is "OK"
          And "scope" in response body is "APP"
          And verify place_Id created maps to "<name>" using "getPlaceAPI"
          
          
Examples:
         |name    |language |address           |
         |AAHouse |English  |World trust center|  
   #     |BBHouse |Hindi    |India Center      |
         
@DeletePlace 
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"