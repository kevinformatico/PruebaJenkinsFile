package driver;

import cucumber.api.Scenario;
import org.openqa.selenium.*;

public class util {
    public static void printCurrentThread(){
        System.out.println("Current Thread : " + Thread.currentThread().getId());
    }

    public static void takeAndEmbedScreenshot(Scenario scenario){
        byte[] screenshot = ((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    public static void waitFor(int segundos){
        try {
            Thread.sleep(segundos*1000);
        }catch (InterruptedException e){
            System.out.println("error de Thread.sleep");
            System.out.println(e.getMessage());
        }
    }

    public static boolean isVisible(By by){
        Boolean isVisible =  true; //asume que la pagina esta cargando
        try {
            isVisible = DriverFactory.getDriver().findElement(by).isDisplayed(); // si es true es decir que esta cargando
        }catch (NoSuchElementException e){
            isVisible=false; // si entra al catch quiere decir que no encontro el elemento, es decir ya cargo
        }
        return isVisible;
    }


    public static boolean isVisible(WebElement webElement){
        Boolean isVisible =  true; //asume que la pagina esta cargando
        try {
            isVisible = webElement.isDisplayed(); // si es true es decir que esta cargando
        }catch (NoSuchElementException e){
            isVisible=false; // si entra al catch quiere decir que no encontro el elemento, es decir ya cargo
        }
        return isVisible;
    }

    public static void waitUntilEscritorioComercialIsLoaded(){
        while (util.isVisible(By.id("loading-bar"))) {
            util.waitFor(1);
        }
    }
}
