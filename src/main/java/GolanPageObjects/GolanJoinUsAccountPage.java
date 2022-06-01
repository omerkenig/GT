package GolanPageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GolanJoinUsAccountPage extends BasePage {

	public GolanJoinUsAccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(fakeBtn);
	}

	@FindBy(xpath = "//button[@class='btn-red']")
	public WebElement fakeBtn;

	@FindBy(xpath = "(//span[@class='custom-control-description'])[1]")
	public WebElement privateCutsomerBtn;

	@FindBy(xpath = "(//span[@class='custom-control-description'])[2]")
	public WebElement businessCustomerBtn;

	@FindBy(xpath = "//input[@id='privateLineIdInput']")
	public WebElement IdText;

	@FindBy(xpath = "//input[@id='email']")
	public WebElement emailText;

	@FindBy(xpath = "//*[contains(text(), 'נקודת איסוף')]")
	public WebElement simNearHomeBtn;

	@FindBy(xpath = "//*[contains(text(), 'דואר רגיל')]")
	public WebElement simRegularPostBtn;

	@FindBy(xpath = "//*[contains(text(), 'משלוח אקספרס')]")
	public WebElement simExpressPostBtn;

	@FindBy(xpath = "//*[contains(text(), 'באזור האישי')]")
	public WebElement invoicesWeb;

	@FindBy(xpath = "//*[contains(text(), 'אימייל')]")
	public WebElement invoicesMail;

	@FindBy(xpath = "//div[text()='דואר']")
	public WebElement invoicesPost;

	@FindBy(xpath = "//*[contains(text(), 'מעוניין לקבל גם מידע שיווקי')]")
	public WebElement MarketingInfoAnd_adver;

	@FindBy(xpath = "//*[contains(text(), 'מידע נוסף')]")
	public WebElement otherInformation;

	@FindBy(xpath = "//*[contains(text(), 'אינני מעוניין לקבל פרסומות')]")
	public WebElement iDontWantMoreInformation;

	@FindBy(xpath = "//button[contains(text(),'המשך')]")
	public WebElement continueBtn;

	public GolanJoinUsPaymentPage fillDetails(String personalId, String email) throws InterruptedException {

		click(fakeBtn, "Fake Button");
		IdText.clear();
		emailText.clear();
//		Thread.sleep(2000);


		type(emailText, email, "Email account");

		scrollByVisibilityOfElement(driver, simRegularPostBtn);
		Thread.sleep(1000);

		click(simRegularPostBtn, "Order Sim Regular Post");
		click(invoicesMail, "Get Invoices By Mail");
		
		click(simRegularPostBtn, "Sim Regular Post Button");

		click(continueBtn, "Contiune Button");
		type(IdText, personalId, "Id number");
		click(continueBtn, "Contiune Button");


		return new GolanJoinUsPaymentPage(driver);

	}

	public void aprove() {

	}

}
