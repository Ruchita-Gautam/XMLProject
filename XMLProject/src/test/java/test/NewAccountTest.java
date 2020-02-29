package test;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import page.ListAccountPage;
import page.LoginPage;
import page.NewAccountPage;
import page.SideNavigation;
import util.BrowserFactory;


public class NewAccountTest {
	
	//Global Variables
	
	
	String username = "techfiosdemo@gmail.com";
	String password = "abc123";
	String accountTitle = "SavingsAccount";
	String description = "Savings";
	String balance = "50000";

	//Test case: Open a new account
	@Test 
	@Parameters({"username","password","accountTitle","description","balance"})
	public void OpenANewAccount (String username, String password,String accountTitle, String description, String balance ) throws InterruptedException {
		WebDriver driver = BrowserFactory.startBrowser();	
		
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.login(username, password);
		
		SideNavigation sideNav = PageFactory.initElements(driver, SideNavigation.class);
		sideNav.goToNewAccountPage();	
		
		Random rnd = new Random();
		String expectedTitle = accountTitle + rnd.nextInt(9999);
		System.out.println(expectedTitle);
		
		NewAccountPage newAccountPage = PageFactory.initElements(driver, NewAccountPage.class);
		newAccountPage.fillInTheNewAccountForm(expectedTitle,description,balance);
		
		//Get the list of column data from the page class
		ListAccountPage listAccountPage = PageFactory.initElements(driver, ListAccountPage.class);
		List<String> columnData = listAccountPage.getColumnDataFor ("Amount");
	
	//	Assert.assertTrue(isDataPresent(expectedTitle,columnData),"New Account did not post");
	}
	
private boolean isDataPresent(String expectedTitle, List<String> columnData) {
	for(String cellData:columnData) {
		if(cellData.equalsIgnoreCase(expectedTitle)) {
			System.out.println(cellData);
			return true;
		}
	}
	return false;
}
}
