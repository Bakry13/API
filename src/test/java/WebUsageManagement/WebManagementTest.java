package WebUsageManagement;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Management.ManagementEndPoints;
import Management.ManagementProducts;
import Utilities.Excel;
import Utilities.ExcelInit;
import Utilities.ExtentReport;
import Utilities.Verification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WebManagementTest 
{
	Response response;
	ManagementProducts UsageProduct;
	public int StatusCode = 0;
	public String jsonString = "0";
	public static int RowNumber = 1;
	String TCPath = System.getProperty("user.dir") + "/Inputs/" + "ManagementData.xlsx";
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
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(RowNumber+6)+"- "+ExcelInit.TCName);
			response = WebManagementEndPoints.managementRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
			UsageProduct = new ManagementProducts(response);
			System.out.println(ExcelInit.TCName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===============================Check Minutes Product for prepaid when it has a value================================
    @Test(priority = 8)
    public void voiceProductHasValue()
    {
    	try {     		
			jsonString = Verification.Success(response); //Verify status code
			Verification.VerifyFloat(UsageProduct.UsedMinutes, 3, "Used minutes = 3", "Used minutes = " + Float.toString(UsageProduct.UsedMinutes)); //Verify used minutes						
    	
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened"); e.printStackTrace();}
    }
//========================Check Minutes Product for prepaid when it is zero in prepaid=====================
    @Test(priority = 9)
    public void voiceProductIsZero()
    {
    	try {     		
	    	jsonString = Verification.Success(response); //Verify status code
	    	Verification.VerifyFloat(UsageProduct.UsedMinutes, 123456, "Used minutes = 0", "Used minutes = " + Float.toString(UsageProduct.UsedMinutes)); //Verify used minutes						
    	
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened"); e.printStackTrace();}
    }
//===============================Check Minutes Product for postpaid when it has a value================================
    @Test(priority = 10)
    public void postpaidProductHasValue()
    {
    	try {     		
	    	jsonString = Verification.Success(response); //Verify status code
	    	Verification.VerifyFloat(UsageProduct.UsedMinutes, 1, "Used minutes = 1", "Used minutes = " + Float.toString(UsageProduct.UsedMinutes)); //Verify used minutes						
	    	Verification.VerifyFloat(UsageProduct.UsedSMS, 4, "Used SMS = 4", "Used SMS = " + Float.toString(UsageProduct.UsedSMS)); //Verify used SMS
    	
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened"); e.printStackTrace();}
    }
//===============================Check Minutes Product for prepaid when it is zero========================
    @Test(priority = 11)
    public void postpaidProductIsZero()
    {
    	try {     		
	    	jsonString = Verification.Success(response); //Verify status code
	    	Verification.VerifyFloat(UsageProduct.UsedMinutes, 123456, "Used minutes = 0", "Used minutes = " + Float.toString(UsageProduct.UsedMinutes)); //Verify used minutes						
	    	Verification.VerifyFloat(UsageProduct.UsedSMS, 123456, "Used SMS = 0", "Used SMS = " + Float.toString(UsageProduct.UsedSMS)); //Verify used SMS						
    	
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened"); e.printStackTrace();}
    }
//================================================================================================
  @AfterMethod
	void EndReportTestCase()
	{
		ExtentReport.extent.endTest(ExtentReport.test); //close the test case in extent report
	}
}
