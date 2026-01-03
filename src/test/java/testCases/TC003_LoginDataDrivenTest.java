package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDataDrivenTest extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups = "Datadriven")//grouping
	//@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)//getting dataprovider from different class
	//@Test(dataProvider="LoginData")//getting dataprovider from same class
	public void verify_LoginDataDrivenTest(String email, String pwd, String exp) throws InterruptedException {

			logger.info("****Started TC003_LoginDataDrivenTest****");
			try {
			//HomePage
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			//LoginPage
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();

			//MyAccountPage
			MyAccountPage myacc=new MyAccountPage(driver);
			boolean targetpage=myacc.isMyAccountPageDisplayed();
			
			/*Data is valid - Login Success - test case passes - logout
			                  Login UnSuccess - test case fails 
			                  
			 * Data is Invalid - Login Success - test case fails - logout
			                     Login UnSuccess - test case passes  */
			
			if (exp.equalsIgnoreCase("valid")) {
				
				if (targetpage==true) {
					
					myacc.clickLogout();
					Assert.assertTrue(true);
				}
				else {
					Assert.assertTrue(false);
				}
			}
			
			else {
				if (targetpage==true) {
					
					myacc.clickLogout();
					Assert.assertTrue(false);
				}
				else {
					Assert.assertTrue(true );
				}
			}
			}
			catch (Exception e) {
				Assert.fail();
			}
			Thread.sleep(3000);

			logger.info("****Finished TC003_LoginDataDrivenTest****");

			
		
	}


}
