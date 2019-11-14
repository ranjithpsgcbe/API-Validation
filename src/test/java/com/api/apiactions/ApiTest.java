package com.api.apiactions;

import com.api.utils.ReadFileUtil;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.exceptions.SerenityManagedException;
import net.thucydides.core.annotations.Step;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiTest {

	public Response response;


	private List<String> file1;
	private List<String> file2;
	private String dataOne, dataTwo;
	private List<String> response1List;
	private List<String> response2List;



	
	@Step
	public void getApiUri() throws Exception {

		String currentDirectory = System.getProperty("user.dir");
		file1 = readFiles(new File(currentDirectory+"/src/test/resources/File1.txt"));
		file2 = readFiles(new File(currentDirectory+"/src/test/resources/File2.txt"));

	}

	@Step
	public void requestQuoteAPIWithGetMethod() throws  Exception {

		response1List = new ArrayList<>();
		response2List = new ArrayList<>();
		if(file1 != null && file2 != null) {
			for (int i = 0; i< file1.size(); i++) {

				try {
					response = given().contentType("application/json").when()
							.get(file1.get(i));
				}catch(SerenityManagedException e){
					System.err.println("ouch!");
				}

				dataOne = response.asString();
				response1List.add(dataOne);




				try {
					response = given().contentType("application/json").when()
							.get(file2.get(i));
				}catch(Exception e){

				}

				dataTwo = response.asString();
				response2List.add(dataTwo);


			}
		}



	}
	public List<String> readFiles(File file) {

		return ReadFileUtil.readFiles(file);
	}

	@Step
	public void ValidateJsonResponse() throws Exception {

		if (null != response1List && null != response2List) {
			for (int i = 0; i < response1List.size(); i++) {




				if (StringUtils.equals(response1List.get(i), response2List.get(i))) {




					Serenity.recordReportData().asEvidence().withTitle("Equals").andContents("Below File1 & File2 APIs Response are Equal");
					Serenity.recordReportData().withTitle("File1 API").andContents(response1List.get(i));
					Serenity.recordReportData().withTitle("File2 API").andContents(response2List.get(i));

							System.out.println("Equals");

				} else {
					Serenity.recordReportData().asEvidence().withTitle("Not Equals").andContents("Below FIle1 & File2 APIs Response are Not Equals");
					Serenity.recordReportData().withTitle("File1 API").andContents(response1List.get(i));
					Serenity.recordReportData().withTitle("File2 API").andContents(response2List.get(i));
					System.out.println("Not Equals");
				}

			}
		}
	}
	@Step
	public int getStatusCode() throws Exception {
		return response.then().extract().statusCode();
	}

	@Step
	public String getContentType() throws Exception {
		return response.then().extract().contentType();
	}
}
