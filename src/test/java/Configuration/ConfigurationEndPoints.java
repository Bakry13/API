package Configuration;

import static io.restassured.RestAssured.given;

import Utilities.Auth;
import Utilities.Routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ConfigurationEndPoints 
{
	public static final String ConsumptionURL = "https://mobile.vodafone.com.eg/services/dxl/cc/channelConfiguration";
	static String token = "0";
	static Response response = null;
	//=========================Configuration Request for Android platform==================================
	public static Response AndroidConfigurationRequest(String MSISDN, String Password)
	{
		//Get token at first
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
			         .queryParam("channelId","AnaVodafoneAndroid").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	//=========================Configuration Request for IOS platform==================================
	public static Response IOSConfigurationRequest(String MSISDN, String Password)
	{
		//Get token at first
		try {
			token= Auth.getToken(MSISDN, Password);
			 //Then send request
			 RestAssured.baseURI = Routes.BaseURL+Routes.Configuration+"/IOS";
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
			         .queryParam("channelId","AnaVodafoneIOS").get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
//==========================Test consumption==============================================
	public static void main( String[] args )
    {
		Response output= IOSConfigurationRequest("1000678688", "Test@1234");
		System.out.println("Configuration Status code: " + output.getStatusCode());
		System.out.println("Status message " + output.body().asString());
    }
}
