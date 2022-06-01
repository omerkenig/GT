package GolanPageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GolanUserGuidePage extends BasePage {

	public GolanUserGuidePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(xpath = "//button[contains(text(),'כבר מנוי של גולן טלקום')]")
	public WebElement existSubscribe;

	@FindBy(xpath = "//button[contains(text(),'אין לי מנוי ורוצה להצטרף')]")
	public WebElement newSubscribe;

	public void alreadySubscriber() {

		click(existSubscribe, "Click on the exist subscribe");

	}

	public GolanJoinUsFlowPage wantToJoin() {

		click(newSubscribe, "Click on the new subscribe");
		return new GolanJoinUsFlowPage(driver);

	}
	
	
	
	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(existSubscribe);
	}

}
