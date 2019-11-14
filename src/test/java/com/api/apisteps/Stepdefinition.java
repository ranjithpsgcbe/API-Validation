package com.api.apisteps;

import com.api.apiactions.ApiTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Stepdefinition {
	
	@Steps
	ApiTest apitest;

	@Given("^the user have proper API Request in File1 and File2$")
	public void the_user_have_proper_API_Request_in_File1_and_File2() throws Exception {
        apitest.getApiUri();
	}

	@When("^the user sents a GET request to register API with valid request$")
	public void the_user_sents_a_POST_request_to_register_API_with_valid_request() throws Exception {
        apitest.requestQuoteAPIWithGetMethod();
	}

	@Then("^API should have status code as (\\d+) and content-type as JSON$")
	public void register_API_should_have_status_code_as_and_content_type_as_JSON(int statusCode) throws Exception {
		assertThat("Verify Content Type for Order Api ", apitest.getContentType(),
				equalTo("application/json; charset=utf-8"));
		assertThat("Verify Status code for Order Api ", apitest.getStatusCode(), equalTo(statusCode));
	}

	@Then("^API should return proper json response and compare both Response$")
	public void API_should_return_proper_json_responseand_compare_both_Response() throws Exception {
		apitest.ValidateJsonResponse();

	}
}
