package Management;

import static io.restassured.RestAssured.given;

import Utilities.Auth;
import Utilities.Routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ManagementErrorHandling {
	
	public static String token;
	static Response response = null;
//==================================Unauthorized 401==================================================	
	public static Response managementRequestWithoutMsisdn(String MSISDN, String Password)
	{    
		try {
			token= Auth.getToken(MSISDN, Password); 
			 RestAssured.baseURI = Routes.BaseURL+Routes.Management;
			 response = given()
						//Send header parameters				
						 .header("api-host","UsageManagementHost")
						 .header("access-token",token)
						 .queryParam("@type","Aggregated") 
			             .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Unauthorized 401==================================================
	public static Response managementRequestWithoutToken(String MSISDN, String Password)
	{    
	     try {
			token= Auth.getToken(MSISDN, Password); 
			 RestAssured.baseURI = Routes.BaseURL+Routes.Management;
			 response = given()
						//Send header parameters
						 .header("msisdn",MSISDN)
						 .header("api-host","UsageManagementHost")
						 .queryParam("@type","Aggregated")
			             .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Not Found 404==================================================	
	public static Response managementRequestWrongURL(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Management+"m";
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
//==================================Method Not Allowed 405==================================================
	public static Response managementpatchRequest(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Management;
			 response = given()
					//Send header parameters
					 .header("msisdn",MSISDN)
					 .header("api-host","UsageManagementHost")
					 .header("access-token",token)
					 .queryParam("@type","Aggregated") 
			         .queryParam("relatedParty.id",MSISDN).patch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Bad Request 400==================================================
	public static Response managementWrongQparam(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Management;
			 response = given()
					//Send header parameters
					 .header("msisdn",MSISDN)
					 .header("api-host","UsageManagementHost")
					 .header("access-token",token)
					 .queryParam("@type","Aggregated") 
			         .queryParam("relatedParty.id",MSISDN+1).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Internal Server Error 500=========================================	
	public static Response managementMissingQParamtRequest(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Management;
			 response = given()
					//Send header parameters
					 .header("msisdn",MSISDN)
					 .header("api-host","UsageManagementHost")
					 .header("access-token",token)
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Unsupported Media type 415=========================================
	public static Response managementWithoutContentType(String MSISDN, String Password)
	{
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Consumption;
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
//==================================Forbidden 403=========================================
	public static Response managementWithDifferentMSISDN(String MSISDN, String Password)
	{
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Management;
			 response = given()
					//Send header parameters
					 .header("msisdn",MSISDN)
					 .header("api-host","UsageManagementHost")
					 .header("access-token",token)
					 .queryParam("@type","Aggregated") 
			         .queryParam("relatedParty.id","1032322955").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//===================================================================================================
	public static void main( String[] args )
    {
		Response output=managementRequestWrongURL("1099759221", "Test@1234");
		System.out.println("Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
	}
}
