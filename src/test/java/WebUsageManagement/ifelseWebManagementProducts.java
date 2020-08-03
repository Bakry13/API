package WebUsageManagement;


import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ifelseWebManagementProducts 
{
	String Type = "x";
	String ProductIndex = "0";
	int ResponseSize = 0;
	//----------------------Used quota---------------------
	public float VoiceOnnetUsedFlexes = 123456;
	public float VoiceOnnetExtraUsedFlexes = 123456;
	public float VoiceXnetUsedFlexes = 123456;
	public float VoiceXnetExtraUsedFlexes = 123456;
	public float SMSOnnetUsedFlexes = 123456;
	public float SMSOnnetExtraUsedFlexes = 123456;
	public float SMSXnetUsedFlexes = 123456;
	public float SMSXnetExtraUsedFlexes = 123456;
	public float MIUsedFlexes = 123456;
	public float MIExtraUsedFlexes = 123456;
	public float VoiceNationalUsedFlexes = 123456;
	public float VoiceNationalExtraUsedFlexes = 123456;
	public float ContentUsedFlexes = 123456;
	public float RBTUsedFlexes = 123456;
//=======================Constructor to extract values from response=================
	public ifelseWebManagementProducts(Response response)
	{
		String jsonString = response.asString(); //Convert response to string
		List<String> ResponseList = response.jsonPath().getList("$"); //save response in list to get its size
		ResponseSize = ResponseList.size(); //Get size of response list
		System.out.println(ResponseSize);
		//------------Start Loop on products to set its values---------------
		for (int ProductIterator = 0; ProductIterator < ResponseSize; ProductIterator++)
		{
			ProductIndex = Integer.toString(ProductIterator);
			Type = JsonPath.from(jsonString).get("type["+ProductIndex+"]");
			//----------------------------------Search for Voice products----------------------------------
			if (Type.equals("VOICE_XNET"))
			{
				VoiceXnetUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			//----------------------------------Search for SMS products----------------------------------
			else if (Type.equals("SMS_ONNET"))
			{
				SMSOnnetUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			
			else if (Type.equals("VOICE_NATIONAL_EXTRA"))
			{
				VoiceNationalExtraUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			//----------------------------------Search for SMS products----------------------------------
			else if (Type.equals("SMS_ONNET_EXTRA"))
			{
				SMSOnnetExtraUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			else if (Type.equals("RBT"))
			{
				RBTUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			//----------------------------------Search for SMS products----------------------------------
			else if (Type.equals("VOICE_XNET_EXTRA"))
			{
				VoiceXnetExtraUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			else if (Type.equals("VOICE_ONNET_EXTRA"))
			{
				VoiceOnnetExtraUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			//----------------------------------Search for SMS products----------------------------------
			else if (Type.equals("VOICE_ONNET"))
			{
				VoiceOnnetUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			
			else if (Type.equals("CONTENT"))
			{
				ContentUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			//----------------------------------Search for SMS products----------------------------------
			else if (Type.equals("SMS_XNET"))
			{
				SMSXnetUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
		   else if (Type.equals("SMS_XNET_EXTRA"))
			{
				SMSXnetExtraUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			else if (Type.equals("MI"))
			{
				MIUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			//----------------------------------Search for SMS products----------------------------------
			else if (Type.equals("MI_EXTRA"))
			{
				MIExtraUsedFlexes = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
			}
			
		
				
		} //End of products loop
	} //End of constructor
//=================================Test==================================
	public static void main( String[] args )
    {
		Response response= WebManagementEndPoints.managementRequest("01030026166", "Test@1234");
		ifelseWebManagementProducts Obj = new ifelseWebManagementProducts(response);
		
		System.out.println("-------Print Voice on-net products------");
		System.out.println("VoiceOnnetUsedFlexes "+Obj.VoiceOnnetUsedFlexes);
		
		System.out.println("-------Print Voice on-net Extra products------");
		System.out.println("VoiceOnnetExtraUsedFlexes "+Obj.VoiceOnnetExtraUsedFlexes);
		
		System.out.println("-------Print Voice X-net products------");
		System.out.println("VoiceXnetUsedFlexes "+Obj.VoiceXnetUsedFlexes);
		
		System.out.println("-------Print Voice X-net Extra products------");
		System.out.println("VoiceXnetExtraUsedFlexes "+Obj.VoiceXnetExtraUsedFlexes);
		
		//------------------------
		System.out.println("-------Print SMS on-net products------");
		System.out.println("SMSOnnetUsedFlexes "+Obj.SMSOnnetUsedFlexes);
		
		System.out.println("-------Print SMS on-net Extra products------");
		System.out.println("SMSOnnetExtraUsedFlexes "+Obj.SMSOnnetExtraUsedFlexes);
		
		//------------------------
		System.out.println("-------Print SMS X-net products------");
		System.out.println("SMSXnetUsedFlexes "+Obj.SMSXnetUsedFlexes);
				
		System.out.println("-------Print SMS x-net Extra products------");
		System.out.println("SMSXnetExtraUsedFlexes "+Obj.SMSXnetExtraUsedFlexes);
		///////////////
		System.out.println("-------Print MI products------");
		System.out.println("MIUsedFlexes "+Obj.MIUsedFlexes);
		
		System.out.println("-------Print MI Extra products------");
		System.out.println("MIExtraUsedFlexes "+Obj.MIExtraUsedFlexes);
		
		//------------------------
		System.out.println("-------Print Voice-National products------");
		System.out.println("VoiceNationalUsedFlexes "+Obj.VoiceNationalUsedFlexes);
		
		System.out.println("-------Print Voice-National Extra products------");
		System.out.println("VoiceNationalExtraUsedFlexes "+Obj.VoiceNationalExtraUsedFlexes);
		
		//------------------------
		System.out.println("-------Print Content products------");
		System.out.println("ContentUsedFlexes "+Obj.ContentUsedFlexes);
				
		System.out.println("-------Print RBT products------");
		System.out.println("RBTUsedFlexes "+Obj.RBTUsedFlexes);
    }
	
}