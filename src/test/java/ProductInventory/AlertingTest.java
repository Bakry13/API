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

public class AlertingTest 
{
	Response response;
	AlertingProducts AlertingProduct;
	public int StatusCode = 0;
	public String jsonString = "0";
	public static int RowNumber = 1;
	String TCPath = System.getProperty("user.dir") + "/Inputs/" + "AlertingData.xlsx";
	
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
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(RowNumber+13)+"- "+ExcelInit.TCName+"_EN");
			response = ProductEndPoints.AlertingRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
			System.out.println(ExcelInit.TCName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===================================Check Health service========================================
    @Test(priority = 15)
    public void HealthProduct()
    {
    	try {	
    		jsonString = Verification.Success(response); //Verify status code
    		AlertingProduct= new AlertingProducts(response,"SallyFouad");
		    Verification.VerifyString(AlertingProduct.Name, "SallyFouad", "Alerting Service name: SallyFouad", "Alerting Service name: " + AlertingProduct.Name); //Verify alerting service name			
		    Verification.VerifyString(AlertingProduct.Description, "Various food recipes & nuitrition tips", "Alerting Service description: Various food recipes & nuitrition tips", "Alerting Service description: " + AlertingProduct.Description); //Verify alerting service description
		    Verification.VerifyString(AlertingProduct.CategoryValue, "Health", "Alerting Service category value: Health", "Alerting Service category value: " + AlertingProduct.CategoryValue); //Verify alerting service category value
		    Verification.VerifyString(AlertingProduct.ChargePeriod, "Day", "Alerting Service charge period: Day", "Alerting Service charge period: " + AlertingProduct.ChargePeriod); //Verify alerting service charge period
		    Verification.VerifyString(AlertingProduct.PriceValue, "30.0", "Alerting Service price value: 30.0", "Alerting Service price value: " + AlertingProduct.PriceValue); //Verify alerting service price value		
		    Verification.VerifyString(AlertingProduct.PriceUnit, "PT", "Alerting Service price unit: PT", "Alerting Service price unit: " + AlertingProduct.PriceUnit); //Verify alerting service price unit

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Check Health second service=================================           
    @Test(priority = 16)
    public void HealthProduct2()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
    		AlertingProduct= new AlertingProducts(response,"Se7aty");
		    Verification.VerifyString(AlertingProduct.Name, "Se7aty", "Alerting Service name: Se7aty", "Alerting Service name: " + AlertingProduct.Name); //Verify alerting service name			
		    Verification.VerifyString(AlertingProduct.Description, "Healthy Tips", "Alerting Service description: Healthy Tips", "Alerting Service description: " + AlertingProduct.Description); //Verify alerting service description
		    Verification.VerifyString(AlertingProduct.CategoryValue, "Health", "Alerting Service category value: Health", "Alerting Service category value: " + AlertingProduct.CategoryValue); //Verify alerting service category value
		    Verification.VerifyString(AlertingProduct.ChargePeriod, "Day", "Alerting Service charge period: Day", "Alerting Service charge period: " + AlertingProduct.ChargePeriod); //Verify alerting service charge period
		    Verification.VerifyString(AlertingProduct.PriceValue, "30.0", "Alerting Service price value: 30.0", "Alerting Service price value: " + AlertingProduct.PriceValue); //Verify alerting service price value		
		    Verification.VerifyString(AlertingProduct.PriceUnit, "PT", "Alerting Service price unit: PT", "Alerting Service price unit: " + AlertingProduct.PriceUnit); //Verify alerting service price unit

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }  
//===================================Check Check cooking service=============================           
    @Test(priority = 17)
    public void CookingProduct()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
    		AlertingProduct= new AlertingProducts(response,"Sofra");
		    Verification.VerifyString(AlertingProduct.Name, "Sofra", "Alerting Service name: Sofra", "Alerting Service name: " + AlertingProduct.Name); //Verify alerting service name			
		    Verification.VerifyString(AlertingProduct.Description, "Learn the latest, fastesf, and easiest cooking recipies", "Alerting Service description: Learn the latest, fastesf, and easiest cooking recipies", "Alerting Service description: " + AlertingProduct.Description); //Verify alerting service description
		    Verification.VerifyString(AlertingProduct.CategoryValue, "Cooking", "Alerting Service category value: Cooking", "Alerting Service category value: " + AlertingProduct.CategoryValue); //Verify alerting service category value
		    Verification.VerifyString(AlertingProduct.ChargePeriod, "Day", "Alerting Service charge period: Day", "Alerting Service charge period: " + AlertingProduct.ChargePeriod); //Verify alerting service charge period
		    Verification.VerifyString(AlertingProduct.PriceValue, "30.0", "Alerting Service price value: 30.0", "Alerting Service price value: " + AlertingProduct.PriceValue); //Verify alerting service price value		
		    Verification.VerifyString(AlertingProduct.PriceUnit, "PT", "Alerting Service price unit: PT", "Alerting Service price unit: " + AlertingProduct.PriceUnit); //Verify alerting service price unit

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Check religious alerting services===========================================           
    @Test(priority = 18)
    public void ReligiousProduct()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
    		AlertingProduct= new AlertingProducts(response,"Affasy");
		    Verification.VerifyString(AlertingProduct.Name, "Affasy", "Alerting Service name: Affasy", "Alerting Service name: " + AlertingProduct.Name); //Verify alerting service name			
		    Verification.VerifyString(AlertingProduct.Description, "Islamic Prayers", "Alerting Service description: Islamic Prayers", "Alerting Service description: " + AlertingProduct.Description); //Verify alerting service description
		    Verification.VerifyString(AlertingProduct.CategoryValue, "Religious", "Alerting Service category value: Religious", "Alerting Service category value: " + AlertingProduct.CategoryValue); //Verify alerting service category value
		    Verification.VerifyString(AlertingProduct.ChargePeriod, "Day", "Alerting Service charge period: Day", "Alerting Service charge period: " + AlertingProduct.ChargePeriod); //Verify alerting service charge period
		    Verification.VerifyString(AlertingProduct.PriceValue, "30.0", "Alerting Service price value: 30.0", "Alerting Service price value: " + AlertingProduct.PriceValue); //Verify alerting service price value		
		    Verification.VerifyString(AlertingProduct.PriceUnit, "PT", "Alerting Service price unit: PT", "Alerting Service price unit: " + AlertingProduct.PriceUnit); //Verify alerting service price unit

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Check Alerting service that does not exist========================================
    @Test(priority = 19)
    public void UnsubscribedProduct()
    {
    	try {	
    		jsonString = Verification.Success(response); //Verify status code
    		AlertingProduct= new AlertingProducts(response,"Weather");
		    Verification.VerifyString(AlertingProduct.Name, "does not exist", "Alerting Service name: does not exist", "Alerting Service name: " + AlertingProduct.Name); //Verify alerting service name			
		    Verification.VerifyString(AlertingProduct.Description, "does not exist", "Alerting Service description: does not exist", "Alerting Service description: " + AlertingProduct.Description); //Verify alerting service description
		    Verification.VerifyString(AlertingProduct.CategoryValue, "does not exist", "Alerting Service category value: does not exist", "Alerting Service category value: " + AlertingProduct.CategoryValue); //Verify alerting service category value
		    Verification.VerifyString(AlertingProduct.ChargePeriod, "does not exist", "Alerting Service charge period: does not exist", "Alerting Service charge period: " + AlertingProduct.ChargePeriod); //Verify alerting service charge period
		    Verification.VerifyString(AlertingProduct.PriceValue, "does not exist", "Alerting Service price value: does not exist", "Alerting Service price value: " + AlertingProduct.PriceValue); //Verify alerting service price value		
		    Verification.VerifyString(AlertingProduct.PriceUnit, "does not exist", "Alerting Service price unit: does not exist", "Alerting Service price unit: " + AlertingProduct.PriceUnit); //Verify alerting service price unit

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Check number that has not alerting services===================================           
    @Test(priority = 20)
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
