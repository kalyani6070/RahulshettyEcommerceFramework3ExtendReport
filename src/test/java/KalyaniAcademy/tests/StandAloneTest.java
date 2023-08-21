package KalyaniAcademy.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		String prodname= "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("supekar.kalyani@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Kalyani6070@");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(prodname)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	Thread.sleep(3000);
	driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();
	List<WebElement> cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));
	boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(prodname));
	Assert.assertTrue(match);
	
    driver.findElement(By.cssSelector(".totalRow button")).click();
    
    //I can do below steps by sendkeys but he try to show we can add also using actions class
    Actions a=new Actions(driver);
    a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
    //He wait for block of dropdown opens
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
    //Now click on country
    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
   
    //Click on place order
    Thread.sleep(3000);////div/a[text()='Place Order ']
    JavascriptExecutor js=(JavascriptExecutor)driver;
    js.executeScript("window.scrollBy(1085, 651)");
    driver.findElement(By.cssSelector("a.action__submit")).click();
    
    //Confirm if order is placed
    String confirmmsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
    Assert.assertTrue(confirmmsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

}
