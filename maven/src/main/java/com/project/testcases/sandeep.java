package com.project.testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class sandeep {
	WebDriver driver;
	@BeforeMethod
	public void LaunchBrowser() throws IOException{
     	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
     	driver = new ChromeDriver(); //launching the browser
     	driver.get("https://www.calculator.net/calorie-calculator.html");
     	//driver.get(PropertyReader.ReadProperty("appurl"));
     	driver.manage().window().maximize(); //maximize 		
	}
	
	@AfterMethod
	public void CloseBrowser(){
		if(driver!=null){
			driver.close(); //the current active browser
			driver.quit(); ///closes all open browsers opened via yout script
		}
	}
	
	@Test(dataProvider="getCalorieData")
	public void EnterCalorieDetails(String age, String gender) throws InterruptedException{
     	WebElement ageElement = driver.findElement(By.name("cage"));
     	System.out.println("Get the default value of age Textbox : " + ageElement.getAttribute("value"));
     	ageElement.clear();
     	ageElement.sendKeys(age); //enter 45 
     	List<WebElement> sexList = driver.findElements(By.name("csex"));
     	for(WebElement sex : sexList ){
     		if(sex.getAttribute("value").equals(gender)){
     			if(!sex.isSelected()){
     				sex.click();
     				break;
     			}     			
     		}
     	}
     	
     	Thread.sleep(5000);

	}
    @DataProvider
	public Object[][] getCalorieData(){
		Object[][] data = new Object[2][2];
		data[0][0] = "45";
		data[0][1] ="f";
		data[1][0] ="56";
		data[1][1] = "m";
		return data;
	}

}
