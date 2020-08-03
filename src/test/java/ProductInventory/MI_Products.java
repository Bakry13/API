package ProductInventory;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MI_Products {
	
	String Type = "x";
	String ProductIndex = "0";
	String ProductTermIndex = "0";
	public static int producIndex = 0;
	int ResponseSize = 0;
	int ProductTermSize = 0;
	//----------------------Service data---------------------
	public String Id = "does not exist";
	public String Name = "does not exist";
	public String PriceValue = "does not exist";
	public String PriceUnit = "does not exist";
	public String IndexName = "does not exist";
	
	public float[] Amount = {1234, 1234, 1234};
	public float[] Consumed = {1234, 1234, 1234};
	public float[] Total =  {1234, 1234, 1234};
	public String[] Units = {"does not exist", "does not exist", "does not exist", "does not exist"};
	public String[] Term_Type = {"does not exist", "does not exist", "does not exist", "does not exist"};
//=======================Constructor to extract values from response=================
	public MI_Products(Response response, String ProductName)
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
				Id= JsonPath.from(jsonString).get("id["+ProductIndex+"]");
				//---------------------------Search for the given product name-------------------------------
				if (IndexName.equals(ProductName))
				{
					Name = JsonPath.from(jsonString).get("productSpecification.name["+ProductIndex+"]");
					PriceValue = JsonPath.from(jsonString).get("productPrice["+ProductIndex+"].price.taxIncludedAmount.value[0]"); //Price value
					PriceUnit = JsonPath.from(jsonString).get("productPrice["+ProductIndex+"].unitOfMeasure[0]"); //Price unite
					if (!(Id.equals("400")||Id.equals("414"))) //In case the Internet product NOT "Stop internt renewable or other ABO product"
					{
						List<String> ProductTermList = response.jsonPath().getList("productTerm["+ProductIndex+"]");
						ProductTermSize = ProductTermList.size(); //Get size of Product term list
						//----------------------Loop on product term like superpass or video content-----------------
						for (int ProductTermIterator = 0; ProductTermIterator < ProductTermSize; ProductTermIterator++)
						{
							ProductTermIndex = Integer.toString(ProductTermIterator);
							Units[ProductTermIterator] = JsonPath.from(jsonString).get("productTerm["+ProductIndex+"].quota.units["+ProductTermIndex+"]"); // Bundle unit
							Consumed[ProductTermIterator] = JsonPath.from(jsonString).get("productTerm["+ProductIndex+"].quota.consumed["+ProductTermIndex+"]");  // consumed Bundle
							Term_Type[ProductTermIterator] = JsonPath.from(jsonString).get("productTerm["+ProductIndex+"].'@type'["+ProductTermIndex+"]");
							if ( (!(Units[ProductTermIterator].equals("Super Mega") || Units[ProductTermIterator].equals("سوبر ميجا"))) || ProductTermIterator==0  )
							{
                            	   Amount[ProductTermIterator] = JsonPath.from(jsonString).get("productTerm["+ProductIndex+"].quota.amount["+ProductTermIndex+"]");  //Bundle amount
       							   Total[ProductTermIterator] = JsonPath.from(jsonString).get("productTerm["+ProductIndex+"].quota.total["+ProductTermIndex+"]");  //Total Bundle 
                            }
						}
				    }
				}	
			} //End of products loop
		} catch (NumberFormatException e) {
			e.printStackTrace(); 
		}
	} //End of constructor
//=================================Test==================================
	public static void main( String[] args )
    {
 //  	Response response= ProductEndPoints.MIRequest("1060991916", "Test@1234");
//		MI_Products Obj = new MI_Products(response,"SuperPass Streaming 15LE 1500 SuperMB");
	    
		Response response= ProductEndPoints.MIArabicRequest("1060991916", "Test@1234");
		MI_Products Obj = new MI_Products(response,"باقةSuperPassستريمنج15ج-1500SuperMB ");	
		System.out.println("-------Print Service Data------");
		System.out.println("Name "+Obj.Name);
		System.out.println("PriceValue "+Obj.PriceValue);
		System.out.println("PriceUnit "+Obj.PriceUnit);
		System.out.println("Amount: "+Obj.Amount[0]+", "+Obj.Amount[1]+", "+Obj.Amount[2]);
		System.out.println("Consumed: "+Obj.Consumed[0]+", "+Obj.Consumed[1]+", "+Obj.Consumed[2]);
		System.out.println("Total: "+Obj.Total[0]+", "+Obj.Total[1]+", "+Obj.Total[2]);	
		System.out.println("Consumed: "+Obj.Units[0]+", "+Obj.Units[1]+", "+Obj.Units[2]);
		System.out.println("type: "+Obj.Term_Type[0]+", "+Obj.Term_Type[1]+", "+Obj.Term_Type[2]);
    }
}



