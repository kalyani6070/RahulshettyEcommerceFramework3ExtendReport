package KalyaniAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KalyaniAcedemy.AbstractComponent.AbstractComponent;



public class CartPage extends AbstractComponent{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutele;
	
	
	public boolean ProductofCart(String prodName) {
	boolean match=cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(prodName));
	return match;
	}
	
	public CheckOutPage gotoCheckout() {
		checkoutele.click();
		WebDriver dirver;
		CheckOutPage co=new CheckOutPage(driver);
		return co;
	}
	
}