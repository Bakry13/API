package Utilities;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport
{
	public static int TCNumb = 1;
	public static int TestId = 0;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String[] Extent_Report = {"Loyalty","Consumption", "Management","FlexCoins","AlertingServices","MI", "Configuration", "USB"};
//===========================================================================================================	
	@BeforeTest
	public void StartReport() 
	{
		extent = new ExtentReports(System.getProperty("user.dir") + "/Reports/" + Extent_Report[TestId] + ".html");
	}
//-----------------------------------------------------------------------------------------------------------
	@AfterTest
	public void EndReport()
	{
		extent.flush();
		extent.close();
		TestId++;
	}
//===========================================================================================================	
	public static void StartEndTC(String KeywordAction, String TCName) throws IOException
	{
		String TCNumber = "1";
		String TestCaseName = "0";
		if (KeywordAction.equals("StartOfTC") || KeywordAction.equals("EndOfTest"))
		{
			if (TCNumb != 1) // if it is not the first test case we will end the previous one
			{
				extent.endTest(test); //close the test case in extent report
				System.out.println("TC Ended");
			}
			if ((KeywordAction.equals("StartOfTC")))// if it is not the last test case we will start the next one
			{
				TCNumber = Integer.toString(TCNumb++); //Counting test case number
				TestCaseName = TCNumber + "-  " + TCName; //store test case name with its number
				test = extent.startTest(TestCaseName); //Test Case name in extent report
			}
		}
	}
}
