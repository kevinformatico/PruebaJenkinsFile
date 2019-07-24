package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "stepdef", features = "src/test/resources/features/",
plugin = {"pretty","json:target/cucumber-report/cucumber.json", "junit:target/cucumber.xml", "html:target/cucumber-report"})
public class Runner {

}
