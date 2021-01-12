package liidaveqa.testmavenproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Sample {
	@BeforeTest
	public void browserLaunch() {
		String path = System.getProperty("user.dir");
		System.out.println("Project Path"+path);
		System.setProperty("webdriver.chrome.driver", path+ "");
		WebDriver driver = new ChromeDriver();
		launchBrowser();
	}
	
	
	public static void launchBrowser() {
		
	}

}
