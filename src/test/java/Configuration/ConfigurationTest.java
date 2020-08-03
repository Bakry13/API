package Configuration;

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

public class ConfigurationTest 
{
	Response response;
	ConfigurationFlags Flags;
	public int StatusCode = 0;
	public String jsonString = "0";
	public static int RowNumber = 1;
	String TCPath = System.getProperty("user.dir") + "/Inputs/" + "ConfigurationData.xlsx";
	
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
			System.out.println(ExcelInit.TCName);
		} catch (IOException e) {e.printStackTrace();}
	}
//===================================Android Red Request========================================
    @Test(priority = 8)
    public void AndroidRedRequest()
    {
    	try {	
    		response = ConfigurationEndPoints.AndroidConfigurationRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
    		jsonString = Verification.Success(response); //Verify status code
    		Flags= new ConfigurationFlags(response);
    		Verification.VerifyString(Flags.channelId, "AnaVodafoneAndroid", "Channel Id = AnaVodafoneAndroid", "Channel Id = " + Flags.channelId); //Verify Channel Id
    		Verification.VerifyString(Flags.latestBuildNumber, "410", "latestBuildNumber = 410", "Latest Build Number = " + Flags.latestBuildNumber); //Verify latest build number
    		Verification.VerifyString(Flags.updateType, "NONE", "updateType = NONE", "Update Type = " + Flags.updateType); //Verify update type
    		Verification.VerifyBoolean(Flags.ConsumptionDXLFlag, true, "Consumption DXL Flag = true", "Consumption DXL Flag = " + Flags.ConsumptionDXLFlag); //Verify usage consumption dxl flag
    		Verification.VerifyBoolean(Flags.ManagementDXLFlag, true, "Management DXL Flag = true", "Management DXL Flag = " + Flags.ManagementDXLFlag); //Verify usage management dxl flag
    		Verification.VerifyString(Flags.OffersMinimumSupportVersion, "0", "Offers Minimum Support Version = 0", "Offers Minimum Support Version = " + Flags.OffersMinimumSupportVersion); //Verify offers min support version
    		Verification.VerifyString(Flags.CashMinimumSupportVersion, "403", "Cash Minimum Support Version = 403", "Cash Minimum Support Version = " + Flags.CashMinimumSupportVersion); //Verify cash min support version
    		Verification.VerifyString(Flags.Team010MinimumSupportVersion, "0", "010 Team Minimum Support Version = 0", "010 Team Minimum Support Version = " + Flags.Team010MinimumSupportVersion); //Verify 010 Team min support version
    		Verification.VerifyString(Flags.MenuMinimumSupportVersion, "0", "Menu Minimum Support Version = 0", "Menu Minimum Support Version = " + Flags.MenuMinimumSupportVersion); //Menu min support version
    		Verification.VerifyString(Flags.HomeMinimumSupportVersion, "0", "Home Minimum Support Version = 0", "Home Minimum Support Version = " + Flags.HomeMinimumSupportVersion); //Home min support version
    		Verification.VerifyString(Flags.MIMinimumSupportVersion, "0", "MI Minimum Support Version = 0", "MI Minimum Support Version = " + Flags.MIMinimumSupportVersion); //MI min support version
    		Verification.VerifyBoolean(Flags.EOYFlag, true, "EOY Flag = true", "EOY Flag = " + Flags.EOYFlag); //EOY Flag
    		Verification.VerifyBoolean(Flags.IsWeekendFlag, true, "Is Weekend promo Flag = true", "Is Weekend promo Flag = " + Flags.IsWeekendFlag); //EOY Flag
    		Verification.VerifyBoolean(Flags.NudgeFlag, true, "Nudge Days Flag = true", "Nudge Days Flag = " + Flags.NudgeFlag); //Nudge Days Flag
    		Verification.VerifyBoolean(Flags.CashPointsFlag, false, "Vodafone cash points Flag = false", "Vodafone cash points Flag = " + Flags.CashPointsFlag); //Cash points Flag
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Android Postpaid open Request========================================
    @Test(priority = 9)
    public void AndroidFlexRequest()
    {
    	try {	
    		response = ConfigurationEndPoints.AndroidConfigurationRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
    		jsonString = Verification.Success(response); //Verify status code
    		Flags= new ConfigurationFlags(response);
    		Verification.VerifyBoolean(Flags.ConsumptionDXLFlag, true, "Consumption DXL Flag = true", "Consumption DXL Flag = " + Flags.ConsumptionDXLFlag); //Verify usage consumption dxl flag
    		Verification.VerifyBoolean(Flags.ManagementDXLFlag, true, "Management DXL Flag = true", "Management DXL Flag = " + Flags.ManagementDXLFlag); //Verify usage management dxl flag
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Android Postpaid open Request========================================
    @Test(priority = 10)
    public void AndroidPostpaidRequest()
    {
    	try {	
    		response = ConfigurationEndPoints.AndroidConfigurationRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
    		jsonString = Verification.Success(response); //Verify status code
    		Flags= new ConfigurationFlags(response);
    		Verification.VerifyBoolean(Flags.ConsumptionDXLFlag, true, "Consumption DXL Flag = true", "Consumption DXL Flag = " + Flags.ConsumptionDXLFlag); //Verify usage consumption dxl flag
    		Verification.VerifyBoolean(Flags.ManagementDXLFlag, true, "Management DXL Flag = true", "Management DXL Flag = " + Flags.ManagementDXLFlag); //Verify usage management dxl flag
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================Android Prepaid Request========================================
    @Test(priority = 11)
    public void AndroidPrepaidRequest()
    {
    	try {	
    		response = ConfigurationEndPoints.AndroidConfigurationRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
    		jsonString = Verification.Success(response); //Verify status code
    		Flags= new ConfigurationFlags(response);
    		Verification.VerifyBoolean(Flags.ConsumptionDXLFlag, true, "Consumption DXL Flag = true", "Consumption DXL Flag = " + Flags.ConsumptionDXLFlag); //Verify usage consumption dxl flag
    		Verification.VerifyBoolean(Flags.ManagementDXLFlag, true, "Management DXL Flag = true", "Management DXL Flag = " + Flags.ManagementDXLFlag); //Verify usage management dxl flag
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================IOS Red Request===================================           
    @Test(priority = 12)
    public void IOSRedRequest()
    {
    	try {	
    		response = ConfigurationEndPoints.IOSConfigurationRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
    		jsonString = Verification.Success(response); //Verify status code
    		Flags= new ConfigurationFlags(response);
    		Verification.VerifyString(Flags.channelId, "AnaVodafoneIOS", "Channel Id = AnaVodafoneIOS", "Channel Id = " + Flags.channelId); //Verify Channel Id
    		Verification.VerifyString(Flags.latestBuildNumber, "170", "latestBuildNumber = 170", "Latest Build Number = " + Flags.latestBuildNumber); //Verify latest build number
    		Verification.VerifyString(Flags.updateType, "NONE", "updateType = NONE", "Update Type = " + Flags.updateType); //Verify update type
    		Verification.VerifyBoolean(Flags.ConsumptionDXLFlag, true, "Consumption DXL Flag = true", "Consumption DXL Flag = " + Flags.ConsumptionDXLFlag); //Verify usage consumption dxl flag
    		Verification.VerifyBoolean(Flags.ManagementDXLFlag, true, "Management DXL Flag = true", "Management DXL Flag = " + Flags.ManagementDXLFlag); //Verify usage management dxl flag
    		Verification.VerifyString(Flags.OffersMinimumSupportVersion, "0", "Offers Minimum Support Version = 0", "Offers Minimum Support Version = " + Flags.OffersMinimumSupportVersion); //Verify offers min support version
    		Verification.VerifyString(Flags.CashMinimumSupportVersion, "163", "Cash Minimum Support Version = 163", "Cash Minimum Support Version = " + Flags.CashMinimumSupportVersion); //Verify cash min support version
    		Verification.VerifyString(Flags.Team010MinimumSupportVersion, "0", "010 Team Minimum Support Version = 0", "010 Team Minimum Support Version = " + Flags.Team010MinimumSupportVersion); //Verify 010 Team min support version
    		Verification.VerifyString(Flags.MenuMinimumSupportVersion, "0", "Menu Minimum Support Version = 0", "Menu Minimum Support Version = " + Flags.MenuMinimumSupportVersion); //Menu min support version
    		Verification.VerifyString(Flags.HomeMinimumSupportVersion, "0", "Home Minimum Support Version = 0", "Home Minimum Support Version = " + Flags.HomeMinimumSupportVersion); //Home min support version
    		Verification.VerifyString(Flags.MIMinimumSupportVersion, "0", "MI Minimum Support Version = 0", "MI Minimum Support Version = " + Flags.MIMinimumSupportVersion); //MI min support version
    		Verification.VerifyBoolean(Flags.EOYFlag, true, "EOY Flag = true", "EOY Flag = " + Flags.EOYFlag); //EOY Flag
    		Verification.VerifyBoolean(Flags.IsWeekendFlag, true, "Is Weekend promo Flag = true", "Is Weekend promo Flag = " + Flags.IsWeekendFlag); //EOY Flag
    		Verification.VerifyBoolean(Flags.NudgeFlag, true, "Nudge Days Flag = true", "Nudge Days Flag = " + Flags.NudgeFlag); //Nudge Days Flag
    		Verification.VerifyBoolean(Flags.CashPointsFlag, false, "Vodafone cash points Flag = false", "Vodafone cash points Flag = " + Flags.CashPointsFlag); //Cash points Flag
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    } 
//===================================IOS Flex Request===================================           
    @Test(priority = 13)
    public void IOSFlexRequest()
    {
    	try {	
    		response = ConfigurationEndPoints.IOSConfigurationRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
    		jsonString = Verification.Success(response); //Verify status code
    		Flags= new ConfigurationFlags(response);
    		Verification.VerifyBoolean(Flags.ConsumptionDXLFlag, true, "Consumption DXL Flag = true", "Consumption DXL Flag = " + Flags.ConsumptionDXLFlag); //Verify usage consumption dxl flag
    		Verification.VerifyBoolean(Flags.ManagementDXLFlag, true, "Management DXL Flag = true", "Management DXL Flag = " + Flags.ManagementDXLFlag); //Verify usage management dxl flag
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }
//===================================IOS Postpaid Request===================================           
    @Test(priority = 14)
    public void IOSPostpaidRequest()
    {
    	try {	
    		response = ConfigurationEndPoints.IOSConfigurationRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
    		jsonString = Verification.Success(response); //Verify status code
    		Flags= new ConfigurationFlags(response);
    		Verification.VerifyBoolean(Flags.ConsumptionDXLFlag, true, "Consumption DXL Flag = true", "Consumption DXL Flag = " + Flags.ConsumptionDXLFlag); //Verify usage consumption dxl flag
    		Verification.VerifyBoolean(Flags.ManagementDXLFlag, true, "Management DXL Flag = true", "Management DXL Flag = " + Flags.ManagementDXLFlag); //Verify usage management dxl flag
    	} catch (IOException e) {ExtentReport.test.log(LogStatus.FAIL, "Exception in code happened and Auth status code is: " + Auth.StatusCode); e.printStackTrace();}
    }  
//===================================IOS Prepaid Request===================================           
    @Test(priority = 15)
    public void IOSPrepaidRequest()
    {
    	try {	
    		response = ConfigurationEndPoints.IOSConfigurationRequest(ExcelInit.MSISDN, ExcelInit.Password); //Auth using a number has points
    		jsonString = Verification.Success(response); //Verify status code
    		Flags= new ConfigurationFlags(response);
    		Verification.VerifyBoolean(Flags.ConsumptionDXLFlag, true, "Consumption DXL Flag = true", "Consumption DXL Flag = " + Flags.ConsumptionDXLFlag); //Verify usage consumption dxl flag
    		Verification.VerifyBoolean(Flags.ManagementDXLFlag, true, "Management DXL Flag = true", "Management DXL Flag = " + Flags.ManagementDXLFlag); //Verify usage management dxl flag
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
