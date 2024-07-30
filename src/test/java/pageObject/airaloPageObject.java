package pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class airaloPageObject extends TestBase {
	
	@FindBy(xpath = "//div[@class='modal-content']//button[@class='btn']")
	public WebElement popup1;

	@FindBy(xpath = "//button[text()='ACCEPT']")
	public WebElement accept;
	
	@FindBy(xpath = "//div[text()='Would you like to receive push notifications?']")
	public WebElement alertText;

	@FindBy(xpath = "//button[@id='wzrk-confirm']")
	public WebElement allow;
	
	@FindBy(xpath = "//button[@id='wzrk-cancel']")
	public WebElement donAllow;
	
	@FindBy(css = ".inp-search-container input")
	public WebElement searchBox;
	
	@FindBy(xpath = "//ul[@class='countries-list position-absolute']")
	public List<WebElement> countryList;

	@FindBy(xpath = ("//a[contains(text(),'Local eSIMs')]"))
	public WebElement localSim;
	  
	@FindBy(xpath = ("//h2[contains(text(),'Japan')]"))
	public WebElement localSimPageText;
	
	@FindBy(xpath = ("//a[@class='sim-item-link']"))
	public List<WebElement> packageList;
	
	@FindBy(xpath = ("(//a[@class='sim-item-link'])[1]"))
	public WebElement firstPackageList;
	
	@FindBy(xpath = ("(//a[@class='sim-item-link'])[1]//button[contains(text(), 'BUY NOW')]"))
	public WebElement buyNowButton;
	
	@FindBy(xpath = ("//div[@class='sim-detail-header']"))
	public WebElement popupPackagedetails;
	
	@FindBy(xpath = ("//ul[@class='sim-detail-info-list']/li"))
	public List<WebElement> noOfpackageLists;
	
	
	@FindBy(xpath = ("//div[@class='sim-detail-operator']//p[contains(text(),'Moshi Moshi')]"))
	public WebElement title;
	
	@FindBy(xpath = ("//ul[@class='sim-detail-info-list']//p[contains(text(),'Japan')]"))
	public WebElement coverage ;

	@FindBy(xpath = ("//ul[@class='sim-detail-info-list']//p[contains(text(),'1 GB')]"))
	public WebElement data;
	
	@FindBy(xpath = ("//ul[@class='sim-detail-info-list']//p[contains(text(),'7 Days')]"))
	public WebElement validity ;
	
	@FindBy(xpath = ("//ul[@class='sim-detail-info-list']//p[contains(text(),'$4.50 USD')]"))
	public WebElement price ;
	
	//ul[@class='sim-detail-info-list']/li[1]/div/p    ------no of colnm present in row
	//ul[@class='sim-detail-info-list']/li[2]/div/p    ------no of colnm present in row
	//ul[@class='sim-detail-info-list']/li[3]/div/p    ------no of colnm present in row
	//ul[@class='sim-detail-info-list']/li[4]/div/p    ------no of colnm present in row
	
	
	
	public void JavaScriptClick(WebElement element)
	{
		JavascriptExecutor j = (JavascriptExecutor) driver;
	  	 j.executeScript("arguments[0].click();", element);
		
	}
	
	public void exportPageObjectElement_Airalo() {

		PageFactory.initElements(driver, this);
	}



	

}
