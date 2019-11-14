Feature: Automate Script for API Test

Scenario: Validate API Request & Response
	Given the user have proper API Request in File1 and File2
	When the user sents a GET request to register API with valid request
	Then API should have status code as 200 and content-type as JSON
	And API should return proper json response and compare both Response