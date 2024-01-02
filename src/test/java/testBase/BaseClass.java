package testBase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class BaseClass {
	public static WebDriver driver;
	
	public Logger logger;
	
	public ResourceBundle rb;
	
	public String subFolderName;
	
	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters("browser")
	public void setup(String brow) {
		//Invoke current class where the test get failed.
		logger = LogManager.getLogger(this.getClass());
		
		//Call the config file.- load the config.properties file.
		rb = ResourceBundle.getBundle("config"); 
		
		//Chromeoptions - used to disable the text "Chrome is being in automate mode" when executing test script will pop-out in each browser page.
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		
		
		switch(brow.toLowerCase()) {
		case "chrome": 
			driver = new ChromeDriver(options);
			break;
		case "edge" : 
			driver = new EdgeDriver();
			break;
		case "firefox" : 
			driver = new FirefoxDriver();
			break;
		default: System.out.println("Invalid Browser name");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get(rb.getString("appURL1"));
		
	}
	@AfterClass(groups = {"Master","Regression","Sanity"})
	public void tearDown() {
		driver.quit();
		
	}
	
	public String randomString() {
		String generateString = RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}
	public String randomNumber() {
		String generateNumber = RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}
	public String randomAlphaNumeric() {
		String generateAlphaNumeric = RandomStringUtils.randomAlphanumeric(15);
		return generateAlphaNumeric;
	}
	public String captureScreenshot(String filename) {
		if(subFolderName == null) {
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			subFolderName = "Test_Report_Folder"+date.format(formatTime);
		}
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		TakesScreenshot	ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"\\screenshots\\"+File.separator+subFolderName+File.separator+timestamp+"-"+filename+".png");
		//String destination = System.getProperty("user.dir")+"\\screenshots\\" +timestamp+ "-" +filename+ ".png";
		try {
			FileUtils.copyFile(source, destination);
		}catch(Exception e) {
			e.getMessage();
		}
		return destination.getAbsolutePath();
	
	}
}
