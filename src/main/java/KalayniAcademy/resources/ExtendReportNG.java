package KalayniAcademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportNG {
	
	public static ExtentReports getReportObject() {
		String path= System.getProperty("(user.dir")+"\\ExtendReportt\\index.html";
		ExtentSparkReporter reporter= new ExtentSparkReporter(path);
		reporter.config().setReportName("Web automation Result");//what you report name 
		reporter.config().setDocumentTitle("Automation test Result");//we can change expernd repot page title
		
		ExtentReports extend =new ExtentReports();
		extend.attachReporter(reporter);
		//we can add tester name.
		extend.setSystemInfo("Tester","Kalyani Spekar");
		return extend;
	}
}
