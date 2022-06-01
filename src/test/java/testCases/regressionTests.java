package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import CRMPageObjects.CrmAccountPage;
import CRMPageObjects.CrmLoginlPage;
import CRMPageObjects.CrmPortalPage;
import CRMPageObjects.CrmRightPanelPage;
import GolanPageObjects.BasePage;
import GolanPageObjects.GolanDashboardPage;
import GolanPageObjects.GolanHomePage;
import GolanPageObjects.GolanJoinUsAccountPage;
import GolanPageObjects.GolanJoinUsFlowPage;
import GolanPageObjects.GolanJoinUsPaymentPage;
import GolanPageObjects.GolanPersonalAreaPage;
import GolanPageObjects.GolanUpperPanelPage;
import GolanPageObjects.GolanUserGuidePage;
import rough.BaseTest;

import java.util.ArrayList;
import java.util.Hashtable;
import utilties.Constants;
import utilties.DataProviders;
import utilties.DataUtil;
import utilties.DriverManager;
import utilties.ExcelReader;

import static GolanPageObjects.BasePage.textToCheck;

public class regressionTests extends BaseTest {

	ArrayList<String> simsFreeList = new ArrayList<String>();
	int count = 0;
	boolean resualt = false;

	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void loginAsNewSubscriberCrediCard(Hashtable<String, String> data) throws Throwable {

		ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
		DataUtil.checkExecution("master", "LoginAsNewSubscriber", data.get("Runmode"), excel);

		openBrowser(data.get("browser"));
		loginfo("launched browser : " + data.get("browser"));

		GolanHomePage gHome = new GolanHomePage().open("http://new.web.staging/");
		GolanUserGuidePage gGuide = new GolanUserGuidePage(DriverManager.getDriver());
		GolanJoinUsFlowPage gJoin = new GolanJoinUsFlowPage(DriverManager.getDriver());
		GolanJoinUsAccountPage gJoinAcc = new GolanJoinUsAccountPage(DriverManager.getDriver());
		CrmLoginlPage crmLogin = new CrmLoginlPage(DriverManager.getDriver());
		CrmRightPanelPage crmRightPanel = new CrmRightPanelPage(DriverManager.getDriver());
		CrmPortalPage crmPortal = new CrmPortalPage(DriverManager.getDriver());
		CrmAccountPage crmAccount = new CrmAccountPage(DriverManager.getDriver());
		GolanUpperPanelPage golanUp = new GolanUpperPanelPage(DriverManager.getDriver());
		GolanJoinUsPaymentPage gJoinPay = new GolanJoinUsPaymentPage(DriverManager.getDriver());
		GolanPersonalAreaPage gPersonal = new GolanPersonalAreaPage(DriverManager.getDriver());
		GolanDashboardPage gDashboard = new GolanDashboardPage(DriverManager.getDriver());

		// connect to DB
		DbManager.setDbConnection();
		DbManager.resetCreditCardConnection();

		// Get list of free sims from DB
		simsFreeList = DbManager.simsList();
		System.out.println(simsFreeList);

		gHome.validateLogoExist();
		gHome.chooseFirstPackage();
		gGuide.wantToJoin();
		gJoin.addNewLineNumber();
		gJoin.noPublishOn144();
		gJoin.approve();
		gJoinAcc.fillDetails(data.get("personalId"), data.get("email"));
		gJoinPay.enterCreditCardInforamtion("cardNameHolder", data.get("creditCardNumber"), data.get("expMonth"),
				data.get("expYear"), data.get("cvv"), data.get("personalId"));

		openNewTab("http://web.staging/crm3");
		crmLogin.loginToCRM(data.get("usernameCRM"), data.get("passwordCRM"));
		testTabs();
		crmRightPanel.search();
		crmAccount.enterSimNumber();
//		do {
		resualt = crmAccount.registerAccountWithSim(simsFreeList.get(count));
//			count++;

//		} while (resualt != true);
//		System.out.println(count);

			DriverManager.getDriver().switchTo().window(golanTab);
			golanUp.enterPersonalArea();
			gPersonal.enterPhoneNumber();
			DriverManager.getDriver().switchTo().window(crmTab);
			crmRightPanel.search();
			crmAccount.getLastSmsNumber();
			crmAccount.getActiveSimCode();
			DriverManager.getDriver().switchTo().window(golanTab);
			gPersonal.enterLastSms();

		golanUp.enterPersonalArea();
		gDashboard.activeNewSim();
		gDashboard.enterActiveSimCode();
		gDashboard.checkIfSimIsActivate();
		Assert.assertTrue(textToCheck.contains(data.get("ExpectedResult")));

	}
	

}
