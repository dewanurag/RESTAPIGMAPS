package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\featureFiles", 
plugin ={"pretty","json:target/jsonReports/cucumber-report.json"}
,glue = {"stepDefinitions"}
,tags = "@AddPlaceDyn or @DeletePlace"
		)
public class TestRunner {

}
