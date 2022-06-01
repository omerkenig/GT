package GolanPageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.BaseTestMethod;

import utilties.DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GolanUpperPanelPage extends BasePage {

	Actions actions = new Actions(driver);

	WebDriverWait wait = new WebDriverWait(driver, LOAD_TIMEOUT);

	public GolanUpperPanelPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(packageBtn);
	}

	@FindBy(xpath = "//img[@src='/themes/golan/logo1.png']")
	public WebElement golanLogo;

	@FindBy(xpath = "//a[@href='/offers']")
	public WebElement packageBtn;

	@FindBy(xpath = "//a[@href='/overseas_offers']")
	public WebElement overseasBtn;

	@FindBy(xpath = "//a[@href='/services']")
	public WebElement servicesBtn;

	@FindBy(xpath = "//a[@href='/99']")
	public WebElement allIncludeBtn;

	@FindBy(xpath = "//a[@href='/Golanoffer']")
	public WebElement roolingBtn;

	@FindBy(xpath = "//a[@class='btn btn-primary btn-lg blue']")
	public WebElement activeateSimBtn;

	@FindBy(xpath = "//div[@class='top-header only-desktop']//a[@role='button'][contains(text(),'האזור האישי שלי')]")
	public WebElement personalAreaBtn;

	@FindBy(id = "phoneNumberCtrl")
	public WebElement phoneNumberText;

	@FindBy(xpath = "//button[@class='btn btn-white']")
	public WebElement approveBtn;

	@FindBy(id = "otp-verify")
	public WebElement verifyCodeText;

	@FindBy(id = "//button[@class='btn btn-white']")
	public WebElement approveSmsBtn;

	public void clickOnPackages() {

		click(packageBtn, "Click on the Packages Tab");
//		return new GolanUserGuidePage(driver);

	}

	public boolean validateLogoExist() throws Throwable {
		return isDisplayed(driver, golanLogo);
	}

	public GolanPersonalAreaPage enterPersonalArea() {

		click(personalAreaBtn, "Personal Area Button");
		click(personalAreaBtn, "Personal Area Button");
		return new GolanPersonalAreaPage(driver);

	}

}
