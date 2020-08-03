package FlexCoins;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import Utilities.AuthWeb;
import Utilities.Routes;

public class FlexLoyaltyEndpoints {
	public static final String LoyaltyURL = "https://web.vodafone.com.eg/services/dxl/loyaltymng/loyaltyProgramMember";
	static Response response = null;
	public static Response loyaltyRequest(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 String token;
			 token= AuthWeb.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.WebBaseURL+Routes.Loyalty;
			 response = given()
					 //Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","LoyaltyManagementHost")
					 .header("Authorization","Bearer"+ " " +  token)
					 .pathParam("MSISDN", MSISDN).get("/{MSISDN}/loyaltyProgramProduct");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==========================Test loyalty==============================================
	public static void main( String[] args )
    {
		Response output=loyaltyRequest("01008308223","Test@1234");
		System.out.println("Loyalty Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
    }
}
