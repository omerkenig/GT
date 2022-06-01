package testCases;

import org.testng.annotations.Test;

import GolanPageObjects.GolanHomePage;
import GolanPageObjects.GolanJoinFULL;
import GolanPageObjects.GolanUserGuidePage;
import rough.BaseTest;

import java.util.Hashtable;
import utilties.Constants;
import utilties.DataProviders;
import utilties.DataUtil;
import utilties.DriverManager;
import utilties.ExcelReader;

public class LoginAsExistSubscriber extends BaseTest {

	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void loginAsNewSubscriber(Hashtable<String, String> data)
			throws InterruptedException {
		
		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "LoginAsExistSubscriber", data.get("Runmode"), excel);
		
		openBrowser(data.get("browser"));

		GolanHomePage gHome = new GolanHomePage().open("http://new.web.staging/");
		GolanUserGuidePage gGuide = new GolanUserGuidePage(DriverManager.getDriver());
		GolanJoinFULL gJoin = new GolanJoinFULL(DriverManager.getDriver());
		
//		gHome.chooseFirstPackage();

		Thread.sleep(2000);

	}
}
