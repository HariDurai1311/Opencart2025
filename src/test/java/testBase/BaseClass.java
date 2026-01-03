package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties p;

	@BeforeClass (groups = {"Regression","Master","Sanity"})//grouping
	//@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {

		//Loading Config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);



		//Loading log file
		logger=LogManager.getLogger(this.getClass());
		switch (br.toLowerCase()) {
		case "chrome": driver= new ChromeDriver(); break;
		case "edge": driver= new EdgeDriver(); break;
		case "firefox": driver= new FirefoxDriver(); break;
		default: System.out.println("Invalid Browser"); return;

		}


		//driver= new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(p.getProperty("url")); //reading values from properties file
		driver.manage().window().maximize();


	}
	@AfterClass	(groups = {"Sanity","Regression","Master", "Datadriven"})//grouping
	//@AfterClass	
	public void teardown() {
		driver.quit();
	}

	//For Randomly Generate Data
	@SuppressWarnings("deprecation")
	public String randomString()
	{
		String genString=RandomStringUtils.randomAlphabetic(5, 10);
		return genString;
	}
	@SuppressWarnings("deprecation")
	public String randomNumeric()
	{
		String gennumber=RandomStringUtils.randomNumeric(10);
		return gennumber;
	}
	
	

	@SuppressWarnings("deprecation")
	public String randomAlphaNumeric()
	{
		String genString=RandomStringUtils.randomAlphabetic(5, 10);
		String gennumber=RandomStringUtils.randomNumeric(10);
		return (genString +"@"+ gennumber);
	}
	
	public String captureScreen(String tname) throws IOException {

	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath = System.getProperty("user.dir")
	            + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

	    File targetFile = new File(targetFilePath);

	    sourceFile.renameTo(targetFile);

	    return targetFilePath;
	}

}
