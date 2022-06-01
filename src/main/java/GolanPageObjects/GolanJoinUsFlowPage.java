package GolanPageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GolanJoinUsFlowPage extends BasePage {
	
	WebDriverWait wait = new WebDriverWait(driver, 10);

	public GolanJoinUsFlowPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

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

	public void addNewLineNumber() {

		click(newNumberBtn, "new number");
		click(suggestNumberBtn, "Suggest Number");
		click(checkBtn, "Check Button");

	}

	public void noPublishOn144() throws InterruptedException  {
		
		Thread.sleep(1500);
		fluentWait(driver, notPublish144Btn, 10);
//		Thread.sleep(2000);

//		click(notPublish144Btn, "Check Button");

	}

	public GolanJoinUsAccountPage approve() throws InterruptedException {

//		Thread.sleep(2000);
		fluentWait(driver, approveBtn, 10);
//		click(approveBtn, "Check Button");

		return new GolanJoinUsAccountPage(driver);
	}

}
