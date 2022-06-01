package CRMPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import GolanPageObjects.BasePage;
import GolanPageObjects.GolanJoinUsPaymentPage;

public class CrmLoginlPage extends BasePage<Object> {

	public CrmLoginlPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(usernameText);

	}
	
	@FindBy(xpath = "//div[@id='loginFormBody']//input[@placeholder='שם משתמש']")
	public WebElement usernameText;

	@FindBy(xpath = "//input[@id='txtPassword']")
	public WebElement passwordText;

	@FindBy(xpath = "//div[@id='loginFormBody']//button[@role='button'][contains(text(),'אישור')]")
	public WebElement approveBtn;
	
	public CrmPortalPage loginToCRM(String username, String password) {

		CRMWindowHandle = driver.getWindowHandle();

		type(usernameText, username, "Username");
		type(passwordText, password, "Password ");
		click(approveBtn, "Approve button");
		
		return new CrmPortalPage(driver);

	}

	 

}
