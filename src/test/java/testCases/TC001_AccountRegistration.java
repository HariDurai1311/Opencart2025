package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass {
	
	
@Test (groups = {"Regression","Master"})//grouping
//@Test
public void verify_account_registration() {
	
	logger.info("****Started TC001_AccountRegistration****");
	
	try {
	
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	
	logger.info("****Clicked on My Account****");
	hp.clickRegister();
	
	logger.info("****Clicked on Register Link****");
	
	RegistrationPage rp=new RegistrationPage(driver);
	logger.info("****Providing Customer Details****");
	//rp.setFirstName("John");
	//rp.setLastName("walk");
	//rp.setEmail("hari1311@gmail.com");
	//rp.setTelephone("8451896585");
	
	//rp.setPassword("Hari@123");
	//rp.setConfirmPassword("Hari@123");
	
	rp.setFirstName(randomString().toUpperCase());
	rp.setLastName(randomString().toUpperCase());
	rp.setEmail(randomString() +"@gmail.com");
	rp.setTelephone(randomNumeric());
	
	String password=randomAlphaNumeric();
	
	rp.setPassword(password);
	rp.setConfirmPassword(password);
	rp.clickCheckBox();
	rp.clickContinue();
	logger.info("****Validating expected message****");
	String confirmmessage= rp.getConfirmationMsg();
	if (confirmmessage.equals("Your Account Has Been Created!"))
	{
		Assert.assertTrue(true);
	}
	else
	{
		Assert.assertTrue(false);
		logger.error("Test Failed");
		 logger.debug("Debug logs");
	}
	//Assert.assertEquals(confirmmessage, "Your Account Has Been Created!");
	}
	catch (Exception e)
	{
		
		Assert.fail();
	}
	logger.info("****Finished TC001_AccountRegistration****");
	
}




}
