package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.logging.Level;

public class SharedDriver {

	public SharedDriver() {

		String headless = System.getProperty("headless", "false");
		String webdriver = System.getProperty("browser", "chrome");
		ChromeOptions options = new ChromeOptions();

		if(headless.equals("true")) options.addArguments("headless");
		options.setExperimentalOption("useAutomationExtension", false);
		util.printCurrentThread();

		if (DriverFactory.getDriver() == null) {
			switch(webdriver) {
				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					DriverFactory.addDriver(new FirefoxDriver());
					break;
				case "chrome":
					System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, System.getProperty("user.dir") + "/target/chromedriver.log");
					WebDriverManager.chromedriver().setup();
					DriverFactory.addDriver(new ChromeDriver(options));
					break;
				case "safari":
					//TODO: implement SafariDriver
					throw new RuntimeException("Unsupported webdriver: " + webdriver);
				default:
					throw new RuntimeException("Unsupported webdriver: " + webdriver);
			}
		}
	}
}
