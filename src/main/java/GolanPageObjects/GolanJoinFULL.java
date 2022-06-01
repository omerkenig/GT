package GolanPageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GolanJoinFULL extends BasePage<Object> {

//	Actions actions = new Actions(driver);
	WebDriverWait wait = new WebDriverWait(driver, 20);

	public GolanJoinFULL(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(newNumberBtn);

	}

	@FindBy(xpath = "//span[text()='בחירת מספר חדש']")
	public WebElement newNumberBtn;

	@FindBy(xpath = "//span[text()='שמירת מספר קיים']")
	public WebElement keepNumberBtn;

	@FindBy(xpath = "//span[text()='הציעו לי מספר']")
	public WebElement suggestNumberBtn;

	@FindBy(xpath = "//button[text()='מעוניין בקו כשר?']")
	public WebElement kosherlineBtn;

	@FindBy(xpath = "//div[@class='btn-red']")
	public WebElement checkBtn;

	@FindBy(xpath = "(//span[@class='custom-control-description'])[1]")
	public WebElement publish144Btn;

	@FindBy(xpath = "(//span[@class='custom-control-description'])[2]")
	public WebElement notPublish144Btn;

	@FindBy(xpath = "(//span[@class='custom-control-indicator'])[3]")
	public WebElement saveForAllLineBtn;

	@FindBy(xpath = "(//span[@class='custom-control-description'])[4]")
	public WebElement withSimForCarBtn;

	@FindBy(xpath = "(//span[@class='custom-control-description'])[5]")
	public WebElement withOutSimForCarBtn;

	@FindBy(xpath = "(//span[@class='custom-control-indicator'])[6]")
	public WebElement saveForAllLine2Btn;

	@FindBy(xpath = "//div[@class='btn-red']")
	public WebElement approveBtn;

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

	@FindBy(xpath = "//div[text()='כרטיס אשראי']")
	public WebElement creditCardBtn;

	@FindBy(id = "userData2")
	public WebElement cardNameHolderText;

	@FindBy(id = "cardNumber")
	public WebElement creditCardNumberText;

	@FindBy(id = "expMonth")
	public WebElement cardMonthExpired;

	@FindBy(id = "expYear")
	public WebElement cardYearExpired;

	@FindBy(id = "cvv")
	public WebElement cvvCardText;

	@FindBy(id = "personalId")
	public WebElement IdNumberText;

	@FindBy(id = "submitBtn")
	public WebElement approveCardBtn;

	@FindBy(xpath = "//div[@class='your-line ng-star-inserted']")
	public WebElement mobileNumberText;

	@FindBy(xpath = "(//div[@class='line-price']//span)[2]")
	public WebElement priceLine;

	@FindBy(xpath = "//div[@class='container ng-star-inserted']//*[contains(@class,'congratulations')]")
	public WebElement infoMessage;

	public void newNumber() {

		click(newNumberBtn, "new number");

	}

	public void keepNumber() {

		click(keepNumberBtn, "keep number");

	}

	public void kosherline() {

		click(kosherlineBtn, "kosher line");

	}

	public void suggestNumber() {

		click(suggestNumberBtn, "suggest number button");
	}

	public void check() {

		wait.until(ExpectedConditions.visibilityOf(checkBtn));
		click(checkBtn, "check button");
	}

	public void approve() throws InterruptedException {

		Thread.sleep(300);
		click(approveBtn, "approve button");
	}

	public void fakerClick(String personalId, String email) throws InterruptedException {

		click(fakeBtn, "faker form");
		IdText.clear();
		emailText.clear();
		Thread.sleep(2000);
		scrollByVisibilityOfElement(driver, simRegularPostBtn);
		type(IdText, personalId, "Id number");
		
		type(emailText, email, "Email account");

	}

	public void getSimAndinvoices() throws InterruptedException {

		scrollByVisibilityOfElement(driver, simRegularPostBtn);
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", simRegularPostBtn);
		Thread.sleep(1000);

		click(simRegularPostBtn, "Order Sim Regular Post");
		click(invoicesMail, "Get Invoices By Mail");

	}

	public void continueB() {

		click(continueBtn, "Continue button");
	}

	public void enterCreditCardInforamtion(String cardNameHolder, String creditCardNumber, String expMonth,
			String expYear, String cvv, String personalId) throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOf(creditCardBtn));

//		findFrameNumber(driver, id = "userData2");
		mobileNumber = mobileNumberText.getText();
		lastFourDigitCreditCard = StringUtils.right(creditCardNumber, 4);

		driver.switchTo().frame(driver.findElement(By.className("payment-iframe")));
		type(cardNameHolderText, cardNameHolder, "Card Name Holder ");
		type(creditCardNumberText, creditCardNumber, "Credit Card Number");
		type(cvvCardText, cvv, "CVV Card Text");
		type(IdNumberText, personalId, "Id Number Text");

		selectByValue(cardMonthExpired, expMonth, "Card Month Expired");
		int year = Integer.parseInt(expYear) - 2000;
		
		selectByValue(cardYearExpired,(StringUtils.right(expYear, 2)), "Card Year Expired");
		click(approveCardBtn, "Approve Card button");

	}

	public void validateConfirmMessage() throws InterruptedException {

		golanWindowHandle = driver.getWindowHandle();
//		Thread.sleep(5000);
		System.out.println("test");
//		infoMessage.getText();
//		System.out.println(infoMessage.getText());
//		System.out.println(infoMessage.getText());
//		String expectedMsg = "ברכות על הצטרפותך לגולן טלקום!";
//		String actualMessage = infoMessage.getText();
		
	}

}
