package GolanPageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GolanJoinUsPaymentPage extends BasePage {
	
	WebDriverWait wait = new WebDriverWait(driver, 20);

	public GolanJoinUsPaymentPage(WebDriver driver) {
		


		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(creditCardBtn);
	}

	@FindBy(xpath = "(//div[@class='ng-star-inserted'])[1]")
	public WebElement creditCardBtn;

	@FindBy(xpath = "(//div[@class='ng-star-inserted'])[2]")
	public WebElement DirectDebitBtn;

	@FindBy(xpath = "//div[@class='join-flow-steps-content']/app-payments/div/div[1]/div/lib-rc-radios/div/div//div[normalize-space()='Pay Pal']")
	public WebElement payPalBtn;
	
	@FindBy(xpath = "(//div[@class='ng-star-inserted'])[4")
	public WebElement foreignCreditCardBtn;
	
	//Credit Card Information
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
	
	//Paypal Information
	@FindBy(id = "email")
	public WebElement paypalEmailText;
	
	@FindBy(id = "btnNext")
	public WebElement paypalNextBtn;
	
	@FindBy(id = "password")
	public WebElement paypalPasswordText;
	
	@FindBy(xpath = "//label[@for='keepMeLoggedIn']")
	public WebElement paypalkeepMeLoggedCB;
	
	@FindBy(id = "confirmButtonTop")
	public WebElement paypalPayNowBtn;
	
	@FindBy(id = "btnLogin")
	public WebElement paypalLoginBtn;
	
	@FindBy(id = "confirmButtonTop")
	public WebElement paypalConfirmBtn;
	
	
	

	@FindBy(id = "submitBtn")
	public WebElement approveCardBtn;
	
	@FindBy(xpath = "//div[@class='your-line ng-star-inserted']")
	public WebElement mobileNumberText;


	public void enterCreditCardInforamtion(String cardNameHolder, String creditCardNumber, String expMonth, String expYear,
			String cvv, String personalId) throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(creditCardBtn));
		Thread.sleep(2000);
		mobileNumber = mobileNumberText.getText();
		lastFourDigitCreditCard = StringUtils.right(creditCardNumber, 4);
		
		System.out.println(mobileNumber);
		System.out.println(lastFourDigitCreditCard);

		driver.switchTo().frame(driver.findElement(By.className("payment-iframe")));
		// int number = findFrameNumber(driver,By.xpath(OR.getProperty("FlightFrom")));
		// System.out.println(number);
		// driver.switchTo().frame(number);
		
		type(cardNameHolderText, cardNameHolder, "Card Name Holder");
		type(creditCardNumberText, creditCardNumber, "Credit Card Number");
		
		selectByValue(cardMonthExpired, expMonth, "Card Month Expired");
		int year = Integer.parseInt(expYear) - 2000;
		selectByValue(cardYearExpired,(StringUtils.right(expYear, 2)), "Card Year Expired");
		
		type(cvvCardText, cvv, "CVV Number");
		type(IdNumberText, personalId, "ID Number");

		click(approveCardBtn, "Approve Card Button");
		
	}
	
	public void enterPaypalInforamtion(String mail, String password) throws InterruptedException {
		
		wait.until(ExpectedConditions.visibilityOf(creditCardBtn));
		mobileNumber = mobileNumberText.getText();
		System.out.println(mobileNumber);
		
		fluentWait(driver, payPalBtn, 10);
		click(payPalBtn, "payPal Button");

		type(paypalEmailText, mail, "Paypal Email");
		click(paypalNextBtn, "payPal Next Button");

		type(paypalPasswordText, password, "Paypal Password");
		
		click(paypalkeepMeLoggedCB, "Paypal Keep Logged CheckBox");
		click(paypalPayNowBtn, "Paypal Keep Logged CheckBox");

		click(paypalLoginBtn, "Paypal Login");
		click(paypalConfirmBtn, "Paypal Login");

		

		


		
		
		
		
	}
	

}
