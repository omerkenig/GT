package CRMPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import GolanPageObjects.BasePage;

public class CrmPortalPage extends BasePage<Object> {

	public CrmPortalPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(accountIdText);

	}
	
	@FindBy(id = "account_id")
	public WebElement accountIdText;

	@FindBy(id = "subscriber")
	public WebElement subscriberText;

	@FindBy(id = "subscriber_id")
	public WebElement subscriberidText;
	
	@FindBy(id = "id_number")
	public WebElement idNumberText;
	
	@FindBy(id = "order_number")
	public WebElement orderNumberText;
	
	@FindBy(id = "pm4_cg")
	public WebElement lastFourDigitCardText;
	
	@FindBy(id = "specific_search")
	public WebElement searchBtn;
	
	//serach By last four digit 
	
	@FindBy(xpath = "//table[@class='table table-search-results table-bordered']/tbody/tr[1]/td[4]")
	public WebElement firstShowButton;
	
	public void searchBySubscriber() throws InterruptedException {

		type(subscriberText, mobileNumber, "Mobile Number");
		click(searchBtn, "Search Button");		
		
	}
	
	public CrmAccountPage searchByLastFourDigitCard() throws InterruptedException {

		
		type(lastFourDigitCardText, getRandomNumberString(), "Last Four Digit Card");
		click(searchBtn, "first Show Button");	
		click(firstShowButton, "first Show Button");	

		
		return new CrmAccountPage(driver);
		
	}
	
	
	

	 

}
