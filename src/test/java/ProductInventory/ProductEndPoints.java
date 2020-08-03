package ProductInventory;

import static io.restassured.RestAssured.given;

import Utilities.Auth;
import Utilities.Routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductEndPoints 
{
	public static final String LoyaltyURL = "https://mobile.vodafone.com.eg/services/dxl/pim/product";
	static String token;
	static Response response = null;
	//-------------------------Alerting Services English request------------------------------
	public static Response AlertingRequest(String MSISDN, String Password)
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
					 .header("Accept-Language","EN")
					 .queryParam("@type","AlertingServices") 
			         .queryParam("relatedParty.id",MSISDN).get();
			System.out.println("Product Inventory Status code: " + response.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	//-------------------------Alerting Services Arabic request------------------------------
	public static Response AlertingArabicRequest(String MSISDN, String Password)
	{
		//Get token at first
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
					 .header("access-token",token)
					 .header("Accept-Partial","Y")
					 .header("Accept-Language","AR")
					 .queryParam("@type","AlertingServices") 
			         .queryParam("relatedParty.id",MSISDN).get();
			System.out.println("Product Inventory Status code: " + response.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	//-------------------------MI English request------------------------------
	public static Response MIRequest(String MSISDN, String Password)
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
					 .header("useCase","MIProfile")
					 .header("Accept-Language","EN")
					 .queryParam("place.@referredType","Local") 
					 .queryParam("@type","MIProfile") 
			         .queryParam("relatedParty.id",MSISDN).get();
			System.out.println("Product Inventory Status code: " + response.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	//-------------------------MI Arabic request------------------------------
	public static Response MIArabicRequest(String MSISDN, String Password)
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
					 .header("useCase","MIProfile")
					 .header("Accept-Language","AR")
					 .queryParam("place.@referredType","Local") 
					 .queryParam("@type","MIProfile") 
			        .queryParam("relatedParty.id",MSISDN).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Product Inventory Status code: " + response.getStatusCode());
		return response;
	}
//==========================Test consumption==============================================
	public static void main( String[] args )
    {
		//Response output = AlertingRequest("1000678688", "Test@1234");
		//System.out.println("Product Inventory Status code: " + output.getStatusCode());
		//System.out.println("Status message " + output.body().asString());
		Response output1 = MIRequest("1000678688", "Test@1234");
		System.out.println("Product Inventory Status code: " + output1.getStatusCode());
		System.out.println("Status message " + output1.body().asString());
    }
}
