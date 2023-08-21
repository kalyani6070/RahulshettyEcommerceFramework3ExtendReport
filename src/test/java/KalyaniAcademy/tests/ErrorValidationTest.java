package KalyaniAcademy.tests;

import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Test;

import KalyaniAcademy.TestComponents.BaseTest;
import KalyaniAcademy.TestComponents.Retry;
import KalyaniAcademy.pageobjects.CartPage;
import KalyaniAcademy.pageobjects.CheckOutPage;
import KalyaniAcademy.pageobjects.ConfirmationPage;
import KalyaniAcademy.pageobjects.LandingPage;
import KalyaniAcademy.pageobjects.ProductCatelog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest{

	//keep one test bez of socket issue
//	@Test
//	public void LoginErrorValidation () throws IOException, InterruptedException {
//		
//			ProductCatelog pc=lp.loginApplication("supekar.kalyani@gamial.com", "Kalyanii6070@");
//			Assert.assertEquals("Incorrect email or password", lp.getErrorMessage());
//			}

	@Test(retryAnalyzer=Retry.class)
	public void producterrorValidation() throws InterruptedException {
		String prodname= "ZARA COAT 3";
		ProductCatelog pc=lp.loginApplication("kalyanisupekar0511@gmail.com", "Kalyani6070@");
		List<WebElement>products=pc.getProductList();	
		pc.addProducttoCart(prodname);			
	    Thread.sleep(3000);	    
	    CartPage cp=pc.gotoCartPage();		
	Boolean match=cp.ProductofCart("ZARA COAT 3");
	Assert.assertFalse(match);
	}
}
