package stepdef;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import Generics.util;
import org.openqa.selenium.WebDriverException;

public class Hooks {

    public Scenario scenario;

    public Hooks(){

    }

    @Before
    public void beforeHooks(Scenario scenario){
        this.scenario=scenario;
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()) {
            try {
                util.takeAndEmbedScreenshot(scenario);
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
    }

    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario){
        util.takeAndEmbedScreenshot(scenario);
    }

}
