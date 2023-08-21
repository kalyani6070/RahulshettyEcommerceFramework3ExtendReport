package KalyaniAcademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import KalyaniAcademy.TestComponents.BaseTest;
import KalyaniAcademy.pageobjects.CartPage;
import KalyaniAcademy.pageobjects.CheckOutPage;
import KalyaniAcademy.pageobjects.ConfirmationPage;
import KalyaniAcademy.pageobjects.LandingPage;
import KalyaniAcademy.pageobjects.OrderPage;
import KalyaniAcademy.pageobjects.ProductCatelog;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest{
	String prodname= "ZARA COAT 3";
	@Test()
	public void SubmitOrder() throws IOException, InterruptedException {
		
			ProductCatelog pc=lp.loginApplication("supekar.kalyani@gmail.com", "Kalyani6070@");
		
		List<WebElement>products=pc.getProductList();
		pc.addProducttoCart(prodname);
		CartPage cp=pc.gotoCartPage();			
	Thread.sleep(3000);
	

	boolean match=cp.ProductofCart(prodname);
	Assert.assertTrue(match);
	CheckOutPage co=cp.gotoCheckout();
	co.selectCountry("india");
	ConfirmationPage confirmpage=co.submitOrder();   
    String confirmationMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
    Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	
	//this does not work for depndson, dataprovider and for multiptest because of socket issue so keep one this
//	@Test(dependsOnMethods= {"SubmitOrder"})
//	public void OrderHistory() {
//		
//		ProductCatelog pc=lp.loginApplication("supekar.kalyani@gmail.com", "Kalyani6070@");	
//	
//		OrderPage orderpage=pc.goToOrdersPage(); 
//		Assert.assertTrue(orderpage.VerifyOrderDisplay(prodname));
//	}
	
	//I used this below code to test for groups
//	@Test(groups={"ErrorHandling"})
//	public void OrderHistory() {
//		
//		ProductCatelog pc=lp.loginApplication("supekar.kalyani@gmail.com", "Kalyani6070@");	
//	
//		OrderPage orderpage=pc.goToOrdersPage(); 
//		Assert.assertTrue(orderpage.VerifyOrderDisplay(prodname));
//	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"supekar.kalyani@gmail.com","Kalyani6070@"},
//				{"kalyanisupekar0511@gmail.com","Kalyani6070@"}};
//	}
	

}
