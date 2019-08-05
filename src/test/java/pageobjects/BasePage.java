package pageobjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    private static final int WAIT_TIMEOUT = 30;
    private static final int DEFAULT_TIMEOUT = 1;
    private static final int POLLING = 100;
    private Logger log = LogManager.getLogger(BasePage.class);
    private

    final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "loading-bar")
    private WebElement barraCargando;

    protected BasePage(WebDriver driver){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, WAIT_TIMEOUT, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,DEFAULT_TIMEOUT), this);
    }

    protected BasePage(WebDriver driver, int timeOutSec){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, timeOutSec, POLLING);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeOutSec), this);
    }

    protected BasePage(WebDriver driver, int timeOutSec, int pollingSec){
        this.driver=driver;
        this.wait= new WebDriverWait(driver, timeOutSec, pollingSec);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, timeOutSec), this);
    }

    protected WebDriver getDriver(){
        return driver;
    }

    protected void waitForElementToAppear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToDisappear(WebElement locator) {
        wait.until(ExpectedConditions.invisibilityOf(locator));
    }

    protected void waitForTextToDisappear(WebElement locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(locator, text)));
    }

    protected boolean isVisible(WebElement webElement){
        boolean isVisible;
        try {
            return webElement.isDisplayed();
        }catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }catch (Exception e){
            log.error("Error Desconocido: ", e);
            return false;
        }
    }

    protected boolean isInvisible(WebElement element){
        try {
            return !element.isDisplayed();
        } catch (NoSuchElementException e){
            return true;
        }
    }

    public void waitUntilEscritorioComercialIsLoaded() {
        while (isVisible(barraCargando)){

        }
    }

    protected void waitFor(int segundos){
        try {
            Thread.sleep(segundos*1000);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    protected void scrollEndPage() {
        try {
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public byte[] takeScreenshot(){
         return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    protected WebElement getElementFrom(By locator){
        return getDriver().findElement(locator);
    }

}
