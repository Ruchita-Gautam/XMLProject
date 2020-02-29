package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewAccountPage {
	
	WebDriver driver;

	public NewAccountPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Notes:
			//AccountTitle ID account
			//Description ID description
			//InitialBalance ID balance
			//SubmitButton Xpath //label[text()='Initial Balance']/../following-sibling::button

	@FindBy(how = How.ID, using = "account")
	WebElement AccountTitleField;

	@FindBy(how = How.ID, using = "description")
	WebElement DescriptionField;
	
	@FindBy(how = How.ID, using = "balance")
	WebElement InitialBalanceField;
	
	@FindBy(how = How.XPATH, using = "//label[text()='Initial Balance']/../following-sibling::button")
	WebElement SubmitButton;
	
	public void fillInTheNewAccountForm(String accountTitle, String description, String balance) throws InterruptedException {
		AccountTitleField.sendKeys(accountTitle);
		DescriptionField.sendKeys(description);
		InitialBalanceField.sendKeys(balance);
		Thread.sleep(2000);
		SubmitButton.click();	
	}	
}