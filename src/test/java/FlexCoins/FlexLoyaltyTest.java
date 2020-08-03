package FlexCoins;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Utilities.AuthWeb;
import Utilities.Excel;
import Utilities.ExcelInit;
import Utilities.ExtentReport;
import Utilities.Verification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FlexLoyaltyTest 
{
	Response response;
	public int StatusCode = 0;
	public String jsonString = "0";
	public float Points = 111;
	public String Code = "0";
	public String reason = "0";
	public String message = "0";
	public static int RowNumber = 1;
	String TCPath = System.getProperty("user.dir")+"/Inputs/"+"FlexLoyaltyData.xlsx";
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
			//---------------------------------------Start test case--------------------------------------------
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(RowNumber-1)+"- "+ExcelInit.TCName);
			response = FlexLoyaltyEndpoints.loyaltyRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
			System.out.println(ExcelInit.TCName);
		} catch (IOException e) {e.printStackTrace();}
	}
//==============================================Flex number that has points============================================	
	@Test(priority = 1)
	public void FlexCoinsHasValue() throws IOException
	{	try {
			jsonString = Verification.Success(response); //Verify status code
			Points = JsonPath.from(jsonString).get("loyaltyAccount[0].loyaltyBalance.quantity.balance"); //Verify points
			Verification.VerifyFloat(Points, 55, "Points = 55", "Points = " + Float.toString(Points)); //Verify status code			
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and web auth status code is: ", AuthWeb.StatusCode); e.printStackTrace();}		
	}
//=========================================Flex number that does has 0 points============================================	
	@Test(priority = 2)
	public void FlexCoinsAreZero() throws IOException
	{	try {
			jsonString = Verification.Success(response); //Verify status code
			Points = JsonPath.from(jsonString).get("loyaltyAccount[0].loyaltyBalance.quantity.balance"); //Verify points
			Verification.VerifyFloat(Points, 0 , "Points = 0", "Points = " + Float.toString(Points)); //Verify status code		
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and web auth status code is: ", AuthWeb.StatusCode); e.printStackTrace();}
	}
//=========================================Flex number that is not eligible for loyalty============================================	
	@Test(priority = 3)
	public void NotEligibleForFlexCoins() throws IOException
	{	try {
			jsonString = Verification.NotFound(response); //Verify Not found 1001
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and web auth status code is: ", AuthWeb.StatusCode); e.printStackTrace();}
	}
//==================================================NonFlexNumber===================================================	
	@Test(priority = 4)
	public void NonFlexNumber() throws IOException
	{	try {
			jsonString = Verification.NotFound(response); //Verify Not found 1001
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and web auth status code is: ", AuthWeb.StatusCode); e.printStackTrace();}
	}

//===================================================================================================================				
	@AfterMethod
	void EndReportTestCase()
	{
		ExtentReport.extent.endTest(ExtentReport.test); //close the test case in extent report
	}
	
}
