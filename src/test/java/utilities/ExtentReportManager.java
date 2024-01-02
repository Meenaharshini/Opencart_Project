package utilities;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager extends BaseClass implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String reportName;
	
	public void onStart(ITestContext context) {
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test_Report_"+timestamp+".html";
		
		File file = new File(System.getProperty("user.dir")+"\\extentreports\\"+reportName);
		
		sparkReporter = new ExtentSparkReporter(file);
		//System.out.println(file);
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		//System.out.println(extent.getClass());
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Username", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS,"Test Passed");
	}
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
		try {
			String screenShotPath = captureScreenshot(result.getMethod().getMethodName());
			//System.out.println(screenShotPath);
			test.info("This is failed test case screenshot")
			.fail(result.getName()+ " test is failed", MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath, "Failed TestCase").build());
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.info("Test is Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context){
		extent.flush();
		//Desktop.getDesktop().browse(new File(reportName).toURI());
	}
	

}
