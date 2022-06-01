package GolanPageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.BaseTestMethod;

import utilties.DriverManager;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GolanPersonalAreaPage extends BasePage {

	Actions actions = new Actions(driver);

	WebDriverWait wait = new WebDriverWait(driver, LOAD_TIMEOUT);

	public GolanPersonalAreaPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(phoneNumberText);
	}

	@FindBy(id = "phoneNumberCtrl")
	public WebElement phoneNumberText;

	@FindBy(xpath = "//button[@role='button']")
	public WebElement approveBtn;

	@FindBy(xpath = "//input[@id='otp-verify']")
	public WebElement smsValidationText;

	@FindBy(xpath = "//button[@role='button']")
	public WebElement approveSms;

	@FindBy(xpath = "//button[@class='btn btn-link btn-only-text']")
	public WebElement validateSms;

	//div[contains(@class, 'lines-total-main')]/app-join-us-flow-summary-lines-popup/div/div/div[2]/lib-rc-radios/div/div

	public void enterPhoneNumber() throws InterruptedException {

		type(phoneNumberText, mobileNumber, "Mobile Number");
		click(approveBtn, "Approve Button");

	}

	public GolanDashboardPage enterLastSms() throws InterruptedException {

		type(smsValidationText, lastSmsNumber, "SMS Number");
		click(approveSms, "Approve Button");
		approveSms.sendKeys(Keys.ENTER);

		return new GolanDashboardPage(driver);

	}
	


}
