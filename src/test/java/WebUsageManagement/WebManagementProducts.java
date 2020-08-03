package WebUsageManagement;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Utilities.Verification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WebManagementProducts 
{
	String Type = "x";
	String ProductIndex = "0";
	int ResponseSize = 0;
	Map<String, Float> typesandvalues = new HashMap<>();
	public boolean exists = true;
	
	//----------------------Used quota---------------------
	public float UsedMinutes = 123456;
	public float UsedSMS = 123456;
	public float Usedvalues = 123456;
	//public String type="0";
//=======================Constructor to extract values from response=================
	public WebManagementProducts(Response response)
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
	
		Usedvalues = JsonPath.from(jsonString).get("ratedProductUsage["+ProductIndex+"].taxIncludedRatingAmount[0]");
		//Float.parseFloat((String)
		
		typesandvalues.put(Type, Usedvalues);
		
		// exists = typesandvalues.containsValue("CONTENT");
		// System.out.println(exists);
		
		//System.out.println(Type + "=" + Usedvalues );
	}}
	
	/*	//------------Start Loop on products to set its values---------------
		for (int ProductIterator = 0; ProductIterator < ResponseSize; ProductIterator++)
		{
			ProductIndex = Integer.toString(ProductIterator);
			Type = JsonPath.from(jsonString).get("type["+ProductIndex+"]");
			//----------------------------------Search for Voice products----------------------------------
			if (Type.equals("VOICE"))
			{
				UsedMinutes = Float.parseFloat((String) JsonPath.from(jsonString).get("usageCharacteristic["+ProductIndex+"].value[0]"));
			}
			//----------------------------------Search for SMS products----------------------------------
			else if (Type.equals("SMS"))
			{
				UsedSMS = Float.parseFloat((String) JsonPath.from(jsonString).get("usageCharacteristic["+ProductIndex+"].value[0]"));
			}
		} //End of products loop
	} //End of constructor*/
//=================================Test==================================
	public static void main( String[] args )
    {
		Response response= WebManagementEndPoints.managementRequest("01030026166", "Test@1234");
		WebManagementProducts Obj = new WebManagementProducts(response);
	
		for( Entry<String, Float> typesandvalues : Obj.typesandvalues.entrySet() ){

			System.out.println(typesandvalues.getKey() + "=" + typesandvalues.getValue());
			
			}
		
		try {
			Verification.VerifyFloat(Obj.typesandvalues.get("Content"), 0f, "success","failure");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("fkfkhfhfkh");
		
		/*System.out.println("-------Print Minutes products------");
		System.out.println("UsedMinutes "+Obj.UsedMinutes);
		
		System.out.println("-------Print SMS products------");
		System.out.println("UsedSMS "+Obj.UsedSMS);*/
    }
}