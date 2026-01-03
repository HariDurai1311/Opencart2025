package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test (groups = {"Sanity","Master"})//grouping
	//@Test
	public void verify_Login() {

		logger.info("****Started TC002_LoginTest****");

		try {
			//HomePage
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			//LoginPage
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();

			//MyAccountPage
			MyAccountPage myacc=new MyAccountPage(driver);
			boolean targetpage=myacc.isMyAccountPageDisplayed();


			Assert.assertTrue(targetpage); //Assert.assertEquals(targetpage, true, "login failed");
		}
		catch (Exception e)
		{

			Assert.fail();
		}

		logger.info("****Finished TC002_LoginTest****");
	}}






























	
