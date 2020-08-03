package USBUsageConsumption;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.List;

import Utilities.AuthWeb;
import Utilities.Routes;

public class USBConsumptionEndpoints {
	//public static final String LoyaltyURL = "https://web.vodafone.com.eg/services/dxl/loyaltymng/loyaltyProgramMember";
	static String token;
	static Response response = null;
	static String ProductIndex = "0";
	
	public static Response USBConsumptionrequest(String MSISDN, String Password, String DataSIM)
	{    
		try {
			//Get token at first
			 token= AuthWeb.getToken(MSISDN, Password); 
			 //Then send request
			 RestAssured.baseURI = Routes.WebBaseURL+Routes.Consumption;
			 response = given()
					 //Send header parameters
					 .header("Content-Type","application/json")
					 .header("Accept","application/json")
					 .header("msisdn",MSISDN)
					 .header("api-host","usageConsumptionHost")
					 .header("Authorization","Bearer"+ " " +  token)
					 .queryParam("@type","USB") 
			         .queryParam("bucket.product.publicIdentifier",DataSIM).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return response;
	}
//========================== Get usage of MSISDNs that has role Data ==============================================
	public static void main( String[] args )
    {
		Response response= AccountList.GetAccountList("01013456722", "Test@1234","207564191");
		List<String> Msisdns = GetDataLines_update.GetDataLines(response);
		
	for (int ProductIterator = 0; ProductIterator < Msisdns.size(); ProductIterator++)
		{
		Response output= USBConsumptionrequest("01013456722", "Test@1234", Msisdns.get(ProductIterator));
			System.out.println(Msisdns.get(ProductIterator));
	
		System.out.println("Consumption Status code: " + output.getStatusCode());
		//System.out.println("Status message " + output.body().asString());
		float RemainingQuota = JsonPath.from(output.body().asString()).get("[1].bucket[0].bucketBalance[0].remainingValue.amount");
		System.out.println(RemainingQuota); 
		}}
}
