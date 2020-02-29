package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ListAccountPage {
	
	WebDriver driver;

	public ListAccountPage(WebDriver driver) {
		this.driver = driver;
	}
	//Notes:
	//Xpath for the table header: //table[@class='table table-striped table-bordered']/tbody/tr[1]/th
	
	@FindBy(how = How.ID, using = "username")
	WebElement UserName;

	//INTERVIEW QUESTION:
	//How do you get data out of a Dynamic Web table?
	//The table may or may not stay the same. The columns may not stay the same.
	//The column header may or may not stay at the same location.
	//So getting the absolute relative Xpath and not worrying about the dynamic part of that table is not a good idea.
	//Therefore, I always implement the dynamic web table in my test cases.
	//The way I get the dynamic web table, first of all, I need a method to get the index out of the table for 
	//table header i.e. the column that I need. If I need a column data, I get that index method, that gives me
	//exactly which index that I am looking for. 
	//Inside that method is a for loop, which loops around all the headers and finds which header it is on, 
	//and finds the header that I want and gives me the exact index of that.
	//Given that index, then I go into the table with another method and get a list of the data on the table.
	//Once I get that list, I take it to my test class and do the logic there of comparing 
	//it with the data that I want/or looking for.
	
	//List<WebElement> columnDataElements is collecting all the web elements from the table. We will do
	//.getText() to these elements, which will return the text values and we will store them in another list
	//which is of String datatype. Because it is the list that means collection of multiple values, we will store
	//them in ArrayList of String datatype, which is, List<String> columnData = new ArrayList<String>().
	//We will also have to do the for loop so that we can collect data from all the rows.
	//List is like a rubber band, it expands depending upon the size of the data.
	
	public List<String> getColumnDataFor(String columnHeader) {
		List<String> columnData = new ArrayList<String>();	
		int index = getColumnHeaderIndexFor(columnHeader);	
		String xpath = "//table[@class='table table-striped table-bordered']/tbody/tr/td["+index+"]";//Dynamic Xpath
		List<WebElement> columnDataElements = driver.findElements(By.xpath(xpath));
		
		for(int i=0; i<columnDataElements.size(); i++) {
			columnData.add(columnDataElements.get(i).getText());		
		}
		return columnData;
	}

	private int getColumnHeaderIndexFor(String columnHeader) {
		String xpath = "//table[@class='table table-striped table-bordered']/tbody/tr[1]/th";
		List<WebElement> columnHeaderElements = driver.findElements(By.xpath(xpath));
		int index = 1;
		for (WebElement element : columnHeaderElements ) {
			if (element.getText().equalsIgnoreCase(columnHeader)) {
				return index;	
			}
			index++;
		}
		return 0;
	}
}