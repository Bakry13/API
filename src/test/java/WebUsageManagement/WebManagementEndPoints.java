package WebUsageManagement;

import static io.restassured.RestAssured.given;

import Utilities.AuthWeb;
import Utilities.Routes;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WebManagementEndPoints {
	static String token;
	static Response response = null;
	public static Response managementRequest(String MSISDN, String Password)
	{
		try {
			//Get token at first
			 token= AuthWeb.getToken(MSISDN, Password);
			 //Then send request (Get Request)
			 RestAssured.baseURI = Routes.WebBaseURL+Routes.Management;
			 response = given()
					//Send header parameters
					 .header("msisdn",MSISDN)
					 .header("api-host","UsageManagementHost")
					 .header("Authorization","Bearer"+ " " +  token)
					 .queryParam("@type","DetailedAggregated") 
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==========================Test Usage ==============================================
	public static void main( String[] args )
    {
		Response output=managementRequest("01030026166", "Test@1234");
		System.out.println(" Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
    }
}
