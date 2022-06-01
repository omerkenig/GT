package GolanPageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilties.DriverManager;

import org.openqa.selenium.WebElement;

public class GolanHomePage extends BasePage {

	public GolanHomePage open(String url) {

		DriverManager.getDriver().navigate().to(url);
		return (GolanHomePage) openPage(GolanHomePage.class);
	}
	@FindBy(xpath = "//div[@class='top-header only-desktop']//div[@id='block-golan-branding']//a")
	public WebElement goloanLogo;

	@FindBy(xpath = "(//a[@title='להצטרפות'][contains(text(),'להצטרפות')])[1]")
	public WebElement firstPackage;

	public GolanUserGuidePage chooseFirstPackage() {

		click(firstPackage, "Click on the first package");
		golanWindowHandle = driver.getWindowHandle();
		return new GolanUserGuidePage(driver);

	}

	public boolean validateLogoExist() throws Throwable {
		return isDisplayed(driver, goloanLogo);
	}


	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(firstPackage);
	}
}
