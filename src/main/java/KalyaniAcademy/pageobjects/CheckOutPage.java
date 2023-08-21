package KalyaniAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KalyaniAcedemy.AbstractComponent.AbstractComponent;



public class CheckOutPage extends AbstractComponent{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectcountry;
	
	@FindBy(css="a.action__submit")
	WebElement submit;
	
By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String CountryName) throws InterruptedException {
		Actions a=new Actions(driver);
	    a.sendKeys(country,CountryName).build().perform();
	    waitforElementtoApper(results);	   
	    selectcountry.click();
	}

	public ConfirmationPage submitOrder() throws InterruptedException {
		 Thread.sleep(3000);
	    JavascriptExecutor js=(JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(1085, 651)");
	    submit.click();
	    ConfirmationPage c=new ConfirmationPage(driver);
	    return c;
	}
	
}