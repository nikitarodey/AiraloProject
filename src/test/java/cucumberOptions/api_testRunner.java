package cucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "Features/airaloAPI.feature",
        glue = "stepDefination",
        plugin = {"pretty", "html:target/cucumber-report/APIreport.html", "json:target/cucumber.json"},
        monochrome=true
        //tags="@Test3"
        
)
public class api_testRunner extends AbstractTestNGCucumberTests {
}