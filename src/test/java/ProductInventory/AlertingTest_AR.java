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

public class AlertingTest_AR 
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
			ExtentReport.test = ExtentReport.extent.startTest(Integer.toString(RowNumber+7)+"- "+ExcelInit.TCName+"_AR");
			response = ProductEndPoints.AlertingArabicRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
			System.out.println(ExcelInit.TCName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===================================Check Health service========================================
    @Test(priority = 9)
    public void HealthProduct()
    {
    	try {	
    		jsonString = Verification.Success(response); //Verify status code
    		AlertingProduct= new AlertingProducts(response,"سالي فؤاد");
		    Verification.VerifyString(AlertingProduct.Name, "سالي فؤاد", "Alerting Service name: سالي فؤاد", "Alerting Service name: " + AlertingProduct.Name); //Verify alerting service name			
		    Verification.VerifyString(AlertingProduct.Description, "خدمة\"سالي فؤاد\" توفر للمستخدمين وصفات طعام صحية ونصائح غذائية", "Alerting Service description: خدمة\"سالي فؤاد\" توفر للمستخدمين وصفات طعام صحية ونصائح غذائية", "Alerting Service description: " + AlertingProduct.Description); //Verify alerting service description
		    Verification.VerifyString(AlertingProduct.CategoryValue, " الصحة", "Alerting Service category value: الصحة", "Alerting Service category value: " + AlertingProduct.CategoryValue); //Verify alerting service category value
		    Verification.VerifyString(AlertingProduct.ChargePeriod, "يوم", "Alerting Service charge period: يوم", "Alerting Service charge period: " + AlertingProduct.ChargePeriod); //Verify alerting service charge period
		    Verification.VerifyString(AlertingProduct.PriceValue, "30.0", "Alerting Service price value: 30.0", "Alerting Service price value: " + AlertingProduct.PriceValue); //Verify alerting service price value		
		    Verification.VerifyString(AlertingProduct.PriceUnit, "قرش", "Alerting Service price unit: قرش", "Alerting Service price unit: " + AlertingProduct.PriceUnit); //Verify alerting service price unit

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Check Health second service=================================           
    @Test(priority = 10)
    public void HealthProduct2()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
    		AlertingProduct= new AlertingProducts(response,"صحتي");
		    Verification.VerifyString(AlertingProduct.Name, "صحتي", "Alerting Service name: صحتي", "Alerting Service name: " + AlertingProduct.Name); //Verify alerting service name			
		    Verification.VerifyString(AlertingProduct.Description, "نصائح للحفاظ على الصحة", "Alerting Service description: نصائح للحفاظ على الصحة", "Alerting Service description: " + AlertingProduct.Description); //Verify alerting service description
		    Verification.VerifyString(AlertingProduct.CategoryValue, " الصحة", "Alerting Service category value: الصحة", "Alerting Service category value: " + AlertingProduct.CategoryValue); //Verify alerting service category value
		    Verification.VerifyString(AlertingProduct.ChargePeriod, "يوم", "Alerting Service charge period: يوم", "Alerting Service charge period: " + AlertingProduct.ChargePeriod); //Verify alerting service charge period
		    Verification.VerifyString(AlertingProduct.PriceValue, "30.0", "Alerting Service price value: 30.0", "Alerting Service price value: " + AlertingProduct.PriceValue); //Verify alerting service price value		
		    Verification.VerifyString(AlertingProduct.PriceUnit, "قرش", "Alerting Service price unit: قرش", "Alerting Service price unit: " + AlertingProduct.PriceUnit); //Verify alerting service price unit

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }  
//===================================Check Check cooking service=============================           
    @Test(priority = 11)
    public void CookingProduct()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
    		AlertingProduct= new AlertingProducts(response,"وصفات");
		    Verification.VerifyString(AlertingProduct.Name, "وصفات", "Alerting Service name: وصفات", "Alerting Service name: " + AlertingProduct.Name); //Verify alerting service name			
		    Verification.VerifyString(AlertingProduct.Description, "تعلم أجدد وأسهل وأسرع الوصفات", "Alerting Service description: تعلم أجدد وأسهل وأسرع الوصفات", "Alerting Service description: " + AlertingProduct.Description); //Verify alerting service description
		    Verification.VerifyString(AlertingProduct.CategoryValue, " الطبخ", "Alerting Service category value: الصحة", "Alerting Service category value: " + AlertingProduct.CategoryValue); //Verify alerting service category value
		    Verification.VerifyString(AlertingProduct.ChargePeriod, "يوم", "Alerting Service charge period: يوم", "Alerting Service charge period: " + AlertingProduct.ChargePeriod); //Verify alerting service charge period
		    Verification.VerifyString(AlertingProduct.PriceValue, "30.0", "Alerting Service price value: 30.0", "Alerting Service price value: " + AlertingProduct.PriceValue); //Verify alerting service price value		
		    Verification.VerifyString(AlertingProduct.PriceUnit, "قرش", "Alerting Service price unit: قرش", "Alerting Service price unit: " + AlertingProduct.PriceUnit); //Verify alerting service price unit

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Check religious alerting services===========================================           
    @Test(priority = 12)
    public void ReligiousProduct()
    {
    	try {
    		jsonString = Verification.Success(response); //Verify status code
    		AlertingProduct= new AlertingProducts(response,"العفاسي");
		    Verification.VerifyString(AlertingProduct.Name, "العفاسي", "Alerting Service name: العفاسي", "Alerting Service name: " + AlertingProduct.Name); //Verify alerting service name			
		    Verification.VerifyString(AlertingProduct.Description, "ادعية اسلامية", "Alerting Service description: ادعية اسلامية", "Alerting Service description: " + AlertingProduct.Description); //Verify alerting service description
		    Verification.VerifyString(AlertingProduct.CategoryValue, " الديني", "Alerting Service category value: الديني", "Alerting Service category value: " + AlertingProduct.CategoryValue); //Verify alerting service category value
		    Verification.VerifyString(AlertingProduct.ChargePeriod, "يوم", "Alerting Service charge period: يوم", "Alerting Service charge period: " + AlertingProduct.ChargePeriod); //Verify alerting service charge period
		    Verification.VerifyString(AlertingProduct.PriceValue, "30.0", "Alerting Service price value: 30.0", "Alerting Service price value: " + AlertingProduct.PriceValue); //Verify alerting service price value		
		    Verification.VerifyString(AlertingProduct.PriceUnit, "قرش", "Alerting Service price unit: قرش", "Alerting Service price unit: " + AlertingProduct.PriceUnit); //Verify alerting service price unit

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Check Alerting service that does not exist========================================
    @Test(priority = 13)
    public void UnsubscribedProduct()
    {
    	try {	
    		jsonString = Verification.Success(response); //Verify status code
    		AlertingProduct= new AlertingProducts(response,"حالة الطقس");
		    Verification.VerifyString(AlertingProduct.Name, "does not exist", "Alerting Service name: does not exist", "Alerting Service name: " + AlertingProduct.Name); //Verify alerting service name			
		    Verification.VerifyString(AlertingProduct.Description, "does not exist", "Alerting Service description: does not exist", "Alerting Service description: " + AlertingProduct.Description); //Verify alerting service description
		    Verification.VerifyString(AlertingProduct.CategoryValue, "does not exist", "Alerting Service category value: does not exist", "Alerting Service category value: " + AlertingProduct.CategoryValue); //Verify alerting service category value
		    Verification.VerifyString(AlertingProduct.ChargePeriod, "does not exist", "Alerting Service charge period: does not exist", "Alerting Service charge period: " + AlertingProduct.ChargePeriod); //Verify alerting service charge period
		    Verification.VerifyString(AlertingProduct.PriceValue, "does not exist", "Alerting Service price value: does not exist", "Alerting Service price value: " + AlertingProduct.PriceValue); //Verify alerting service price value		
		    Verification.VerifyString(AlertingProduct.PriceUnit, "does not exist", "Alerting Service price unit: does not exist", "Alerting Service price unit: " + AlertingProduct.PriceUnit); //Verify alerting service price unit

    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Check number that has not alerting services===================================           
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
