package KalyaniAcademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	//how many time you want to run fail test again.
	int count=0;
	int maxtry=1;
	@Override
	public boolean retry(ITestResult result) {
	if(count<maxtry) {
		count++;
		return true;
	}
		
		return false;
	}

}
