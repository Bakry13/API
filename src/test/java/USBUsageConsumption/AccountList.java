package USBUsageConsumption;

    import io.restassured.RestAssured;
    import io.restassured.response.Response;

    import static io.restassured.RestAssured.given;

    import org.json.simple.JSONObject;

    import Utilities.AuthWeb;
	import Utilities.Routes;

	public class AccountList {
		
		public static  Response GetAccountList(String MSISDN, String Password, String CustomerID )
		{    
			//Get token at first
			 String token;
		     token= AuthWeb.getToken(MSISDN, Password);
		     System.out.println(token);
		     //Then send request
			 RestAssured.baseURI = Routes.WebBaseURL+Routes.AccountList;
			
			 JSONObject requestParams = new JSONObject();		
			 requestParams.put("customerId", CustomerID);
			 requestParams.put("msisdn", MSISDN);
			 	 
		/* String requestBody = "{\n" +
			            "  \"customerId\": \"207564191\",\n" +
				 
			            "  \"msisdn\": \"01013456722\"  ,\n" +
			            "}";*/		
			 
		 //Send header parameters
			       Response response = given()
					  
		    			.header("Content-Type","application/json")
						.header("Accept","application/json")
						.header("msisdn", MSISDN)
		    			.header("Authorization","Bearer"+ " " +  token)
		    			.header("Accept-Language" , "EN")   			
		    //Send body in Data Form type
		    			.body(requestParams.toJSONString())
		    			.post(); //Make post action	            
		    			   			
	        System.out.println("AccountList code is "+ response.getStatusCode());
	        return response;
		}
	//==========================Get all MSISDNs in the list ==============================================
		public static void main( String[] args )
	    {
			Response output= GetAccountList("01091293312","Test@1234","207153324");
			System.out.println("Account list Status code: " + output.getStatusCode());
			System.out.println("Status message " + output.body().asString() );
			
	
	    }
	}
	
