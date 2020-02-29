package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.ID, using = "username")
	WebElement UserName;
	@FindBy(how = How.NAME, using = "password")
	WebElement Password;
	@FindBy(how = How.NAME, using = "login")
	WebElement SignInButton;
	
	public void login(String username, String password) {
		UserName.sendKeys(username);
		Password.sendKeys(password);
		SignInButton.click();
	}
	public String getTheTitle() {
		return driver.getTitle();
	}
}