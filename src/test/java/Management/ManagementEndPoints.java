package Management;

import static io.restassured.RestAssured.given;

import Utilities.Auth;
import Utilities.Routes;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ManagementEndPoints {
	static String token;
	static Response response = null;
	public static Response managementRequest(String MSISDN, String Password)
	{
		//Get token at first
		try {
			token= Auth.getToken(MSISDN, Password);
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Management;
			 response = given()
					//Send header parameters
					 .header("msisdn",MSISDN)
					 .header("api-host","UsageManagementHost")
					 .header("access-token",token)
					 .queryParam("@type","Aggregated") 
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==========================Test consumption==============================================
	public static void main( String[] args )
    {
		Response output=managementRequest("1012450274", "Test@1234");
		System.out.println(" Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
    }
}
