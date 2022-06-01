package CRMPageObjects;

import java.sql.SQLException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import GolanPageObjects.BasePage;

public class CrmAccountPage extends BasePage<Object> {

	public CrmAccountPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition<?> getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(accountIdText);

	}

	@FindBy(xpath = "//table[@class='account_tabs']/tbody/tr[2]/td/div[2]/div[1]/div[4]/table/tbody/tr[2]/td[2]")
	public WebElement accountIdText;

	@FindBy(xpath = "//span[@class='ltr']")
	public WebElement personalIdCrm;

	@FindBy(xpath = "//button[@id='action_num_147']")
	public WebElement historyPaymentBtn;

	@FindBy(xpath = "//table[@id='historyPmTable']/tbody/tr[2]/td[4]")
	public WebElement lastFourDigitCrm;

	@FindBy(xpath = "//tr[@class='row-with-ndc_sn-in_use']//button[@id='found_account_submit']")
	public WebElement showBTN;

	@FindBy(xpath = "//a[@tab='2']")
	public WebElement linesTab;

	@FindBy(xpath = "//table[@class='account_tabs']/tbody/tr[2]/td/div[2]/div[1]/div[4]/table/tbody/tr[14]/td[2]")
	public WebElement imsiNumberText;

	@FindBy(xpath = "//a[@tab='3']")
	public WebElement orderTab;

	@FindBy(xpath = "//i[@class='icon-barcode icon-barcode']")
	public WebElement orderBarcode;

	@FindBy(id = "barcodes")
	public WebElement barcodeText;
//	
	@FindBy(className = "alert-content")
	public WebElement errorPairedSim;

	@FindBy(xpath = "//table[@class='crm_table']/tbody/tr[2]/td[1]")
	public WebElement itemNumberText;

	@FindBy(xpath = "//button[@id='action_num_253']")
	public WebElement smsBtn;

	@FindBy(xpath = "//td[@class='sms_content']")
	public WebElement smsContent;

	@FindBy(xpath = "//td[@class='edit_order_button']")
	public WebElement orderNumber;

	@FindBy(xpath = "//table[@class='account_tabs_tab_3_table account_tabs_tab_table table_orders view_order']/tbody/tr[2]/td[8]")
	public WebElement simActiveCodeText;

	@FindBy(xpath = "//td[@class='account_tabs_content']/div[2]/table/tbody/tr[2]/td[1]")
	public WebElement lineNumberLink;

	@FindBy(xpath = "//div[@class='tab_line_title search-results-subscriber-status-in_use']")
	public WebElement lineNumberText;

	public void searchBySubscriber(String accountNumber) {

		click(orderTab, "Order Tab");
		click(orderBarcode, "Order Barcode");
	}

	public void enterSimNumber() {

		click(orderTab, "Order Tab");
		click(orderBarcode, "Order Barcode");
	}

	public boolean registerAccountWithSim(String simNumber) throws SQLException {

		type(barcodeText, simNumber + ";itm" + itemNumberText.getText(), "Id Number Text");
//		if (errorPairedSim.getText().contains("שגיאה בשיווך")) {
//			testCases.DbManager.getSimNumberFromDB();
//		}
		barcodeText.sendKeys(Keys.ENTER);
		barcodeText.sendKeys(Keys.CONTROL, Keys.TAB);
//		CRMWindowHandle = driver.getWindowHandle();

		if (errorPairedSim.getText().contains("שגיאה בשיווך")) {

			return false;
		}
		barcodeText.sendKeys(Keys.CONTROL + "\t");
		return true;

	}

	public void getLastSmsNumber() {

		fluentWait(driver, smsBtn, 10);
//		click(smsBtn, "SMS button");
		fluentWait(driver, smsContent, 10);
		lastSmsNumber = (String) deleteAllnoDigitNumber(smsContent.getText());

		System.out.println("Last SMS Number Is : " + lastSmsNumber);
	}

	public void getActiveSimCode() {

		fluentWait(driver, orderTab, 10);
		click(orderNumber, "Order Number");
		simActiveCode = (String) deleteAllnoDigitNumber(simActiveCodeText.getText());
		System.out.println("Sim Activate code is :" + simActiveCode);

	}

	public void getLineInformation() {

		fluentWait(driver, linesTab, 10);
		personalId = personalIdCrm.getText();
		System.out.println(" The ID from CRM is : " + personalId);
		
		click(historyPaymentBtn, "History Payment");
		lastFourDigitCreditCard = lastFourDigitCrm.getText().substring(0,4);
		System.out.println(" The Last Four Digit Credit from CRM is : " + lastFourDigitCreditCard);

		


		click(linesTab, "Order Number");
		click(lineNumberLink, "Line Number Link");
		imsiNumber = imsiNumberText.getText();
		mobileNumber = (String) deleteAllnoDigitNumber(lineNumberText.getText());

		System.out.println(" The imsi is : " + imsiNumber);
		System.out.println(" The Line number is : " + mobileNumber);

	}

}
