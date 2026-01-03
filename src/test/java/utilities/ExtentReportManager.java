package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener, ISuiteListener{
	public ExtentReports extent;
	public ExtentTest test;
    public ExtentSparkReporter sparkReporter;
    
    String repName;

 // Executes before <test> tag in testng.xml
    public void onStart(ITestContext testContext) {
    	
    	/*
         * SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
         * Date dt = new Date();
         * String currentdatetimestamp = df.format(dt);
         */

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//Single line code for timestamp 
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);//specify the location of the report

        sparkReporter.config().setDocumentTitle("opencart Automation Report");//Title of the report
        sparkReporter.config().setReportName("opencart Functional Testing"); //Name  of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);
        
        List <String> includedgroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedgroups.isEmpty()) {
        extent.setSystemInfo("Groups", includedgroups.toString());
        } 	 	
    }

    // Executes when test case is started
    /*public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); //to display groups in the reports
        test.log(Status.INFO, result.getName() + " started executing");
    }*/

    // Executes when test case is passed
    public void onTestSuccess(ITestResult result) {
    	test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); //to display groups in the reports
        test.log(Status.INFO, result.getName() + " started executing");
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    // Executes when test case is failed
    public void onTestFailure(ITestResult result) {
    	test = extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups()); //to display groups in the reports
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());
        
        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
            
        } catch (IOException e1) {
            e1.printStackTrace();
        } 
    }

    // Executes when test case is skipped
    public void onTestSkipped(ITestResult result) {
    	test = extent.createTest(result.getTestClass().getName());
    	test.assignCategory(result.getMethod().getGroups()); //to display groups in the reports    	
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage()); 
    }

    // Executes after <test> tag in testng.xml
    public void onFinish(ITestContext testContext) {

        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir")
                + "\\reports\\" + repName;

        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            // Build file reference
            File reportFile = new File(System.getProperty("user.dir") + "\\Reports\\" + repName);

            // Convert to URL properly (no deprecated method)
            URL url = reportFile.toURI().toURL();

            // Create Email Message
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(reportFile.toURI().toURL()));
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("haridurai1311@gmail.com", "lvqawjhdjnirfhcl"));
            email.setSSLOnConnect(true);
            email.setFrom("hari13112908@gmail.com");  // Sender
            email.setSubject("Test Results");
            email.setMsg("Please find Attached Report.");

            email.addTo("hari13112908@gmail.com");   // Receiver

            // Attach Report to Email
            email.attach(url, "Extent Report", "Please check report");

            // Send Email
            email.send();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        /*try {
        	
            /*URL url = new URL("file:///" 
                    + System.getProperty("user.dir") 
                    + "\\reports\\" + repName);

            // Create the email message
            ImageHtmlEmail email = new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));

            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(
                    new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
            email.setSSLOnConnect(true);

            email.setFrom("pavanoltraining@gmail.com");   // Sender
            email.setSubject("Test Results");
            email.setMsg("Please find Attached Report....");

            email.addTo("pavankumar.busyqa@gmail.com");   // Receiver
            email.attach(url, "extent report", "please check report...");
            email.send();   // send the email

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        
//password: lvqa wjhd jnir fhcl
    }
    
}
