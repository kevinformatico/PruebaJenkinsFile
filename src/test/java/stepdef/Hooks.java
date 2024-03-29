package stepdef;

import Managers.context.TestContext;
import Managers.driver.DriverFactory;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import org.openqa.selenium.WebDriverException;
import support.util;

import java.util.ArrayList;

public class Hooks {

    public Scenario scenario;
    public ArrayList<byte[]> screenshotList;

    public Hooks(TestContext testContext){
        this.screenshotList=testContext.screenshotList;
    }

    @Before
    public void beforeHooks(Scenario scenario){
        this.scenario=scenario;
        if(DriverFactory.getDriver()!= null){
            DriverFactory.getDriver().manage().deleteAllCookies();
        }
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

    @BeforeStep
    public void cleanScrenshotList(){
        this.screenshotList=null;
    }

    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario){
        scenario.write(DriverFactory.getDriver().getCurrentUrl());
        if(screenshotList != null){
            for (byte[] sc: screenshotList) {
                scenario.embed(sc, "image/png");
            }
        }else{
            util.takeAndEmbedScreenshot(scenario);
        }


    }

}
