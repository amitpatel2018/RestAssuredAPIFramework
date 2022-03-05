Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
Given Add Place Payload with "<name>", "<language>" and "<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id creted maps to "<name>" using "GetPlaceAPI"

Examples:
|name|language|address|
|Elizabeth House|English|23 Some Street SC3 5EN|
#|Obama House|US English|56 Sea cross center|

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working
Given DeletePlace PayLoad
When user calls "DeletePlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"