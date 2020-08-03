package ProductInventory;

import static io.restassured.RestAssured.given;

import Utilities.Auth;
import Utilities.Routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AlertingErrorHandling 
{
	public static String token;
	static Response response = null;
	//==================================Unauthorized 401==================================================	
	public static Response AlertingRequestWithoutMsisdn(String MSISDN, String Password)
	{   
		try {
			token= Auth.getToken(MSISDN, Password); 
			//Then send request
			RestAssured.baseURI = Routes.BaseURL+Routes.ProductInventory;
			response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("api-host","ProductInventoryManagementHost")
					 .header("access-token",token)
					 .header("Accept-Partial","Y")
					 .header("Accept-Language","AR")
					 .queryParam("@type","AlertingServices") 
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Unauthorized 401==================================================
	public static Response AlertingRequestWithoutToken(String MSISDN, String Password)
	{    
		try {
			token= Auth.getToken(MSISDN, Password); 
			    //Then send request
			    RestAssured.baseURI = Routes.BaseURL+Routes.ProductInventory;
			 	response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","ProductInventoryManagementHost")
					 .header("Accept-Partial","Y")
					 .header("Accept-Language","AR")
					 .queryParam("@type","AlertingServices") 
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Not Found 404==================================================	
	public static Response AlertingRequestWrongURL(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.ProductInventory+"m";
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","ProductInventoryManagementHost")
					 .header("access-token",token)
					 .header("Accept-Partial","Y")
					 .header("Accept-Language","AR")
					 .queryParam("@type","AlertingServices") 
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Method Not Allowed 405==================================================
	public static Response AlertingpostRequest(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.ProductInventory;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","ProductInventoryManagementHost")
					 .header("access-token",token)
					 .header("Accept-Partial","Y")
					 .header("Accept-Language","AR")
					 .queryParam("@type","AlertingServices") 
			         .queryParam("relatedParty.id",MSISDN).post();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Bad Request 400==================================================
	public static Response AlertingWrongQparam(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.ProductInventory;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","ProductInventoryManagementHost")
					 .header("access-token",token)
					 .header("Accept-Partial","Y")
					 .header("Accept-Language","AR")
					 .queryParam("@type","AlertingServices") 
			         .queryParam("relatedParty.id",MSISDN+1).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Internal Server Error 500=========================================	
	public static Response AlertingMissingQParamtRequest(String MSISDN, String Password)
	{    
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.ProductInventory;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","ProductInventoryManagementHost")
					 .header("access-token",token)
					 .header("Accept-Partial","Y")
					 .header("Accept-Language","AR")
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return response;
	}
//==================================Unsupported Media type 415=========================================
	public static Response AlertingWithoutContentType(String MSISDN, String Password)
	{
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.ProductInventory;
			 response = given()
					//Send header parameters
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","ProductInventoryManagementHost")
					 .header("access-token",token)
					 .header("Accept-Partial","Y")
					 .header("Accept-Language","AR")
					 .queryParam("@type","AlertingServices") 
			         .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==================================Forbidden 403=========================================
	public static Response AlertingWithDifferentMSISDN(String MSISDN, String Password)
	{
		try {
			//Get token at first
			 token= Auth.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.ProductInventory;
			 response = given()
					//Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","ProductInventoryManagementHost")
					 .header("access-token",token)
					 .header("Accept-Partial","Y")
					 .header("Accept-Language","AR")
					 .queryParam("@type","AlertingServices") 
			         .queryParam("relatedParty.id","1032322955").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
