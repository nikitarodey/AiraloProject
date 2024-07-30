package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		
		features= {"Features/airalo_eSIM.feature"}, //feature file path
		glue= {"stepDefination"}, //step definition file path
		plugin = {"pretty", "html:target/cucumber-report/UIreport.html", "json:target/cucumber.json"},
		monochrome=true, // format console info
		dryRun=true
		)

public class testRunner extends AbstractTestNGCucumberTests {

}
//@RunWith(Cucumber.class)