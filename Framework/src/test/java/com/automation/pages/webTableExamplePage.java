package com.automation.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class webTableExamplePage {
	WebDriver driver;
	List<String> Header = new ArrayList<String>(); 
	
	
	@FindBy(xpath = "//table//tr")
	private List<WebElement> totalRow;

	@FindBy(xpath = "//table//th")
	private List<WebElement> totalColumn;


	public webTableExamplePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public int totalRowFun() {
		return totalRow.size() - 1;
	}

	public int totalColumnFun() {
		return totalColumn.size();
	}
	
	public void addAllHeadersInAList() {
		Header.clear();
		for(WebElement e : totalColumn) {
			Header.add(e.getText());
		}
	}
	
	public HashMap<String, List<String>> getCellElement() {
		if (Header.isEmpty()) {
            addAllHeadersInAList();
        }
		HashMap<String, List<String>> tableContent = new HashMap<String, List<String>>();
		for(int i=0 ; i<totalColumnFun() ; i++ ) {
			List<String> ColumnList = new ArrayList<String>();
			for(int j =1;j<=totalRowFun();j++) {
				String dynamicXPath = "//table//tr["+(j+1)+"]//td["+(i+1)+"]";
				WebElement cell = driver.findElement(By.xpath(dynamicXPath));
						ColumnList.add(cell.getText());
			}
			tableContent.put(Header.get(i), ColumnList);
			
		}
		  return tableContent;
	}
}
