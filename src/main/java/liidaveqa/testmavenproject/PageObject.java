package liidaveqa.testmavenproject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

	private WebDriver driver;
	static Defaultmethods defaultmethods;
	static WebDriverWait wait; 


	private static String URL="https://www.liidaveqa.com/";

	@FindBy(xpath="//a[text()='Sign In']")
	WebElement signInlink;


	@FindBy(id="j_username")
	WebElement username;

	@FindBy(id="j_password")
	WebElement password;

	@FindBy(id="loginSubmit")
	WebElement submit;

	@FindBy(xpath="//i[@class='far fa-bars v2-hamburger-menu']")
	WebElement burgerMenu;

	@FindBy(xpath="//a[contains(.,'$arg')]")
	WebElement salesMenu;

	@FindBy(xpath="//a[contains(.,'$arg')]")
	WebElement buildProposalMenu;

	@FindBy(xpath="//a[contains(.,'SELECT LEAD')]")
	WebElement selectLeadButton;

	@FindBy(xpath="//a[@href='/proposal-tool/lead/create?source=search']")
	WebElement addLeadButton;

	@FindBy(id="firstName")
	WebElement firstname;

	@FindBy(id="lastName")
	WebElement lastname;

	@FindBy(id="phNo")
	WebElement phonenumber;

	@FindBy(id="email")
	WebElement email;

	@FindBy(id="calender1")
	WebElement calendar1;

	@FindBy(id="calender2")
	WebElement calendar2;

	//	@FindBy(xpath="//i[@class='far fa-bars v2-hamburger-menu']")
	//	WebElement burgerMenu;

	@FindBy(xpath="//div[@class='addLeadButton']")
	WebElement uploadIcon;
	
	
	@FindBy(id="scheduleRequestTime")
	WebElement timeDropdown1;
	
	@FindBy(id="appointmentRequestTime")
	WebElement timeDropdown2;
	
	@FindBy(xpath="//div[@class='leads-uploads-add']")
	WebElement addDocument;
	
	@FindBy(name="documents[1].documentType")
	WebElement docDropdown;
	
	@FindBy(name="documentFiles[1]")
	WebElement docFiles;
	
	@FindBy(name="imageFiles")
	WebElement uploadImageIcon;
	
	@FindBy(xpath="//span[contains(.,'SAVE LEAD')]")
	WebElement saveLeadButton;
	
	@FindBy(xpath="//span[contains(.,'Add To Lead')]")
	WebElement addToLeadButton;
	
	@FindBy(xpath="//li[contains(.,'Lead Saved Successfully')]")
	WebElement leadSaved;
	

	public PageObject(WebDriver driver) {
		this.driver=driver;
		/*driver.get(URL);
		driver.manage().window().maximize();*/
		PageFactory.initElements(driver, this);  
	}

	public void signIn() {
		signInlink.click();	  
	}

	public void username(String arg) {
		username.sendKeys(arg);
	}

	public void password(String arg) {
		password.sendKeys(arg);
	}	

	public void submit() {
		submit.click();
	}

	public void burgerMenu() {
		burgerMenu.click();
	}

	public void salesMenu(WebDriver driver, String arg) {
		driver.findElement(By.xpath("//a[contains(.,'"+arg+"')]")).click();;
	}

	public void buildProposalMenu(WebDriver driver,String arg) {
		driver.findElement(By.xpath("//a[contains(.,'"+arg+"')]")).click();;
	}

	public void selectLeadButton(WebDriver driver, JavascriptExecutor executor) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'SELECT LEAD')]")));
		executor.executeScript("arguments[0].click();", selectLeadButton);
//		selectLeadButton.click();
	}

	public void addLeadButton(WebDriver driver,JavascriptExecutor executor) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/proposal-tool/lead/create?source=search']")));
		executor.executeScript("arguments[0].click();", addLeadButton);
	}

	public void firstName(String arg) {
		firstname.sendKeys(arg);
	}

	public void lastName(String arg) {
		lastname.sendKeys(arg);
	}

	public void phoneNumber(String arg) {
		phonenumber.sendKeys(arg);
	}

	public void email(String arg) {
		email.sendKeys(arg);
	}

	public void date1(String arg) {
		driver.findElement(By.xpath("//a[contains(.,'"+arg+"')]")).click();;
	}
	
	public void date2(String arg) {
		driver.findElement(By.xpath("//a[contains(.,'"+arg+"')]")).click();;
	}
	
	public void calendar1(WebDriver driver,JavascriptExecutor executor) {
        executor.executeScript("arguments[0].scrollIntoView();", calendar1);
		calendar1.click();
	}
	
	public void calendar2() {
		calendar2.click();
	}

	public void timeDropdown1(WebDriver driver,String arg) {
		timeDropdown1.click();
		Select select=new Select(timeDropdown1);
		select.selectByVisibleText(arg);
	}
	
	public void timeDropdown2(WebDriver driver,String arg) {
		timeDropdown2.click();
		Select select=new Select(timeDropdown2);
		select.selectByVisibleText(arg.replaceAll("", arg));
	}
	
	public void addDocument(String arg,JavascriptExecutor executor) {
		addDocument.click();
		Select select=new Select(docDropdown);
		select.selectByVisibleText("OTHER");
		docFiles.sendKeys(arg);
		executor.executeScript("arguments[0].click();", addToLeadButton);
	}
	
	public void addImage(String arg) {
		uploadImageIcon.sendKeys(arg);
	}
	
	public String saveButton() {
		saveLeadButton.click();
		return (leadSaved.getText());
	}

}
