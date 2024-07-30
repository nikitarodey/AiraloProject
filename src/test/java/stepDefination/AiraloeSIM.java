package stepDefination;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.TestBase;
import pageObject.airaloPageObject;

public class AiraloeSIM extends TestBase{
	
	

	airaloPageObject object = new airaloPageObject();
	
	@Given("User open the browser and navigate to Airalo website")
	public void user_open_the_browser_and_navigate_to_airalo_website() throws Exception {
		
		object.openBrowser("chrome");
		object.openApplication(object.readdataPropertiesFile("url_airalo"));
		object.exportPageObjectElement_Airalo();
		
		object.popup1.click();
		object.JavaScriptClick(object.accept);
		
		//code to validate the popup and accept it
		String alertTextvalue =object.getTextWebElement(object.alertText);
		Assert.assertEquals("Would you like to receive push notifications?", alertTextvalue);
		System.out.println(alertTextvalue);
		object.waitSec(2);
		object.handleClickEvent(object.donAllow);
			
		
	}
	
	@When("User search for {string} in the search field on the home page")
	public void user_search_for_in_the_search_field_on_the_home_page(String location) {
		
		
		//validate searchBox is present
		WebElement searchboxPresent = object.searchBox;
		System.out.println(searchboxPresent.isDisplayed());
		
		//search for Japan
		object.searchBox.sendKeys(location);
		//object.handleDropDown(searchboxPresent, location);
	}

	@And("User select the {string} destination from the Local section in the autocomplete options")
	public void user_select_the_delocationstination_from_the_section_in_the_autocomplete_options(String location) {
	    
	    //select japan from the local section from list
	
		List<WebElement> list = object.countryList;
	
		for(WebElement ele : list)
		{
			String currentList=ele.getText();
			System.out.println(currentList);
			
			if(currentList.contains(location))
			{
				ele.click();
				break;
			}
			
		}
				
	}

	@Then("User should see the list of eSIM packages available for {string}")
	public void user_should_see_the_list_of_e_sim_packages_available_for(String location) {
	   
		
		//user validate text and title after landing on correct page
		String localSimText = object.getTextWebElement(object.localSim);
		Assert.assertEquals("Local eSIMs", localSimText);
		System.out.println("Text on the page of sim packages available is : " + localSimText);
		
		String localSimPageTitle = object.getTextWebElement(object.localSimPageText);
		Assert.assertEquals("Japan", localSimPageTitle);
		System.out.println("Title on the page of sim packages available is : " + localSimPageTitle);
		
		//user should see list of eSIM package available and get the count
		List<WebElement> count = object.packageList;
		int linkCount = count.size();
		System.out.println(linkCount);
	}

	@And("User choose the first eSIM package and click on {string} button")
	public void user_choose_the_first_e_sim_package_and_click_on_button(String string) {
	    
		String firstSIM = object.firstPackageList.getText();
		System.out.println(firstSIM);
		if(object.buyNowButton.isEnabled()) 
		{
			object.buyNowButton.click();
		}
		else {
			System.out.println("Buy Now button is disabled");
		}
		
	
	}
	

	@Then("a popup should appear with the package details")
	public void a_popup_should_appear_with_the_package_details() throws Exception {
	    
        object.waitSec(5);

		WebElement popup = object.popupPackagedetails;
		boolean isPopupPresent = popup.isDisplayed();
		System.out.println(isPopupPresent);
		
		
	}

	@Then("User should be able to validate below package details:")
	public void user_should_be_able_to_validate_below_package_details(io.cucumber.datatable.DataTable dataTable) {
	    
		WebElement popup = object.popupPackagedetails;
		
		String title = object.title.getText();
        String coverage = object.coverage.getText();
        String data = object.data.getText();
        String validity = object.validity.getText();
        String price = object.price.getText();
        System.out.println(title);
        System.out.println(coverage);
        System.out.println(data);
        System.out.println(validity);
        System.out.println(price);
		
		
		//user validate expected and actual values of popup
        List<Map<String, String>> packageDetails = dataTable.asMaps(String.class, String.class);
        
        for(Map<String, String> expectedDetails : packageDetails)
        {
        	
        	assertEquals(expectedDetails.get("Title"), title);
            assertEquals(expectedDetails.get("Coverage"), coverage);
            assertEquals(expectedDetails.get("Data"), data);
            assertEquals(expectedDetails.get("Validity"), validity);
            assertEquals(expectedDetails.get("Price"), price);
        }       
        
        
        
        
        System.out.println("Validation matched properlly");
        // Close the browser
        driver.quit();	
		
		
	}		

	
}
