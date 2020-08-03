package USBUsageConsumption;

import java.util.List;

import io.restassured.response.Response;

public class GetDataLines_update 
{
	String Type = "x";
	String ProductIndex = "0";
	int ResponseSize = 0;
	String MSISDN ="C";

//=======================Constructor to extract values from response=================
/*	public GetDataLines_update(Response response)
	{
		String jsonString = response.asString(); //Convert response to string
		String roleData = "ROLE_DATA";
		List<String> ResponseList = response.jsonPath().getList("msisdnDto.findAll {x ->x.roleDTOS.findAll{ role -> role.name == 'ROLE_DATA'}}.msisdn"); //save response in list to get its size
		ResponseSize = ResponseList.size(); //Get size of response list
		System.out.println(ResponseSize);
		//------------Start Loop on products to set its values---------------
		for (int ProductIterator = 0; ProductIterator < ResponseList.size(); ProductIterator++)
			{
			String DataLine =ResponseList.get(ProductIterator);
			System.out.println("Data line" + " "+ ProductIterator + " ="+ DataLine);
			}
	}
			//System.out.println(Obj.ResponseSize);*/	
	/*for (int ProductIterator = 0; ProductIterator < ResponseSize; ProductIterator++)
	{
		ProductIndex = Integer.toString(ProductIterator);	
	   MSISDN =JsonPath.from(jsonString).get("type["+ProductIndex+"]");	
		System.out.println(ResponseList.get(ProductIterator));		
	}}*/
//================================= Get list of MSISDNs that has role Data ==================================
	
    public static List<String> GetDataLines (Response response)
	{
	//	String jsonString = response.asString(); //Convert response to string
		//String roleData = "ROLE_DATA";
	return response.jsonPath().getList("msisdnDto.findAll {x ->x.roleDTOS.findAll{ role -> role.name == 'ROLE_DATA'}}.msisdn"); //save response in list to get its size
		//ResponseSize = ResponseList.size(); //Get size of response list
	//	System.out.println(ResponseSize);
	}
		//------------Start Loop on products to set its values---------------
	
	public static void main( String[] args )
    {
		//Response Dataresponse= AccountList.GetAccountList("01091293312", "Test@1234","207153324");
		Response Dataresponse= AccountList.GetAccountList("01013456722", "Test@1234","207564191");
		List<String> Msisdns = GetDataLines(Dataresponse);
	for (int ProductIterator = 0; ProductIterator < Msisdns.size(); ProductIterator++)
		{
			System.out.println(Msisdns.get(ProductIterator));
		}
    }
}