package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement linkMyAccount;
	
	@FindBy(linkText = "Register")
	WebElement linkRegister;
	
	@FindBy(linkText = "Login")
	WebElement linkLogin;
	
	//Action Methods
	public void clickMyAccount() {
		linkMyAccount.click();
	}
	public void clickRegister() {
		linkRegister.click();
	}
	public void clickLogin() {
		linkLogin.click();
	}
}