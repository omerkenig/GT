package testCases;

import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import CRMPageObjects.CrmAccountPage;
import CRMPageObjects.CrmLoginlPage;
import CRMPageObjects.CrmPortalPage;
import CRMPageObjects.CrmRightPanelPage;
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
import static GolanPageObjects.BasePage.totalCost;

public class LoginAsNewSubscriber extends BaseTest {

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

//		 //connect to DB
		DbManager.setDbConnection();
		DbManager.resetCreditCardConnection();

		 //Get list of free sims from DB
		simsFreeList = DbManager.simsList();
		System.out.println(simsFreeList);

//		gHome.validateLogoExist();
		gHome.chooseFirstPackage();
		gGuide.wantToJoin();
		gJoin.addNewLineNumber();
		gJoin.noPublishOn144();
		gJoin.approve();
		gJoinAcc.fillDetails(data.get("personalId"), data.get("email"));
		gJoinPay.enterCreditCardInforamtion("UserName", data.get("creditCardNumber"), data.get("expMonth"),
				data.get("expYear"), data.get("cvv"), data.get("personalId"));

//		Thread.sleep(7000);
		DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
		DriverManager.getDriver().get("http://web.staging/crm3");
//		openNewTab("http://web.staging/crm3");
		crmLogin.loginToCRM(data.get("usernameCRM"), data.get("passwordCRM"));
		testTabs();
		crmRightPanel.search();
		crmAccount.enterSimNumber();
		do {
		resualt = crmAccount.registerAccountWithSim(simsFreeList.get(count));
			count++;

		} while (resualt != true);
		System.out.println(count);

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
//		gDashboard.checkIfSimIsActivate();
		Assert.assertFalse(isDisplayed(DriverManager.getDriver(), gDashboard.activeSimLnk));
	}

	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void loginAsNewSubscriberPayPal(Hashtable<String, String> data) throws Throwable {

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
		gJoinPay.enterPaypalInforamtion(data.get("paypalMail"), data.get("paypalPassword"));

		openNewTab("http://web.staging/crm3");
		crmLogin.loginToCRM(data.get("usernameCRM"), data.get("passwordCRM"));
		testTabs();
		crmRightPanel.search();
		crmAccount.enterSimNumber();
		resualt = crmAccount.registerAccountWithSim(simsFreeList.get(count));

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

	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void loginAsExistSubscriber(Hashtable<String, String> data) throws Throwable {

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
//		DbManager.resetCreditCardConnection();

		// Get list of free sims from DB
//		simsFreeList = DbManager.simsList();
//		System.out.println(simsFreeList);

//		gHome.validateLogoExist();
//		gHome.chooseFirstPackage();
//		gGuide.wantToJoin();
//		gJoin.addNewLineNumber();
//		gJoin.noPublishOn144();
//		gJoin.approve();
//		gJoinAcc.fillDetails(data.get("personalId"), data.get("email"));
//		gJoinPay.enterPaypalInforamtion(data.get("paypalMail"), data.get("paypalPassword"));

		openNewTab("http://web.staging/crm3");
		crmLogin.loginToCRM(data.get("usernameCRM"), data.get("passwordCRM"));
		testTabs();

		crmRightPanel.enterPortal();
		crmPortal.searchByLastFourDigitCard();

	}

	@Test(dataProviderClass = DataProviders.class, dataProvider = "masterDP")
	public void existSubscriberAddNewLine(Hashtable<String, String> data) throws Throwable {

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

		openNewTab("http://web.staging/crm3");
		crmLogin.loginToCRM(data.get("usernameCRM"), data.get("passwordCRM"));
		testTabs();

		crmRightPanel.enterPortal();
		crmPortal.searchByLastFourDigitCard();
		crmAccount.getLineInformation();
		DriverManager.getDriver().switchTo().window(golanTab);
		golanUp.enterPersonalArea();
		gPersonal.enterPhoneNumber();
		DriverManager.getDriver().switchTo().window(crmTab);
		crmRightPanel.search();
		crmAccount.getLastSmsNumber();
		DriverManager.getDriver().switchTo().window(golanTab);
		gPersonal.enterLastSms();

		golanUp.enterPersonalArea();
		golanUp.enterPersonalArea();
		gDashboard.addNewLines(data.get("numberOfLineToAdd"));
		gDashboard.chooseSequenceNumbers(data.get("promoOn144"), data.get("SaveForAllLines144"), data.get("NewSim"),
				data.get("CarSim"), data.get("SaveForAllLinesSim"));
//		gDashboard.choosePackage();

		for (int i = 0; i < Integer.parseInt(data.get("numberOfLineToAdd")); i++) {
			gDashboard.choosePackage();
		}
		Assert.assertTrue(gDashboard.checkPayment());

		gJoinAcc.fillDetails(data.get("personalId"), data.get("email"));


		
		
//		gPersonal.addNewLines(data.get("numberOfLineToAdd"));

//		gPersonal.enterPhoneNumber();
//		DriverManager.getDriver().switchTo().window(crmTab);
//		crmRightPanel.search();
//		crmAccount.getLastSmsNumber();
//		DriverManager.getDriver().switchTo().window(golanTab);
//		gPersonal.enterLastSms();
//		
	}

}
