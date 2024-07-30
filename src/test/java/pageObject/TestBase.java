package pageObject;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBase {

	
	public static WebDriver driver;
	public static String projectPath = System.getProperty("user.dir");
	public static FileInputStream fi;
	public static Properties prop;
	public static WebDriverWait wait;

	public static void init() throws Exception {

		fi = new FileInputStream(projectPath + "\\Config\\config.properties");
		prop = new Properties();
		prop.load(fi);

	}

	public void openBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath+"\\Drivers\\chromedriver.exe");
		    driver = new ChromeDriver();
			
		}
		if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver", projectPath+"\\Drivers\\geckodriver.exe");
		    driver = new FirefoxDriver();
		
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	public void openApplication(String url) {
		driver.get(url);
	}

	public void closeBrowser() {

		driver.quit();
	}

	public void handleTextBox(WebElement we, String value) {

		we.sendKeys(value);
	}

	public void handleClickEvent(WebElement we) {

		we.click();
	}

	public String getTextWebElement(WebElement we) {
		return we.getText();
	}

	public String getCurrentPageTitle() {
		return driver.getTitle();
	}

	public void handleDropDown(WebElement we, String value) {
		Select s = new Select(we);
		s.selectByVisibleText(value);
	}

	public void handleAlertaccept() {
		try {
	
			driver.switchTo().alert().accept();
		} catch (Exception e) {

		}
	}

	public void handleAlertdismiss() {
		try {
			
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {

		}
	}
	public void handleFrame(WebElement we) {
		driver.switchTo().frame(we);
	}

	public void waitSec(int sec) throws Exception {
		sec = sec * 1000;
		Thread.sleep(sec);
	}

	
	public void explicitWait(WebDriver driver, Duration sec)
	{
		wait = new WebDriverWait(driver, sec);
	}
	
	public static void takeScreenShot(String fileName) throws Exception {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File f = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File(projectPath + "\\Screenshot\\" + fileName + ".png"));

	}

	

	public String readdataPropertiesFile(String key) throws Exception {
		
		init();
		return prop.getProperty(key);

		
	}



	
}
