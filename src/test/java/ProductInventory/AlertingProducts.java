package ProductInventory;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AlertingProducts 
{
	String Type = "x";
	String ProductIndex = "0";
	public static int producIndex = 0;
	int ResponseSize = 0;
	//----------------------Service data---------------------
	public String Name = "does not exist";
	public String Description = "does not exist";
	public String IndexName = "does not exist";
	public String CategoryValue = "does not exist";
	public String ChargePeriod = "does not exist";
	public String PriceValue = "does not exist";
	public String PriceUnit = "does not exist";
//=======================Constructor to extract values from response=================
	public AlertingProducts(Response response, String ProductName)
	{ 
		try {
			String jsonString = response.asString(); //Convert response to string
			List<String> ResponseList = response.jsonPath().getList("$"); //save response in list to get its size
			ResponseSize = ResponseList.size(); //Get size of response list
			//------------Start Loop on products to set its values---------------
			for (int ProductIterator = 0; ProductIterator < ResponseSize; ProductIterator++)
			{
				ProductIndex = Integer.toString(ProductIterator);
				IndexName = JsonPath.from(jsonString).get("productSpecification.name["+ProductIndex+"]");
				//---------------------------Search for the given product name-------------------------------
				if (IndexName.equals(ProductName))
				{
					Name = JsonPath.from(jsonString).get("productSpecification.name["+ProductIndex+"]");
					Description = JsonPath.from(jsonString).get("description["+ProductIndex+"]"); //Description
					CategoryValue = JsonPath.from(jsonString).get("characteristic["+ProductIndex+"].value[2]"); //Category name
					ChargePeriod = JsonPath.from(jsonString).get("productPrice["+ProductIndex+"].recurringChargePeriod[0]"); //Charge period
					PriceValue = JsonPath.from(jsonString).get("productPrice["+ProductIndex+"].price.taxIncludedAmount.value[0]"); //Price value
					PriceUnit = JsonPath.from(jsonString).get("productPrice["+ProductIndex+"].price.taxIncludedAmount.unit[0]"); //Price unite
				}
			} //End of products loop
		} catch (NumberFormatException e) {
			e.printStackTrace(); 
		}
	} //End of constructor
//=================================Test==================================
	public static void main( String[] args )
    {
		Response response= ProductEndPoints.AlertingArabicRequest("1000678688", "Test@1234");
		AlertingProducts Obj = new AlertingProducts(response,"سالي فؤاد");
		
		System.out.println("-------Print Service Data------");
		System.out.println("Name "+Obj.Name);
		System.out.println("Description "+Obj.Description);
		System.out.println("CategoryValue "+Obj.CategoryValue);
		System.out.println("ChargePeriod "+Obj.ChargePeriod);
		System.out.println("PriceValue "+Obj.PriceValue);
		System.out.println("PriceUnit "+Obj.PriceUnit);
    }
}
