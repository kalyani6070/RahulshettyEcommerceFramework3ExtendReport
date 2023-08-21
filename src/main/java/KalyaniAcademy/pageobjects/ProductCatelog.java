package KalyaniAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KalyaniAcedemy.AbstractComponent.AbstractComponent;


public class ProductCatelog extends AbstractComponent{
	WebDriver driver;
	public ProductCatelog(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By productsBy=By.cssSelector(".mb-3");
	
	By addtoCart=By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage=By.cssSelector("#toast-container");
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getProductList() {
		waitforElementtoApper(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String prodName) {//this will come from TC productname
		WebElement prod=getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProducttoCart(String prodName) {
		WebElement prod=getProductByName(prodName);//prod
				prod.findElement(addtoCart).click();
				//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); see how its breakdown above
				waitforElementtoApper(toastMessage);
				waitforElementtodisapper(spinner);

	}
	
}