package Configuration;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ConfigurationFlags 
{
	String ModuleIndex = "0";
	String FeatureIndex = "0";
	String APIIndex = "0";
	int APISize = 0;
	int ModuleSize = 0;
	int FeatureSize = 0;
	//=============Channel Configuration general Flags==========
	String channelId = "does not exist";
	String latestBuildNumber = "does not exist";
	String updateType = "does not exist";
	//===========Channel Configuration dxl api Flags===========
	String APIId = "does not exist";
	boolean ConsumptionDXLFlag = false;
	boolean ManagementDXLFlag = false;
	//=======================Module Flags======================
	String ModuleId = "does not exist";
	String FeatureId = "does not exist";
	//----------------------offers--------------------
	String OffersMinimumSupportVersion = "does not exist";
	//EOY feature
	boolean EOYFlag = false;
	//----------------------Vodafone Cash--------------------
	String CashMinimumSupportVersion = "does not exist";
	//Cash points feature
	boolean CashPointsFlag = false;
	//----------------------010 team--------------------
	String Team010MinimumSupportVersion = "does not exist";
	//Is weekend promo feature
	boolean IsWeekendFlag = false;
	//----------------------Menu--------------------
	String MenuMinimumSupportVersion = "does not exist";
	//----------------------Home--------------------
	String HomeMinimumSupportVersion = "does not exist";
	//----------------------Mobile Internet--------------------
	String MIMinimumSupportVersion = "does not exist";
	//Nudge feature
	boolean NudgeFlag = false;
//=======================Constructor to extract Flags from response=================
	public ConfigurationFlags(Response response)
	{
		String jsonString = response.asString(); //Convert response to string
		channelId = JsonPath.from(jsonString).get("channelId"); //get channel id (Android or IOS)
		latestBuildNumber = JsonPath.from(jsonString).get("latestBuildNumber"); //get Latest build number
		updateType = JsonPath.from(jsonString).get("updateType"); //get update type
		//------------------------------API loop------------------------
		List<String> APIList = response.jsonPath().getList("api"); // get api list
		APISize = APIList.size(); //Get size of api array
		for (int APIIterator = 0; APIIterator < APISize; APIIterator++)
		{
			APIIndex = Integer.toString(APIIterator);
			APIId =  JsonPath.from(jsonString).get("api.id["+APIIndex+"]"); //get api id
			if (APIId.equals("USE_CONS"))
			{
				ConsumptionDXLFlag = JsonPath.from(jsonString).get("api.dxlFlag["+APIIndex+"]"); //get usage consumption dxl flag
			}
			else if (APIId.equals("USG_MNG"))
			{
				ManagementDXLFlag = JsonPath.from(jsonString).get("api.dxlFlag["+APIIndex+"]"); //get usage management dxl flag
			}
		}
		//----------------------------Module loop------------------------
		List<String> ModuleList = response.jsonPath().getList("module"); // get module list
		ModuleSize = ModuleList.size(); //Get size of api array
		for (int ModuleIterator = 0; ModuleIterator < ModuleSize; ModuleIterator++)
		{
			ModuleIndex = Integer.toString(ModuleIterator);
			ModuleId =  JsonPath.from(jsonString).get("module.id["+ModuleIndex+"]");
			if (ModuleId.equals("user_offers"))
			{
				try {
					OffersMinimumSupportVersion = JsonPath.from(jsonString).get("module.minimumSupportVersion["+ModuleIndex+"]"); //get offers module min version number
					//-----------------offers Feature loop---------------------------
					List<String> OffersFeatureList = response.jsonPath().getList("module.feature["+ModuleIndex+"]"); // get offers features list
					FeatureSize = OffersFeatureList.size(); //Get size of offers feature array
					for (int Featureterator = 0; Featureterator < FeatureSize; Featureterator++)
					{
						FeatureIndex = Integer.toString(Featureterator);
						FeatureId =  JsonPath.from(jsonString).get("module.feature["+ModuleIndex+"].id["+FeatureIndex+"]"); //get feature id
						if (FeatureId.equals("EOY"))
						{
							EOYFlag = JsonPath.from(jsonString).get("module.feature["+ModuleIndex+"].flag["+FeatureIndex+"]"); //get EOY flag
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (ModuleId.equals("vodafone_cash"))
			{
				try {
					CashMinimumSupportVersion = JsonPath.from(jsonString).get("module.minimumSupportVersion["+ModuleIndex+"]"); //get vodafone cash module min version number
					//-----------------Cash points Feature loop---------------------------
					List<String> CashFeatureList = response.jsonPath().getList("module.feature["+ModuleIndex+"]"); // get cash points features list
					FeatureSize = CashFeatureList.size(); //Get size of offers feature array
					for (int Featureterator = 0; Featureterator < FeatureSize; Featureterator++)
					{
						FeatureIndex = Integer.toString(Featureterator);
						FeatureId =  JsonPath.from(jsonString).get("module.feature["+ModuleIndex+"].id["+FeatureIndex+"]"); //get feature id
						if (FeatureId.equals("cashPoints"))
						{
							CashPointsFlag = JsonPath.from(jsonString).get("module.feature["+ModuleIndex+"].flag["+FeatureIndex+"]"); //get cash points flag
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (ModuleId.equals("010_team"))
			{
				try {
					Team010MinimumSupportVersion = JsonPath.from(jsonString).get("module.minimumSupportVersion["+ModuleIndex+"]"); //get 010 team module min version number
					//-----------------010 teams Feature loop---------------------------
					List<String> Team010FeatureList = response.jsonPath().getList("module.feature["+ModuleIndex+"]"); // get 010 team features list
					FeatureSize = Team010FeatureList.size(); //Get size of offers feature array
					for (int Featureterator = 0; Featureterator < FeatureSize; Featureterator++)
					{
						FeatureIndex = Integer.toString(Featureterator);
						FeatureId =  JsonPath.from(jsonString).get("module.feature["+ModuleIndex+"].id["+FeatureIndex+"]"); //get feature id
						if (FeatureId.equals("isWeekendPromo"))
						{
							IsWeekendFlag = JsonPath.from(jsonString).get("module.feature["+ModuleIndex+"].flag["+FeatureIndex+"]"); //get isWeekendPromo flag
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if (ModuleId.equals("Menu"))
			{
				MenuMinimumSupportVersion = JsonPath.from(jsonString).get("module.minimumSupportVersion["+ModuleIndex+"]"); //get Menu module min version number
			}
			else if (ModuleId.equals("Home"))
			{
				HomeMinimumSupportVersion = JsonPath.from(jsonString).get("module.minimumSupportVersion["+ModuleIndex+"]"); //get Home module min version number
			}
			else if (ModuleId.equals("mobile internet"))
			{
				try {
					MIMinimumSupportVersion = JsonPath.from(jsonString).get("module.minimumSupportVersion["+ModuleIndex+"]"); //get Home module min version number
					//-----------------mobile internet Feature loop---------------------------
					List<String> MIFeatureList = response.jsonPath().getList("module.feature["+ModuleIndex+"]"); // get offers features list
					FeatureSize = MIFeatureList.size(); //Get size of offers feature array
					for (int Featureterator = 0; Featureterator < FeatureSize; Featureterator++)
					{
						FeatureIndex = Integer.toString(Featureterator);
						FeatureId =  JsonPath.from(jsonString).get("module.feature["+ModuleIndex+"].id["+FeatureIndex+"]"); //get feature id
						if (FeatureId.equals("nudgeDays"))
						{
							NudgeFlag = JsonPath.from(jsonString).get("module.feature["+ModuleIndex+"].flag["+FeatureIndex+"]"); //get nudgeDays flag
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	} //End of constructor
//=================================Test==================================
	public static void main( String[] args )
    {
		Response response= ConfigurationEndPoints.AndroidConfigurationRequest("1030693069", "Test@1234");
		ConfigurationFlags Obj = new ConfigurationFlags(response);
		
		System.out.println("channelId "+Obj.channelId);
		System.out.println("latestBuildNumber "+Obj.latestBuildNumber);
		System.out.println("updateType "+Obj.updateType);
		System.out.println("ConsumptionDXLFlag "+Obj.ConsumptionDXLFlag);
		System.out.println("ManagementDXLFlag "+Obj.ManagementDXLFlag);
		System.out.println("OffersMinimumSupportVersion "+Obj.OffersMinimumSupportVersion);
		System.out.println("CashMinimumSupportVersion "+Obj.CashMinimumSupportVersion);
		System.out.println("Team010MinimumSupportVersion "+Obj.Team010MinimumSupportVersion);
		System.out.println("MenuMinimumSupportVersion "+Obj.MenuMinimumSupportVersion);
		System.out.println("HomeMinimumSupportVersion "+Obj.HomeMinimumSupportVersion);
		System.out.println("MIMinimumSupportVersion "+Obj.MIMinimumSupportVersion);
		System.out.println("EOYFlag "+Obj.EOYFlag);
		System.out.println("IsWeekendFlag "+Obj.IsWeekendFlag);
		System.out.println("NudgeFlag "+Obj.NudgeFlag);
    }
}
