package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "stepdef", features = "src/test/resources/features/CV-1519.feature",
plugin = {"pretty","json:target/cucumber-report/cucumber.json", "junit:target/cucumber.xml", "html:target/cucumber-report"})
public class Runner {

}
