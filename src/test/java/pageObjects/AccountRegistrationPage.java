package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	JavascriptExecutor js = (JavascriptExecutor)driver;
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	//Elements
	@FindBy(id = "input-firstname")
	WebElement txtFirstname;
	
	@FindBy(id = "input-lastname")
	WebElement txtLastname;
	
	@FindBy(name = "email")
	WebElement txtEmail;
	
	@FindBy(name = "password")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkboxPolicy;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	//Action Methods
	public void setFirstName(String firstname) {
		txtFirstname.sendKeys(firstname);
	}
	public void setLastName(String lastname) {
		txtLastname.sendKeys(lastname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	public void setPrivacyPolicy() {
		//chkboxPolicy.click();
		
		js.executeScript("arguments[0].click();",chkboxPolicy);
	}
	public void clickContinue() {
		//btnContinue.click();
		
		js.executeScript("arguments[0].click();", btnContinue);
	}
	
	public String getConfirmationMsg() {
		try {
			return msgConfirmation.getText();
		}catch(Exception e) {
			return e.getMessage();
		}
	}
}
