package GolanPageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.junit.ArrayAsserts;

import ExtentListeners.ExtentListeners;
import utilties.DriverManager;

public abstract class BasePage<T> {

	protected WebDriver driver;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	public static String iframetotal;
	protected long LOAD_TIMEOUT = 30;
	private int AJAX_ELEMENT_TIMEOUT = 10;
	public static String mobileNumber = null;
	public static int totalCost = 0;
	public static String accountNumber = null;
	public static String lastFourDigitCreditCard = null;
	public static String lineNumber = null;
	public static String sims = null;
	public static String imsiNumber = null;
	public static String lastSmsNumber = null;
	public static String simActiveCode = null;
	public static String textToCheck = null;
	public static String personalId = null;
	Random rand = new Random();


	public static String golanWindowHandle;
	public static String CRMWindowHandle;

	public static WebElement waitUntilElementVisible(WebDriver driver, WebElement element, int delay) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, delay);
			return wait.until(ExpectedConditions.visibilityOf(element));
		} catch (NoSuchElementException e) {
			throw new RuntimeException("Web element not visible within given time" + element + " Time " + delay);
		}
	}

	public void scrollByVisibilityOfElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		ExtentListeners.testReport.get().info("Scroll to " + element);

	}

	public BasePage() {

		this.driver = DriverManager.getDriver();
	}

	public T openPage(Class<T> clazz) {

		T page = null;
		driver = DriverManager.getDriver();
		AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, 10);
		page = PageFactory.initElements(driver, clazz);
		PageFactory.initElements(ajaxElemFactory, page);
		ExpectedCondition PageLoadCondition = ((BasePage) page).getPageLoadCondition();
		waitForPageToLoad(PageLoadCondition);
		return page;

	}

	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
		WebDriverWait wait = new WebDriverWait(driver, LOAD_TIMEOUT);
		wait.until(pageLoadCondition);
	}

	protected abstract ExpectedCondition getPageLoadCondition();

	public void click(WebElement element, String elementName) {

		try {
			element.click();
			ExtentListeners.testReport.get().info("Clicking on : " + elementName);
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage() + "On elemenet " + elementName);

		}
	}

	public void type(WebElement element, String value, String elementName) {

		try {
			element.clear();
			element.sendKeys(value);
			ExtentListeners.testReport.get()
					.info("typing in  : " + elementName + " and entered the value as : " + value);
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage() + "On elemenet " + elementName);
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param value       : Value wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 */
	public void explicitWait(WebDriver driver, WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static String getRandomNumberString() {

		// generate a 4 digit integer 1000 <10000
		lastFourDigitCreditCard = "" + ((int) (Math.random() * 9000) + 1000);
		System.out.println("The last four digit crad is : " + lastFourDigitCreditCard);
		return lastFourDigitCreditCard;

	}

	public boolean selectByValue(WebElement element, String value, String elementName) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				ExtentListeners.testReport.get().info("typing in  : " + element + " entered the value as : " + value);

			} else {
				System.out.println("Option not selected by Value");
			}
		}
	}

	public void fluentWait(WebDriver driver, WebElement element, int timeOut) {
		Wait<WebDriver> wait = null;
		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(20))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
		} catch (Exception e) {
		}
	}

	public boolean selectByIndex(WebElement element, String index, String elementName) {
		boolean flag = false;
		try {
			element.click();
			Select s = new Select(element);
			int j = Integer.parseInt(index);
			for (int i = 0; i < j; i++) {
				element.sendKeys(Keys.DOWN);
			}
			element.sendKeys(Keys.ENTER);

			int i = Integer.parseInt(index);

			s.selectByIndex(i);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Index");
			} else {
				System.out.println("Option not selected by Index");
			}
		}
	}

	public static int findFrameNumber(WebDriver driver, By by) {

		int i;
		int framecount = driver.findElements(By.tagName("iframe")).size();

		for (i = 0; i < framecount; i++) {
			driver.switchTo().frame(i);

			int count = driver.findElements(by).size();
			if (count > 0) {
				break;
			} else {
				System.out.println("continue looping");
			}
		}
		driver.switchTo().defaultContent();
		return i;
	}

	public Object deleteAllnoDigitNumber(String orginalText) {

		return orginalText.replaceAll("\\D+", "");

	}

	public boolean IsElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
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

}
