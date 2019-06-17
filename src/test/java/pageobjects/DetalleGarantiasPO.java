package pageobjects;

import Generics.ManejadorTablaFrontEnd;
import com.google.gson.JsonArray;
import driver.DriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetalleGarantiasPO extends BasePage {

    @FindBy(xpath = "//table[@class='table tabla-interactiva']")
    private
    WebElement tabla;

    public DetalleGarantiasPO() {
        super(DriverFactory.getDriver());
    }

    public void imprimirTablaCompleta(){
        waitUntilEscritorioComercialIsLoaded();
        waitForElementToAppear(tabla);
        JsonArray objeto = ManejadorTablaFrontEnd.extraerDatosDeTablaAJson(tabla);


        //db






        System.out.println(objeto);
    }

    public WebElement getTabla(){
        waitUntilEscritorioComercialIsLoaded();
        return tabla;
    }

}

