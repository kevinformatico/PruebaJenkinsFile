package support;

import Managers.driver.DriverFactory;
import io.cucumber.core.api.Scenario;
import org.openqa.selenium.*;

public class util {

    public static void takeAndEmbedScreenshot(Scenario scenario){
        byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    public static void waitFor(int segundos){
        try {
            Thread.sleep(segundos*1000);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean seExpande(Dimension dimAnterior, Dimension dimNueva){
        boolean seExpande = false;
        int widthDimAnterior = dimAnterior.getWidth();
        int heightDimAnterior = dimAnterior.getWidth();
        int widthDimNueva = dimNueva.getWidth();
        int heightDimNueva = dimNueva.getHeight();
        if(widthDimAnterior<widthDimNueva) seExpande = true;
        if(heightDimAnterior<heightDimNueva) seExpande =true;
        return seExpande;
    }
}
