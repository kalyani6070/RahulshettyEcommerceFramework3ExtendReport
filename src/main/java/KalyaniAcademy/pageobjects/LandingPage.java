package KalyaniAcademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KalyaniAcedemy.AbstractComponent.AbstractComponent;



public class LandingPage extends AbstractComponent{
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		//to assign instance variable driver from stanalonetest i use this keyword
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement userPassword;

	@FindBy(id="login")
	WebElement Login;
	
	//Error validation locator
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	//add all the actions
	public ProductCatelog loginApplication(String email, String password) {
		userEmail.sendKeys(email);//wrote here email because values should come from test only
		userPassword.sendKeys(password);
		Login.click();
		ProductCatelog pc =new ProductCatelog(driver);
		return pc;
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}