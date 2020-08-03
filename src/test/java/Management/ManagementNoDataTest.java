package Management;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Utilities.Auth;
import Utilities.Excel;
import Utilities.ExcelInit;
import Utilities.ExtentReport;
import Utilities.Verification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ManagementNoDataTest 
{
	public String message = "x";
	Response response;
	public int StatusCode = 0;
	public String jsonString = "0";
	public static int RowNumber = 5;
	String TCPath = System.getProperty("user.dir") + "/Inputs/" + "ManagementData.xlsx";
	//==========================No Data Test for Postpaid and prepaid==================
	@Test(priority = 12)
	public void StartTestCase() throws IOException
	{	
		Excel.setPath(TCPath);
		ExcelInit.TCName = "x";
		while(true)
		{
			try {
				Excel.setPath(TCPath);
				RowNumber++;
				//--------------------------------Reading data from excel-------------------------------------------
				ExcelInit.TCName = Excel.read(RowNumber, ExcelInit.TCNameCol); //Reading test case name
				if(ExcelInit.TCName.equals("EndOfTest")) break; //if it is the last test case
				ExcelInit.MSISDN = Excel.read(RowNumber, ExcelInit.MSISDNCol); //Reading MSISDN
				ExcelInit.Password = Excel.read(RowNumber, ExcelInit.PasswordCol); //Reading Password
				//---------------------------------------Start test case--------------------------------------------
				ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(RowNumber+6)+"- "+ExcelInit.TCName);
				response = ManagementEndPoints.managementRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
				System.out.println(ExcelInit.TCName);
				
				//----------------------Verification---------------------
				jsonString = Verification.NotFound(response); //Check no data found
		    	message = JsonPath.from(jsonString).get("message");
		    	Verification.VerifyString(message, "No Usages found", "message is: No Usages found", "message is: " + message); //Verify message
			} catch (IOException e) {
				e.printStackTrace(); ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode);
				}
			ExtentReport.extent.endTest(ExtentReport.test); //close the test case in extent report
		}
	}
}
