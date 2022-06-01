package rough;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import CRMPageObjects.CrmAccountPage;
import CRMPageObjects.CrmLoginlPage;
import CRMPageObjects.CrmPortalPage;
import CRMPageObjects.CrmRightPanelPage;
import ExtentListeners.ExtentListeners;
import GolanPageObjects.BasePage;
import GolanPageObjects.GolanDashboardPage;
import GolanPageObjects.GolanHomePage;
import GolanPageObjects.GolanJoinUsAccountPage;
import GolanPageObjects.GolanJoinUsFlowPage;
import GolanPageObjects.GolanJoinUsPaymentPage;
import GolanPageObjects.GolanPersonalAreaPage;
import GolanPageObjects.GolanUpperPanelPage;
import GolanPageObjects.GolanUserGuidePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilties.DriverFactory;
import utilties.DriverManager;

public class BaseTest {

	private WebDriver driver;
	private Properties config = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseTest.class);
	public static String golanTab;
	public static String crmTab;
	
	protected String oldTab = null;
	ArrayList<String> newTab;
	


	@BeforeSuite
	public void setUpFramework() {

		ConfigureLogging();
		DriverFactory.setGridPath("http://localhost:4444/wd/hub");
		DriverFactory.setConfigPropertiesFile(
				System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
//
//		if (System.getProperty("os.name").contains("Linux")) {
//
//			DriverFactory.setChromeDriverExePath(
//					System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver");
//			DriverFactory.setGeckoDriverExePath(
//					System.getProperty("u		BaseTest.switchTab();\n" + 
//							"ser.dir") + "/src/test/resources/executables/geckodriver");
//
//		} else {
//			DriverFactory.setChromeDriverExePath(
//					System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver.exe");
//			DriverFactory.setGeckoDriverExePath(
//					System.getProperty("user.dir") + "/src/test/resources/executables/geckodriver.exe");
//		}

		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertiesFile());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			config.load(fis);
			log.info("Configuration file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loginfo(String message) {

		ExtentListeners.testReport.get().info(message);

	}
	
	public void switchTabs() {
		
		Actions actions = new Actions(driver);

		actions.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
	}


	public void ConfigureLogging() {

		String log4jConfigFile = System.getProperty("user.dir") + "/src/test/resources/properties/log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);

	}

	@BeforeMethod
	static void setupClass() throws InvocationTargetException {

	
		try {
			initElements();
			WebDriverManager.chromedriver().setup();

	} catch (Exception e) {
	        // generic exception handling
	        e.printStackTrace();
	}

	}

	@SuppressWarnings("deprecation")
	public void openBrowser(String browser) {

		if (browser.equals("chrome")) {

			System.out.println("Launching : " + browser);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Chrome browser launched !!!");

		} else if (browser.equals("firefox")) {

			System.out.println("Launching : " + browser);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("FireFox browser launched !!!");

		}

		DriverManager.setWebDriver(driver);
//		DriverManager.getDriver().get("http://new.web.staging/");
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	public boolean isDisplayed(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed");
			} else {
				System.out.println("The element is not Displayed");
			}
		} else {
			System.out.println("Not displayed ");
		}
		return flag;
	}
	
	public boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			// System.out.println("Location not found: "+locatorName);
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element at : " + ele);

			} else {
				System.out.println("Unable to locate element at : " + ele);
			}
		}
		return flag;
	}

	public static void initElements() {

	}
	
	public void openNewTab(String webSite) throws InterruptedException {

		
		DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
		DriverManager.getDriver().get(webSite);


	

//		Robot r = new Robot(); 
//		r.keyPress(KeyEvent.VK_CONTROL); 
//		r.keyPress(KeyEvent.VK_T); 
//		r.keyRelease(KeyEvent.VK_CONTROL); 
//		r.keyRelease(KeyEvent.VK_T);   
//		DriverManager.getDriver().switchTo().newWindow(WindowType.TAB);
//
//		DriverManager.getDriver().get(webSite);

	}

	
	public void testTabs() {

	    // considering that there is only one tab opened in that point.


		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> it = tabs.iterator();
		golanTab = it.next();
		crmTab = it.next();
		
//		driver.switchTo().window(golanTab);
//		driver.close();
//		driver.switchTo().window(crmTab);
	}
	
	public void switchTabCrm() throws InterruptedException {

		DriverManager.getDriver().switchTo().window(BasePage.CRMWindowHandle);
	}
	

	public void quit() {

//		DriverManager.getDriver().quit();
	}

	@AfterMethod
	public void tearDown() {
		loginfo("Test Successfilly Completed");

		quit();

	}

}
