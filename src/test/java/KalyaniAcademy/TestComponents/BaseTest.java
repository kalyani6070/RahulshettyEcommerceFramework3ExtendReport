package KalyaniAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import KalyaniAcademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop= new Properties();		
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\KalayniAcademy\\resources\\GlobalData.properties");
		prop.load(fis);
		//now we can extract any value from file using method getProperty
		String browserName=prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			 driver= new ChromeDriver();//life is here but not down so assign public WebDriver driver;
			
			}
			else if(browserName.equalsIgnoreCase("firefox")) {
				
			}else if(browserName.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", "edge.exe");
				 driver= new EdgeDriver();
			}	
		
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	//life is not here
	driver.manage().window().maximize();
	return driver;
	}
	
	@BeforeTest(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver= initializeDriver();
		 lp=new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterTest(alwaysRun=true)
	public void teardown() {
		driver.close();
	}
	
	//Screenshot code
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"\\reports\\ testCaseName" + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"\\reports\\ testCaseName" + ".png";
	}
	
}
