package liidaveqa.testmavenproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class Tester 
{	static WebDriver driver = new ChromeDriver();
static String path = System.getProperty("user.dir");
public static String URL="https://www.liidaveqa.com/";
public static String excelPath="/Testdata.xlsx";
static ExtentTest test;
static ExtentReports report;

/*static PageObject pageObject;
	static IntialiseDriver intialiseDriver;*/
static Defaultmethods defaultmethods;
public static String date1,date2;
public static JavascriptExecutor executor = (JavascriptExecutor) driver;
public static ITestResult result;

@BeforeTest
public void browserLaunch() throws IOException {	
	report = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
	report
	.addSystemInfo("Host Name", "SoftwareTestingMaterial")
	.addSystemInfo("Environment", "Automation Testing")
	.addSystemInfo("User Name", "Ramya Palaniswamy");
	report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	System.out.println("Project Path"+path);
	System.setProperty("webdriver.chrome.driver", path+ "/chromedriver.exe");
	driver.get(URL);
	//		defaultmethods.waitForLoad(driver);
	driver.manage().window().maximize();

}



@Test
public static void implementLogic() throws Exception {
	test = report.startTest("ExtentDemo");
	FileInputStream fis=new FileInputStream(path+excelPath);
	XSSFWorkbook wb=new XSSFWorkbook(fis);
	XSSFSheet sh1= wb.getSheet("Credentials");
	XSSFSheet sh2= wb.getSheet("Addlead");
	PageObject pageObject=new PageObject(driver);
	driver.getTitle();
	/*	WebElement frame=driver.findElement(By.id("lightningjs-frame-usabilla_live"));
		driver.switchTo().frame(frame);*/
	pageObject.signIn();
	pageObject.username(sh1.getRow(0).getCell(1).getStringCellValue());
	pageObject.password(sh1.getRow(1).getCell(1).getStringCellValue());
	pageObject.submit();
	pageObject.burgerMenu();
	pageObject.salesMenu(driver,sh2.getRow(0).getCell(1).getStringCellValue());
	pageObject.buildProposalMenu(driver,sh2.getRow(1).getCell(1).getStringCellValue());
	pageObject.selectLeadButton(driver,executor);
	pageObject.addLeadButton(driver,executor);
	pageObject.firstName(sh2.getRow(2).getCell(1).getStringCellValue()+RandomStringUtils.randomAlphabetic(8));
	pageObject.lastName(sh2.getRow(3).getCell(1).getStringCellValue()+RandomStringUtils.randomAlphabetic(8));
	int phno=(int) (sh2.getRow(4).getCell(1).getNumericCellValue());
	pageObject.phoneNumber(Integer.toString(phno)+RandomStringUtils.randomNumeric(5));
	pageObject.email(sh2.getRow(5).getCell(1).getStringCellValue()+RandomStringUtils.randomAlphanumeric(3)+sh2.getRow(5).getCell(2).getStringCellValue());
	int extractDate1=((int) (sh2.getRow(6).getCell(1).getNumericCellValue()))+1;
	int extractDate2=((int) (sh2.getRow(8).getCell(1).getNumericCellValue()))+1;
	pageObject.calendar1(driver,executor);
	pageObject.date1(Integer.toString(extractDate1));
	//		pageObject.timeDropdown1(driver,sh2.getRow(7).getCell(1).getStringCellValue());
	pageObject.calendar2();
	pageObject.date2(Integer.toString(extractDate2));
	//		pageObject.timeDropdown2(driver,sh2.getRow(9).getCell(1).getStringCellValue());
	pageObject.addDocument(sh2.getRow(10).getCell(1).getStringCellValue(),executor);
	pageObject.addImage(sh2.getRow(11).getCell(1).getStringCellValue());
	String savedText=pageObject.saveButton();

	if(savedText.trim().equals("Lead Saved Successfully")) {
		test.log(LogStatus.PASS, "Lead created successfully");
	}else {
		test.log(LogStatus.FAIL, "Failed");

	}
}

@AfterMethod
public void getResult(ITestResult result) throws Exception{
	if(result.getStatus() == ITestResult.SUCCESS){
		String screenshotPath = getScreenhot(driver, result.getName());
		test.log(LogStatus.PASS, test.addScreenCapture(screenshotPath));
	}else if(result.getStatus() == ITestResult.SKIP){
		test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
	}

	report.endTest(test);
}


public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	String destination = System.getProperty("user.dir") + "/test-output/FailedTestsScreenshots/"+screenshotName+dateName+".png";
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
	return destination;
}


@AfterTest
public void quitBrowser() {
	driver.quit();
	report.endTest(test);
	report.flush();
}


}
