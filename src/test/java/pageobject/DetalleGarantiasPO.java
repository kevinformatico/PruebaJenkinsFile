package pageobject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import driver.DriverFactory;
import driver.ManejadorTablaFrontEnd;
import driver.util;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class DetalleGarantiasPO extends LoadableComponent<DetalleGarantiasPO> {

    @FindBy(xpath = "//table[@class='table tabla-interactiva']")
    WebElement tabla;


    By BY_CABECERA = By.xpath("//thead/tr/th");
    By BY_FILAS = By.xpath("//tbody/tr");


    public DetalleGarantiasPO() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }


    public void imprimirTablaCompleta(){
        util.waitUntilElementIsPresent(tabla, 10);
        System.out.println("Imprimiendo tabla");
        System.out.println("Tabla: "+ ManejadorTablaFrontEnd.extraerDatosDeTablaAJson(tabla));
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
    }
}

