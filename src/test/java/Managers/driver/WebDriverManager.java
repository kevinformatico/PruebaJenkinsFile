package Managers.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverManager {
    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;

    public WebDriverManager(){
        driverType= castDriverType(System.getProperty("browser","CHROME"));
        environmentType= castEnvironmentType(System.getProperty("env","LOCAL"));
    }

    public WebDriver getDriver(){
        if(driver== null) driver= createDriver();
        return driver;
    }

    private WebDriver createDriver(){
        switch (environmentType){
            case LOCAL: driver = createLocalDriver();
            break;
            case REMOTE: driver = createRemoteDriver();
            break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver(){
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver(){
        switch (driverType){
            case FIREFOX:
                io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, System.getProperty("user.dir") + "/target/chromedriver.log");
                io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case SAFARI:
                throw new RuntimeException("Unsupported browser: " + driverType);
        }
        return driver;
    }

    public void closeDriver(){
        driver.close();
        driver.quit();
    }







    private DriverType castDriverType(String driverType){
        switch (driverType.toUpperCase()){
            case "CHROME": return DriverType.CHROME;
            case "FIREFOX": return DriverType.FIREFOX;
            case "SAFARI": return DriverType.SAFARI;
            default: throw new RuntimeException("Unsupported webdriver: " + driverType);
        }
    }
    private EnvironmentType castEnvironmentType(String envType){
        switch (envType.toUpperCase()){
            case "LOCAL": return EnvironmentType.LOCAL;
            case "REMOTE": return EnvironmentType.REMOTE;
            default: throw new RuntimeException("Unsupported environment: " + envType);
        }
    }
}
