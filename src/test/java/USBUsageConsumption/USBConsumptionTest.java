package USBUsageConsumption;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Utilities.Excel;
import Utilities.ExcelInit;
import Utilities.ExtentReport;
import Utilities.Verification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import USBUsageConsumption.USBConsumptionEndpoints;

public class USBConsumptionTest 
{
	List<String> Msisdns;
	public int StatusCode = 0;
	public String jsonString = "0";
	public float RemainingQuota = 111;
	public float Points = 111;
	public String Code = "0";
	public String reason = "0";
	public String message = "0";
	public static int RowNumber = 1;
	String TCPath = System.getProperty("user.dir")+"/Inputs/"+"USBUsageConsumption.xlsx";
//====================================================================================================================	
	@BeforeMethod
	void StartTestCase()
	{	try {
			Excel.setPath(TCPath);
			RowNumber++;
			//--------------------------------Reading data from excel-------------------------------------------
			ExcelInit.TCName = Excel.read(RowNumber, ExcelInit.TCNameCol); //Reading test case name
			ExcelInit.MSISDN = Excel.read(RowNumber, ExcelInit.MSISDNCol); //Reading MSISDN
			ExcelInit.Password = Excel.read(RowNumber, ExcelInit.PasswordCol); //Reading Password
			ExcelInit.CustomerID = Excel.read(RowNumber, ExcelInit.CustomerIDCol); //Reading CustomerId
			//---------------------------------------Start test case--------------------------------------------
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(RowNumber-1)+"- "+ExcelInit.TCName);
			Response Accountlistresponse= AccountList.GetAccountList(ExcelInit.MSISDN, ExcelInit.Password,ExcelInit.CustomerID);
		   Msisdns = GetDataLines_update.GetDataLines(Accountlistresponse);
			
		/*for (int ProductIterator = 0; ProductIterator < Msisdns.size(); ProductIterator++)
			{
			Response output= USBConsumptionEndpoints.USBConsumptionrequest(ExcelInit.MSISDN, ExcelInit.Password, Msisdns.get(ProductIterator));///response = USBConsumptionEndpoints.USBConsumptionrequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
			System.out.println("hala");
			}*/
		System.out.println(ExcelInit.TCName);
		}catch (IOException e) {e.printStackTrace();}
	}
//==============================================Flex number that has Data lines ============================================	
	@Test(priority = 1)
	public void FlexUSBUsage() throws IOException
	{
		float[] RemainingValue =  {10853,5600};
		
		for (int ProductIterator = 0; ProductIterator < Msisdns.size(); ProductIterator++)
		{
		Response output= USBConsumptionEndpoints.USBConsumptionrequest(ExcelInit.MSISDN,ExcelInit.Password, Msisdns.get(ProductIterator));
		try {
			jsonString = Verification.Success(output); //Verify status code
			RemainingQuota =JsonPath.from(output.body().asString()).get("[1].bucket[0].bucketBalance[0].remainingValue.amount"); //Verify points
			//Verification.VerifyFloat(RemainingQuota, 10853, "RemainingQuota = 10853", "RemainingQuota = " + Float.toString(RemainingQuota)); //Verify status code	
			Verification.VerifyFloat(RemainingQuota, RemainingValue[ProductIterator], "RemainingQuota = " + RemainingValue[ProductIterator], "RemainingQuota = " + Float.toString(RemainingQuota)); //Verify status code 
		System.out.println(RemainingQuota);
		System.out.println(ExcelInit.MSISDN);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened"); e.printStackTrace();
		
		}}
	}
//========================================= RED number that has Data lines ============================================	
	@Test(priority = 2)
	public void REDUSBUsage() throws IOException
	{
		float[] RemainingValue =  {10853,5600};
	
	for (int ProductIterator = 0; ProductIterator < Msisdns.size(); ProductIterator++)
	{
	Response output= USBConsumptionEndpoints.USBConsumptionrequest(ExcelInit.MSISDN, ExcelInit.Password, Msisdns.get(ProductIterator));
	try {
		jsonString = Verification.Success(output); //Verify status code
		RemainingQuota =JsonPath.from(output.body().asString()).get("[1].bucket[0].bucketBalance[0].remainingValue.amount"); // Get remaining quota
		//Verification.VerifyFloat(RemainingQuota, 10853, "RemainingQuota = 10853", "RemainingQuota = " + Float.toString(RemainingQuota)); //Verify status code	
		Verification.VerifyFloat(RemainingQuota, RemainingValue[ProductIterator], "RemainingQuota = " + RemainingValue[ProductIterator], "RemainingQuota = " + Float.toString(RemainingQuota)); //Verify remaining quota 
	System.out.println(RemainingQuota);
	System.out.println(ExcelInit.MSISDN);
	} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened"); e.printStackTrace();
	
	}}
}

//===================================================================================================================				
	@AfterMethod
	void EndReportTestCase()
	{
		ExtentReport.extent.endTest(ExtentReport.test); //close the test case in extent report
	}
	
}
