package Management;

import java.util.List;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ManagementProducts 
{
	String Type = "x";
	String ProductIndex = "0";
	int ResponseSize = 0;
	//----------------------Used quota---------------------
	public float UsedMinutes = 123456;
	public float UsedSMS = 123456;
//=======================Constructor to extract values from response=================
	public ManagementProducts(Response response)
	{
		try {
			String jsonString = response.asString(); //Convert response to string
			List<String> ResponseList = response.jsonPath().getList("$"); //save response in list to get its size
			ResponseSize = ResponseList.size(); //Get size of response list
			//------------Start Loop on products to set its values---------------
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
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	} //End of constructor
//=================================Test==================================
	public static void main( String[] args )
    {
		Response response= ManagementEndPoints.managementRequest("1000289747", "Test@1234");
		ManagementProducts Obj = new ManagementProducts(response);
		
		System.out.println("-------Print Minutes products------");
		System.out.println("UsedMinutes "+Obj.UsedMinutes);
		
		System.out.println("-------Print SMS products------");
		System.out.println("UsedSMS "+Obj.UsedSMS);
    }
}