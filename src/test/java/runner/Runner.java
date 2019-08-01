package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.apache.log4j.BasicConfigurator;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"stepdef"}, features = "src/test/resources/features/", tags = {"@run"},
plugin = {"pretty","json:target/cucumber-report/cucumber.json", "junit:target/cucumber.xml", "html:target/cucumber-report"})
public class Runner {
    @BeforeClass
    public static void configure(){
        BasicConfigurator.configure();
    }

}
