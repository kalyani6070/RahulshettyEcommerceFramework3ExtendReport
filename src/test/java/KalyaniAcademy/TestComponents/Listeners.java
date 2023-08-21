package KalyaniAcademy.TestComponents;

import org.testng.ITestListener;

import java.io.IOException;

import javax.xml.transform.Result;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import KalayniAcademy.resources.ExtendReportNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent=ExtendReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();//thread safe
	@Override		
    public void onTestStart(ITestResult result) {					
	  test=extent.createTest(result.getMethod().getMethodName());	
        	extentTest.set(test);	
    }
	
	 @Override		
	    public void onTestSuccess(ITestResult result) {					
		 extentTest.get().log(Status.PASS, "Test is Passed");
	    }
	 
	 @Override		
	    public void onTestFailure(ITestResult result) {			
	extentTest.get().fail(result.getThrowable());
	try {
		driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());//this step give us driver
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		 String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
	    }
	@Override		
    public void onFinish(ITestContext context) {					
        extent.flush();			
        		
    }		

    		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    		

    @Override		
    public void onTestSkipped(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    		

   		
}
