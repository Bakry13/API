package Configuration;

import static io.restassured.RestAssured.given;

import Utilities.Auth;
import Utilities.Routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ConfigurationErrorHandling 
{
	public static String token;
	static Response response = null;
//==================================Unauthorized 401==================================================	
	public static Response configurationRequestWithoutMsisdn(String MSISDN, String Password)
	{ 
		try {
			token= Auth.getToken(MSISDN, Password); 
			//Then send request
			RestAssured.baseURI = Routes.BaseURL+Routes.Configuration+"/Android";
			response = given()
				//Send header parameters
					.header("Content-Type","application/json")
					.header("Accept","application/json")
					.header("api-host","ChannelConfigurationHost")
					.header("access-token",token)
					.queryParam("currentBuildNum","140")
					.queryParam("priceGroupType","CONSUMER")
					.queryParam("customerType","Postpaid")
					.queryParam("contractSubType","DEFAULT")
			        .queryParam("msisdn",MSISDN)
			        .queryParam("lineType","VOICE")
			        .queryParam("channelId","AnaVodafoneAndroid").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==================================Unauthorized 401==================================================
	public static Response configurationRequestWithoutToken(String MSISDN, String Password)
	{    
	    try {
			token= Auth.getToken(MSISDN, Password); 
			//Then send request
			RestAssured.baseURI = Routes.BaseURL+Routes.Configuration+"/Android";
			response = given()
					//Send header parameters
					.header("Content-Type","application/json")
					.header("Accept","application/json")
					.header("msisdn",MSISDN)
					.header("api-host","ChannelConfigurationHost")
					.queryParam("currentBuildNum","140")
					.queryParam("priceGroupType","CONSUMER")
					.queryParam("customerType","Postpaid")
					.queryParam("contractSubType","DEFAULT")
			        .queryParam("msisdn",MSISDN)
			        .queryParam("lineType","VOICE")
			        .queryParam("channelId","AnaVodafoneAndroid").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==================================Not Found 404==================================================	
	public static Response configurationRequestWrongURL(String MSISDN, String Password)
	{     
		try {
			token= Auth.getToken(MSISDN, Password); 
			//Then send request
			RestAssured.baseURI = Routes.BaseURL+Routes.Configuration;
			response = given()
					//Send header parameters
					.header("Content-Type","application/json")
					.header("Accept","application/json")
					.header("msisdn",MSISDN)
					.header("api-host","ChannelConfigurationHost")
					.header("access-token",token)
					.queryParam("currentBuildNum","140")
					.queryParam("priceGroupType","CONSUMER")
					.queryParam("customerType","Postpaid")
					.queryParam("contractSubType","DEFAULT")
			        .queryParam("msisdn",MSISDN)
			        .queryParam("lineType","VOICE")
			        .queryParam("channelId","AnaVodafoneAndroid").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==================================Method Not Allowed 405==================================================
	public static Response configurationPostRequest(String MSISDN, String Password)
	{    
		try {
			token= Auth.getToken(MSISDN, Password); 
			//Then send request
			RestAssured.baseURI = Routes.BaseURL+Routes.Configuration+"/Android";
			response = given()
					//Send header parameters
					.header("Content-Type","application/json")
					.header("Accept","application/json")
					.header("msisdn",MSISDN)
					.header("api-host","ChannelConfigurationHost")
					.header("access-token",token)
					.queryParam("currentBuildNum","140")
					.queryParam("priceGroupType","CONSUMER")
					.queryParam("customerType","Postpaid")
					.queryParam("contractSubType","DEFAULT")
			        .queryParam("msisdn",MSISDN)
			        .queryParam("lineType","VOICE")
			        .queryParam("channelId","AnaVodafoneAndroid").post();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==================================Bad Request 400==================================================
	public static Response configurationWrongQparam(String MSISDN, String Password)
	{    
		try {
			token= Auth.getToken(MSISDN, Password); 
			//Then send request
			RestAssured.baseURI = Routes.BaseURL+Routes.Configuration+"/Android";
			response = given()
					//Send header parameters
					.header("Content-Type","application/json")
					.header("Accept","application/json")
					.header("msisdn",MSISDN)
					.header("api-host","ChannelConfigurationHost")
					.header("access-token",token)
					.queryParam("currentBuildNum","140")
					.queryParam("priceGroupType","CONSUMER")
					.queryParam("customerType","Postpaid")
					.queryParam("contractSubType","DEFAULT")
			        .queryParam("msisdn",MSISDN+1)
			        .queryParam("lineType","VOICE")
			        .queryParam("channelId","AnaVodafoneAndroid").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==================================Internal Server Error 500=========================================	
	public static Response configurationMissingQParamtRequest(String MSISDN, String Password)
	{    
		try {
			token= Auth.getToken(MSISDN, Password); 
			//Then send request
			RestAssured.baseURI = Routes.BaseURL+Routes.Configuration+"/Android";
			response = given()
					//Send header parameters
					.header("Content-Type","application/json")
					.header("Accept","application/json")
					.header("msisdn",MSISDN)
					.header("api-host","ChannelConfigurationHost")
					.header("access-token",token)
					.queryParam("currentBuildNum","140")
					.queryParam("priceGroupType","CONSUMER")
					.queryParam("contractSubType","DEFAULT")
					.queryParam("msisdn",MSISDN)
			        .queryParam("lineType","VOICE")
			        .queryParam("channelId","AnaVodafoneAndroid").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==================================Unsupported Media type 415=========================================
	public static Response configurationWithoutContentType(String MSISDN, String Password)
	{
		try {
			token= Auth.getToken(MSISDN, Password); 
			//Then send request
			RestAssured.baseURI = Routes.BaseURL+Routes.Configuration+"/Android";
			response = given()
					//Send header parameters
					.header("Accept","application/json")
					.header("msisdn",MSISDN)
					.header("api-host","ChannelConfigurationHost")
					.header("access-token",token)
					.queryParam("currentBuildNum","140")
					.queryParam("priceGroupType","CONSUMER")
					.queryParam("customerType","Postpaid")
					.queryParam("contractSubType","DEFAULT")
			        .queryParam("msisdn",MSISDN)
			        .queryParam("lineType","VOICE")
			        .queryParam("channelId","AnaVodafoneAndroid").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//==================================Forbidden 403=========================================
	public static Response configurationWithDifferentMSISDN(String MSISDN, String Password)
	{    
		try {
			token= Auth.getToken(MSISDN, Password); 
			//Then send request
			RestAssured.baseURI = Routes.BaseURL+Routes.Configuration+"/Android";
			response = given()
					//Send header parameters
					.header("Content-Type","application/json")
					.header("Accept","application/json")
					.header("msisdn",MSISDN)
					.header("api-host","ChannelConfigurationHost")
					.header("access-token",token)
					.queryParam("currentBuildNum","140")
					.queryParam("priceGroupType","CONSUMER")
					.queryParam("customerType","Postpaid")
					.queryParam("contractSubType","DEFAULT")
			        .queryParam("msisdn","1032322955")
			        .queryParam("lineType","VOICE")
			        .queryParam("channelId","AnaVodafoneAndroid").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
//===================================================================================================
	public static void main( String[] args )
    {
		Response output=configurationWithDifferentMSISDN("1000678688", "Test@1234");
		System.out.println("Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
    }
}
