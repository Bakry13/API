package ProductInventory;

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
import io.restassured.response.Response;

public class MI_Test 
{
	Response response;
	MI_Products MIProduct;
	public int StatusCode = 0;
	public String jsonString = "0";
	public static int RowNumber = 1;
	String TCPath = System.getProperty("user.dir") + "/Inputs/" + "MIData.xlsx";
	
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
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(RowNumber+6)+"- "+ExcelInit.TCName+"_EN");
			response = ProductEndPoints.MIRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
			System.out.println(ExcelInit.TCName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===================================Check ExtremeProduct========================================
    @Test(priority = 8)
    public void ExtremeProduct()
    {
    	try {	
    		jsonString = Verification.Success(response); //Verify status code
    		MIProduct= new MI_Products(response,"Extreme Net Bundle 10LE 500MB");
		    Verification.VerifyString(MIProduct.Name, "Extreme Net Bundle 10LE 500MB", "Internet product name: Extreme Net Bundle 10LE 500MB", "Internet product name: " + MIProduct.Name); //Verify Internet product name
		    Verification.VerifyString(MIProduct.PriceValue, "10", "Internet product price: 10", "Internet product price: " + MIProduct.PriceValue); //Verify Internet product price
		    Verification.VerifyString(MIProduct.PriceUnit, "LE", "Internet product price unit: LE", "Internet product price unit: " + MIProduct.PriceUnit); //Verify Internet product price unit
		    Verification.VerifyFloat(MIProduct.Amount[0], 400, "Internet product amount: 400", "Internet product amount: " + MIProduct.Amount[0]); //Verify Internet product amount
		    Verification.VerifyFloat(MIProduct.Consumed[0], 100, "Internet product consumed MBs: 100", "Internet product consumed MBs: " + MIProduct.Consumed[0]); //Verify Internet product consumed MBs
		    Verification.VerifyFloat(MIProduct.Total[0], 500, "Internet product total MBs: 500", "Internet product total MBs: " + MIProduct.Total[0]); //Verify Internet product total MBs
		    Verification.VerifyString(MIProduct.Units[0], "MB", "Internet product unit: MB", "Internet product unit: " + MIProduct.Units[0]); //Verify Internet product unit
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Check ContentProduct=================================           
    @Test(priority = 9)
    public void ContentProduct()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
    		MIProduct= new MI_Products(response,"Monthly Renewable Video Bundle 10 LE");
		    Verification.VerifyString(MIProduct.Name, "Monthly Renewable Video Bundle 10 LE", "Internet product name: Monthly Renewable Video Bundle 10 LE", "Internet product name: " + MIProduct.Name); //Verify Internet product name
		    Verification.VerifyString(MIProduct.PriceValue, "10", "Internet product price: 10", "Internet product price: " + MIProduct.PriceValue); //Verify Internet product price
		    Verification.VerifyString(MIProduct.PriceUnit, "LE", "Internet product price unit: LE", "Internet product price unit: " + MIProduct.PriceUnit); //Verify Internet product price unit
		    Verification.VerifyFloat(MIProduct.Amount[0], 750, "Internet product off peak amount: 750", "Internet product amount: " + MIProduct.Amount[0]); //Verify off peak amount
		    Verification.VerifyFloat(MIProduct.Consumed[0], 0, "Internet product off peak consumed MBs: 0", "Internet product consumed MBs: " + MIProduct.Consumed[0]); //Verify off peak consumed amount
		    Verification.VerifyFloat(MIProduct.Total[0], 750, "Internet product off peak total MBs: 750", "Internet product total MBs: " + MIProduct.Total[0]); //Verify off peak total amount
		    Verification.VerifyFloat(MIProduct.Amount[1], 250, "Internet product All day amount: 250", "Internet product amount: " + MIProduct.Amount[1]); //Verify All day amount
		    Verification.VerifyFloat(MIProduct.Consumed[1], 0, "Internet product All day consumed MBs: 0", "Internet product consumed MBs: " + MIProduct.Consumed[1]); //Verify All day consumed amount
		    Verification.VerifyFloat(MIProduct.Total[1], 250, "Internet product All day total MBs: 250", "Internet product total MBs: " + MIProduct.Total[1]); //Verify All day total amount
		    Verification.VerifyString(MIProduct.Units[0], "MB", "Internet product off peak unit: MB", "Internet product unit: " + MIProduct.Units[0]); //Verify Internet product unit
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }  
//===================================Check Stop_internetProduct=============================           
    @Test(priority = 10)
    public void Stop_internetProduct()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
    		MIProduct= new MI_Products(response,"Stop Internet Renewable");
		    Verification.VerifyString(MIProduct.Name, "Stop Internet Renewable", "Internet product name: Stop Internet Renewable", "Internet product name: " + MIProduct.Name); //Verify Internet product name
		    Verification.VerifyString(MIProduct.PriceValue, "0", "Internet product price: 0", "Internet product price: " + MIProduct.PriceValue); //Verify Internet product price
		    Verification.VerifyString(MIProduct.PriceUnit, "LE", "Internet product price unit: LE", "Internet product price unit: " + MIProduct.PriceUnit); //Verify Internet product price unit
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
  //===================================Check addon Product=============================     
    @Test(priority = 11)
    public void AddonProduct()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
    		MIProduct= new MI_Products(response,"Monthly add-on 5LE Renewable");
		    Verification.VerifyString(MIProduct.Name, "Monthly add-on 5LE Renewable", "Addon product name: Monthly add-on 5LE Renewable", "Addon product name: " + MIProduct.Name); //Verify Internet product name
		    Verification.VerifyString(MIProduct.PriceValue, "5", "Addon product price: 5", "Addon product price: " + MIProduct.PriceValue); //Verify Internet product price
		    Verification.VerifyString(MIProduct.PriceUnit, "LE", "Addon product price unit: LE", "Addon product price unit: " + MIProduct.PriceUnit); //Verify Internet product price unit
		    Verification.VerifyFloat(MIProduct.Amount[0], 150, "Addon product amount: 150", "Addon product amount: " + MIProduct.Amount[0]); //Verify Internet product amount
		    Verification.VerifyFloat(MIProduct.Consumed[0], 0, "Addon product consumed MBs: 0", "Addon product consumed MBs: " + MIProduct.Consumed[0]); //Verify Internet product consumed MBs
		    Verification.VerifyFloat(MIProduct.Total[0], 150, "Addon product total MBs: 150", "Addon product total MBs: " + MIProduct.Total[0]); //Verify Internet product total MBs
		    Verification.VerifyString(MIProduct.Units[0], "MB", "Addon product unit: MB", "Addon product unit: " + MIProduct.Units[0]); //Verify Internet product unit
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }      
//===================================Check Unsubscribed bundle ===========================================           
    @Test(priority = 12)
    public void UnsubscribedProduct()
    {
    	try {	
    		jsonString = Verification.Success(response); //Verify status code
    		MIProduct= new MI_Products(response,"Extreme Net Bundle 5LE 150MB");
		    Verification.VerifyString(MIProduct.Name, "does not exist", "Internet product name: does not exist", "Internet product name: " + MIProduct.Name); //Verify Internet product name
		    } catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
     }
//===================================Check super pass========================================
    @Test(priority = 13)
    public void Supper_passProduct()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
    		MIProduct= new MI_Products(response,"SuperPass Streaming 15LE 1500 SuperMB");
		    Verification.VerifyString(MIProduct.Name, "SuperPass Streaming 15LE 1500 SuperMB", "Internet product name: SuperPass Streaming 15LE 1500 SuperMB", "Internet product name: " + MIProduct.Name); //Verify Internet product name
		    Verification.VerifyString(MIProduct.PriceValue, "15", "Internet product price: 15", "Internet product price: " + MIProduct.PriceValue); //Verify Internet product price
		    Verification.VerifyString(MIProduct.PriceUnit, "LE", "Internet product price unit: LE", "Internet product price unit: " + MIProduct.PriceUnit); //Verify Internet product price unit
		    Verification.VerifyFloat(MIProduct.Amount[0],1200, "Internet product Data amount: 1200", "Internet product amount: " + MIProduct.Amount[0]); //Verify  amount
		    Verification.VerifyFloat(MIProduct.Consumed[0], 300, "Internet product  consumed Supermega: 300", "Internet product consumed Supermega: " + MIProduct.Consumed[0]); //Verify consumed amount
		    Verification.VerifyFloat(MIProduct.Total[0], 1500, "Internet product total Supermega: 1500", "Internet product total Supermega: " + MIProduct.Total[0]); //Verify total amount
	//	    Verification.VerifyFloat(MIProduct.Amount[1], 0, "Internet product Generic amount: 0", "Internet product amount: " + MIProduct.Amount[1]); //Verify Generic amount
		    Verification.VerifyFloat(MIProduct.Consumed[1], 200, "Internet product Generic consumed Supermega: 200", "Internet product consumed Supermega: " + MIProduct.Consumed[1]); //Verify Generic consumed amount
	//	    Verification.VerifyFloat(MIProduct.Total[1], 0, "Internet product Generic total Supermega: 0", "Internet product total Supermega: " + MIProduct.Total[1]); //Verify Generic total amount
	//	    Verification.VerifyFloat(MIProduct.Amount[2], 0, "Internet product streaming amount: 0", "Internet product amount: " + MIProduct.Amount[2]); //Verify streaming amount
		    Verification.VerifyFloat(MIProduct.Consumed[2], 100, "Internet product streaming consumed Supermega:100", "Internet product consumed Supermega: " + MIProduct.Consumed[2]); //Verify streaming consumed amount
	//	    Verification.VerifyFloat(MIProduct.Total[2], 0, "Internet product streaming total Supermega: 0", "Internet product total Supermega: " + MIProduct.Total[2]); //Verify streaming total amount
		    Verification.VerifyString(MIProduct.Units[0], "Super Mega", "Internet product  unit: Super Mega", "Internet product unit: " + MIProduct.Units[0]); //Verify Internet product unit
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }  
//===================================Check number that has not bundle===================================           
    @Test(priority = 14)
    public void NoProduct()
    {
    	try {	
    		jsonString = Verification.NotFound(response);
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    } 

//===============================================================================================
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
