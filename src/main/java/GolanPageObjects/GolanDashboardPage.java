package GolanPageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Scanner;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GolanDashboardPage extends BasePage {

	Actions actions = new Actions(driver);

	WebDriverWait wait = new WebDriverWait(driver, LOAD_TIMEOUT);
	List<WebElement> listLineToAdd = driver.findElements(By.xpath("//*[@class='sequence-number-wrapper']/div[2]"));


	public GolanDashboardPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {

		return ExpectedConditions.visibilityOf(titleText);
	}

	@FindBy(xpath = "//h2[contains(text(),'שימושים עבור ')]")
	public WebElement titleText;

	@FindBy(xpath = "(//a[contains(@class,'btn btn-primary')])[2]")
	public WebElement activeSimBtn;

	@FindBy(xpath = "//div[@class='dashboard-wrapper']/div[2]/app-sim-activation/div/app-component-wrapper/div/div/div[2]/div/div")
	public WebElement statusSim;

	@FindBy(xpath = "(//a[@class='gt-notification-link gt-notification-info-btn'])[1]")
	public WebElement updatePersonalInfoLnk;

	@FindBy(xpath = "(//a[@class='gt-notification-link gt-notification-info-btn'])[2]")
	public WebElement activeSimLnk;

	@FindBy(xpath = "//button[@id='invoicesButton']//img[1]")
	public WebElement invoicesLookBtn;

	@FindBy(xpath = "//button[@id='documentsButton']//img[1]")
	public WebElement docsLookBtn;

	@FindBy(xpath = "//button[@id='orderTracking']//img[1]")
	public WebElement orderTrackingBtn;

	@FindBy(xpath = "//button[@id='updatePaymentMethodButton']")
	public WebElement updatePaymentBtn;

	@FindBy(xpath = "//button[@id='updatePersonalInfoButton']")
	public WebElement updatePersonalInfoBtn;

	@FindBy(xpath = "//div[@id='component-wrapper']//div[@class='container']/div[2]/div[2]/div/div/button[@class='btn red-btn']")
	public WebElement activeBtn;

	@FindBy(xpath = "//div[@id='component-wrapper']//div[@class='container']/div[2]/div[2]/div/lib-rc-input/div/div/input[@id='simActivationControl']")
	public WebElement simActivationText;

	@FindBy(xpath = "(//div[@id='component-wrapper']//div[@class='container']/div[2]/div[2]/div/div//button[@class='btn red-btn'])[2]")
	public WebElement approveSmsBtn;

	// add new lines
	@FindBy(xpath = "//*[@class='add-new-line']")
	public WebElement plusToAddMordeLines;

	@FindBy(xpath = "//div[@class='login-main-wrapper']/div[2]/div/div/lib-rc-input[1]/div/div/input")
	public WebElement personalIdText;

	@FindBy(xpath = "//div[@class='login-main-wrapper']/div[2]/div/div/lib-rc-input[2]/div/div/input")
	public WebElement lastFourDigitCreditCardText;

	@FindBy(xpath = "//button[@type='button']")
	public WebElement approvePayment;

	@FindBy(xpath = "//*[@class='add-new-line-wrapper']")
	public WebElement addNumberOfLineToAdd;

	@FindBy(xpath = "//button[@class='btn btn-red']")
	public WebElement contiuneBtn;

	@FindBy(xpath = "//div[@class='container']/div[2]/app-steps-wrapper/div/app-choose-number-program/div/div/app-choose-number/div[1]/div[2]/lib-rc-radios/div/div/label[1]")
	public WebElement chooseNewNumber;

	@FindBy(xpath = "//div[@class='container']/div[2]/app-steps-wrapper/div/app-choose-number-program/div/div/app-choose-number/div[1]/div[2]/lib-rc-radios/div/div/label[2]")
	public WebElement keepExistNumber;

	@FindBy(xpath = "//div[@class='container']/div[2]/app-steps-wrapper/div/app-choose-number-program/div/div/app-choose-number/div[1]/div[2]/lib-rc-radios/div/div/label[3]")
	public WebElement sequenceNumbers;

	@FindBy(xpath = "//div[@class='container']/div[2]/app-steps-wrapper/div/app-choose-number-program/div/div/app-choose-number/div[1]/div[3]/div/div[3]")
	public WebElement approveBtn;

	@FindBy(xpath = "//*[@id='choosing-package-slide-0']")
	public WebElement chooseProgram1;

	@FindBy(xpath = "//*[@id='choosing-package-slide-1']")
	public WebElement chooseProgram2;

	@FindBy(xpath = "//*[@id='choosing-package-slide-2']")
	public WebElement chooseProgram3;

	@FindBy(xpath = "(//*[@class='line-price']//span)[2]")
	public WebElement priceFirstLine;

	@FindBy(xpath = "//*[@id='choosing-package-slide-0']//span[@class='price']")
	public WebElement priceProgram1;

	@FindBy(xpath = "//*[@id='choosing-package-slide-1']//span[@class='price']")
	public WebElement priceProgram2;

	@FindBy(xpath = "//*[@id='choosing-package-slide-2']//span[@class='price']")
	public WebElement priceProgram3;

	@FindBy(xpath = "//div[@class='settings-wrapper']//span[@class='custom-control-description'][contains(text(),'כן')]")
	public WebElement promo144_Yes;

	@FindBy(xpath = "//div[@class='settings-wrapper']//span[@class='custom-control-description'][contains(text(),'לא')]")
	public WebElement promo144_No;

	@FindBy(xpath = "//div[@class='choose-program-wrapper']/div[1]/div[4]/div[5]/lib-rc-checkbox/div/label")
	public WebElement promo_Save;

//	@FindBy(xpath = "//div[@class='choose-program-wrapper']/div[1]/div[5]/div[2]/div/div[1]/lib-rc-radios/div/div/label[1]")
//	public WebElement newSim_Yes;
//
//	@FindBy(xpath = "//div[@class='choose-program-wrapper']/div[1]/div[5]/div[2]/div/div[1]/lib-rc-radios/div/div/label[2]")
//	public WebElement newSim_No;

	@FindBy(xpath = "//div[@class='choose-program-wrapper']/div[1]/div[5]/div[3]/div[1]/div[1]/lib-rc-radios/div/div/label[1]")
	public WebElement simForCar_Yes;

	@FindBy(xpath = "//div[@class='choose-program-wrapper']/div[1]/div[5]/div[3]/div[1]/div[1]/lib-rc-radios/div/div/label[2]")
	public WebElement simForCar_No;

	@FindBy(xpath = "//div[@class='choose-program-wrapper']/div[1]/div[5]/div[3]/div[2]/lib-rc-checkbox/div/label")
	public WebElement simCar_Save;

	@FindBy(xpath = "//div[@class='choose-program-wrapper']/div[1]/div[6]/button[contains(text(),'אישור')]")
	public WebElement approve_Save;

	@FindBy(xpath = "//*[@class='btn-red']")
	public WebElement approvePackage;

	@FindBy(xpath = "(//*[@class='number'])[1]")
	public WebElement monthPayment;

	List<WebElement> listLinesToAdd = driver.findElements(By.xpath("//*[@class='sequence-number-wrapper']/div[2]"));

	
	public void activeNewSim() throws InterruptedException {

		click(activeSimLnk, "Active Sim Button");
//		fluentWait(driver, activeBtn, 10);
		explicitWait(driver, activeBtn, 10);

		click(activeBtn, "Active Button");

	}

	public void enterActiveSimCode() throws InterruptedException {

		fluentWait(driver, activeBtn, 10);
		if (isDisplayed(driver, activeBtn)) {
			activeNewSim();

		}

		type(simActivationText, simActiveCode, "Sim Active Code");
		click(approveSmsBtn, "Approve Sms Button");

	}

	public String checkIfSimIsActivate() throws InterruptedException {

		Thread.sleep(2000);
		if (isDisplayed(driver, approveSmsBtn)) {

			click(approveSmsBtn, "Approve Sms Button");
		}
		Thread.sleep(10000);
		fluentWait(driver, activeSimBtn, 10);
		click(activeSimBtn, "Active Sms Button");
		
		textToCheck = statusSim.getText();
		System.out.println("The text is : " + textToCheck);
		return textToCheck;
	}

	public void addNewLines(String numberOfLines) throws InterruptedException {

		// click(addNewLine, "Add New Line");
		// Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(plusToAddMordeLines));
		click(plusToAddMordeLines, "Add New Line Payment");
//		explicitWait(driver, personalIdText, 10);
//		personalIdText.sendKeys("756575");
//		lastFourDigitCreditCardText.sendKeys("frfr");
		type(personalIdText, personalId, "Personal Id");
		type(lastFourDigitCreditCardText, lastFourDigitCreditCard, "Last Four Digit Credit Card");
		click(approvePayment, "Approve Payment");

		fluentWait(driver, addNumberOfLineToAdd, 10);
		driver.findElement(By.xpath("(//input[contains(@class,'rc-radios-radio-input custom-control-input')]"
				+ "/following-sibling::span)[" + numberOfLines + "]")).click();

		click(contiuneBtn, "Contiune");

	}

	public void chooseSequenceNumbers(String promoOn144, String SaveForAllLines144, String NewSim, String CarSim,
			String SaveForAllLinesSim) throws InterruptedException {

		explicitWait(driver, sequenceNumbers, 10);
		click(sequenceNumbers, "Sequence Numbers");
		Thread.sleep(2000);

		
		
		click(approveBtn, "Approve");
		choosePackage();

//		click(approveBtn, "Promo - Yes");

//		if (promoOn144.equalsIgnoreCase("Y")) {
//			click(promo144_Yes, "Promo - Yes");
//		} else {
//			click(promo144_No, "Promo - No");
//		}
//		if (SaveForAllLines144.equalsIgnoreCase("Y")) {
//			click(promo_Save, "Promo - Save");
//		}
////		if (isDisplayed(driver, newSim_No)) {
////			if (NewSim.toUpperCase() == "Y") {
////				click(newSim_Yes, "NewSim - Yes");
////			} else {
////				click(newSim_No, "NewSim - No");
////			}
	}

//
//		if (CarSim.equalsIgnoreCase("Y")) {
//			click(simForCar_Yes, "Sim For Car - Yes");
//		} else {
//			click(simForCar_No, "Sim For Car  - No");
//		}
//		if (SaveForAllLinesSim.equalsIgnoreCase("Y")) {
//			click(simCar_Save, "Sim Car Save");
//		}
//		click(approve_Save, "Sim Car Save");
//	}
//
	public void choosePackage() throws InterruptedException {
		
		Thread.sleep(1500);
		int pack = rand.nextInt(3) + 1;
		switch (pack) {
		case 1:
			click(chooseProgram1, "Choose Program 1");
			totalCost += Integer.parseInt(priceProgram1.getText());
			break;
		case 2:
			click(chooseProgram2, "Choose Program 2");
			totalCost += Integer.parseInt(priceProgram2.getText());
			break;
		case 3:
			click(chooseProgram3, "Choose Program 3");
			totalCost += Integer.parseInt(priceProgram3.getText());
			break;
		}
		scrollByVisibilityOfElement(driver, approvePackage);
		fluentWait(driver, approvePackage, 10);
//		click(approvePackage, "Approve Package " + pack);

	}

	public boolean checkPayment() {

		String payment = monthPayment.getText();
		String payment1 = payment.substring(0, payment.indexOf('.'));
//		payment1 = payment1.trim();
//		int payment2 = Integer.parseInt(payment1);
//		payment1 = Integer.parseInt(payment1);

		if (totalCost == Integer.parseInt(payment1.trim())) {
			return true;
		} else {
			return false;
		}

	}

}
