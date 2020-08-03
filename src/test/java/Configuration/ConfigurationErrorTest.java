package Configuration;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Configuration.ConfigurationErrorHandling;
import Utilities.Auth;
import Utilities.Excel;
import Utilities.ExcelInit;
import Utilities.ExtentReport;
import Utilities.Verification;
import io.restassured.response.Response;

public class ConfigurationErrorTest 
{
	Response response;
	public int StatusCode = 0;
	public String jsonString = "0";
	public String Code = "0";
	public String reason = "0";
	public String message = "0";
	public static int RowNumber = 1;
	String TCPath = System.getProperty("user.dir") + "/Inputs/" + "ConfigurationErrorHandling.xlsx";
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
			System.out.println(ExcelInit.TCName);
		} catch (IOException e) {e.printStackTrace();}
	}	
//==================================Unauthorized - ConfigurationRequestWithoutMSISDN===============================================
	@Test(priority = 1)
	 public void RequestWithoutMsisdn() throws IOException
	 {	try {
		    response = ConfigurationErrorHandling.configurationRequestWithoutMsisdn(ExcelInit.MSISDN, ExcelInit.Password);
		    jsonString = Verification.Unauthorized(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); 
		  e.printStackTrace();}
	 }
//==================================Unauthorized - ConfigurationRequestWithoutToken==============================================================
	@Test(priority = 2)
	public void RequestWithoutToken() throws IOException
	{	try {
		    response = ConfigurationErrorHandling.configurationRequestWithoutToken(ExcelInit.MSISDN, ExcelInit.Password);
		    jsonString = Verification.Unauthorized(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode);
		  e.printStackTrace();}
	}
//=====================================Not Found - ConfigurationRequestWrongURL==============================================================
	@Test(priority = 3)
	public void RequestWrongURL() throws IOException
	{	try {
		    response = ConfigurationErrorHandling.configurationRequestWrongURL(ExcelInit.MSISDN, ExcelInit.Password); 
		    jsonString = Verification.NotFoundURL(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); 
		  e.printStackTrace();}
	}
//===================================405 Method Not Allowed - configurationPostRequest==============================================================
	@Test(priority = 4)
	public void PostRequest() throws IOException
	{	try {
		    response = ConfigurationErrorHandling.configurationPostRequest(ExcelInit.MSISDN, ExcelInit.Password); 
		    jsonString = Verification.MethodNotAllowed(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); 
		  e.printStackTrace();}	
	}
//=====================================400 Bad request - configurationRequestWrongURL==============================================================
	@Test(priority = 5)
	public void badRequest() throws IOException
	{	try {
		    response = ConfigurationErrorHandling.configurationWrongQparam(ExcelInit.MSISDN, ExcelInit.Password); 
		    jsonString = Verification.BadRequest(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); 
		  e.printStackTrace();}
	}
//===================================500 Internal Server Error - Missing query param Request==============================================================
	@Test(priority = 6)
	public void missningQRequest() throws IOException
	{	try {
		    response = ConfigurationErrorHandling.configurationMissingQParamtRequest(ExcelInit.MSISDN, ExcelInit.Password); 
		    jsonString = Verification.BadRequest(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); 
		  e.printStackTrace();}
	}
//===================================403 Forbidden - Request without different MSISDN==============================================================
	@Test(priority = 7)
	public void RequestWithDifferentMSISDN() throws IOException
	{	try {
		    response = ConfigurationErrorHandling.configurationWithDifferentMSISDN(ExcelInit.MSISDN, ExcelInit.Password);
		    jsonString = Verification.Forbidden(response);
		} catch (Exception e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); 
		  e.printStackTrace();}
	}
//===================================================================================================================				
	@AfterMethod
	void EndReportTestCase()
	{
		try {
			ExtentReport.extent.endTest(ExtentReport.test); //close the test case in extent report
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
