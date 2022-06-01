package CRMPageObjects;

import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GolanPageObjects.BasePage;

public class CrmRightPanelPage extends BasePage<Object> {

	WebDriverWait wait = new WebDriverWait(driver, 10);

	public CrmRightPanelPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(searchText);

	}

	@FindBy(id = "header_search_input")
	public WebElement searchText;

	@FindBy(xpath = "//table[@class='table table-search-results table-bordered']/tbody/tr[1]/td[4]")
	public WebElement firstOption;

	@FindBy(xpath = "//a[@tab='3']")
	public WebElement orderTab;

	@FindBy(xpath = "//a[contains(text(),'פורטל')]")
	public WebElement portalBtn;

	@FindBy(xpath = "//i[@class='icon-barcode']")
	public WebElement logisticBtn;

	@FindBy(xpath = "//a[@data-parent='#accordion8']")
	public WebElement invoicesBtn;

	@FindBy(xpath = "//a[@data-parent='#accordion4']")
	public WebElement martamBtn;

	@FindBy(xpath = "//a[contains(text(),'מספרים וירטואליים')]")
	public WebElement virtualBtn;

	@FindBy(xpath = "//a[contains(text(),'קניית חבילת חול')]")
	public WebElement roamingPackageBtn;

	public CrmAccountPage search() {

		searchText.clear();
		type(searchText, mobileNumber, "Mobile Number");
		searchText.sendKeys(Keys.ENTER);
		
		if (isDisplayed(driver, firstOption)) {
		click(firstOption, "Logistic Button");
		}
	
		return new CrmAccountPage(driver);

	}
	
	public CrmPortalPage enterPortal() {

		click(portalBtn, "Portal Button");

		return new CrmPortalPage(driver);

	}

	public void openLogistic() {

		click(logisticBtn, "Logistic Button");

	}

	public void openInvoices() {

		click(invoicesBtn, "Invoices Button");

	}

	public void openMartam() {

		click(martamBtn, "Martam Button");

	}

	public void openVirtual() {

		click(virtualBtn, "Virtual Numbers Button");

	}

	public void openRoamingPackageBtn() {

		click(roamingPackageBtn, "Roaming Package Button");

	}



}
