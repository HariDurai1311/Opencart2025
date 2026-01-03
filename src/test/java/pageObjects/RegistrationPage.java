package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
	

	
	public RegistrationPage(WebDriver driver)
	
	{
		super(driver);
	}
	
	@FindBy(id="input-firstname")
	WebElement txtFirstName;
	
	@FindBy(id="input-lastname")
	WebElement txtLastName;
	
	@FindBy(id="input-email")
	WebElement txtEmail;
	
	@FindBy(id="input-telephone")
	WebElement txtTelephone;
	
	@FindBy(id="input-password")
	WebElement txtPassword;
	
	@FindBy(id="input-confirm")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath= "//input[@name='agree']")
	WebElement btnCheckbox;
	
	@FindBy(xpath= "//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath= "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void setFirstName(String fName) {
		txtFirstName.sendKeys(fName);
	}
	
	public void setLastName(String lName) {
		txtLastName.sendKeys(lName);
	}
	
	public void setEmail(String Email) {
		txtEmail.sendKeys(Email);
	}
	
	public void setTelephone(String Telephone) {
		txtTelephone.sendKeys(Telephone);
	}
	
	public void setPassword(String Password) {
		txtPassword.sendKeys(Password);
	}
	
	public void setConfirmPassword(String ConfirmPassword) {
		txtConfirmPassword.sendKeys(ConfirmPassword);
	}
	

	public void clickCheckBox() 
	{
		btnCheckbox.click();
	}

	public void clickContinue() 
	{
		btnContinue.click();
	}
	
	public String getConfirmationMsg() {
	try {
		return (msgConfirmation.getText());
		}
	catch (Exception e)
	{
		return(e.getMessage());
	}
}}
