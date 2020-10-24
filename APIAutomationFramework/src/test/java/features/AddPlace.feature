Feature: Validate Add Place API 

@AddPlace
Scenario Outline: Verify if Add Place API is successfully adding a place. 

	Given PayLoad is provided to the API request with "<name>" "<address>" "<language>" <accuracy> 
	When User calls the "AddPlaceApi" with "POST" Http request 
	Then The Response received is success with status code as 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP"
	And Verify "<name>" "<address>" "<language>" <accuracy> in "GetPlaceApi" using GET Http Request
	
	Examples: 
		|name|address|language|accuracy|
		|MSDianfan clib|Colony|Sindhi|39|
		##|Sachin|Colony|Sindhi|40|

@DeletePlace	
Scenario: Verify if delete API is working fine

Given Delete Payload
When User calls the "DeletePlaceApi" with "POST" Http request 
Then The Response received is success with status code as 200 
And "status" in response body is "OK" 

 