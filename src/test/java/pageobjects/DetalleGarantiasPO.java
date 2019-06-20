package pageobjects;

import Generics.ManejadorTablaFrontEnd;
import com.google.gson.JsonArray;
import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.cert.X509Certificate;
import java.util.List;

public class DetalleGarantiasPO extends BasePage {

    @FindBy(xpath = "//table[@class='table tabla-interactiva']")
    private WebElement tabla;

    @FindBy(xpath = "//a[contains(text(),'Por constituir')]")
    private WebElement tabPorConstituir;

    @FindBy(className = "bch-paginador")
    private WebElement paginador;

    @FindBy(xpath = "//div[contains(@ng-model,'registrosPorPagina')]//i[contains(@ng-click,'event')]")
    private WebElement flechaSelectPaginador;

    @FindBy(xpath = "//ul[contains(@repeat,'listadoRegistrosPaginacion')]//a")
    private List<WebElement> listadoPaginador;

    @FindBy(xpath = "//div[contains(@class,'search-bar__filters')]//label")
    private List<WebElement> listadoFiltros;

    @FindBy(xpath = "//div[contains(@class,'search-bar__buttonset')]//button")
    private List<WebElement> listadoBotones;

    @FindBy(xpath = "//ul[@class='search-bar__actions']//li")
    private WebElement botonLupaFiltro;

    @FindBy(xpath = "//input[@placeholder='Ej. 123456']")
    private WebElement inputFiltroPorFolio;






    public DetalleGarantiasPO() {
        super(DriverFactory.getDriver());
    }

    public JsonArray getJsonFromTabla(){
        return ManejadorTablaFrontEnd.extraerDatosDeTablaAJson(tabla);
    }

    public WebElement getTabla(){
        waitForElementToAppear(tabla);
        return tabla;
    }

    public void clickTabPorConstituir(){
        waitUntilEscritorioComercialIsLoaded();
        tabPorConstituir.click();
    }

    public void esperarAQueCargueLaPagina(){
        waitUntilEscritorioComercialIsLoaded();
    }

    public void clickEnCabecera(String cabecera){
        waitForElementToAppear(tabla);
        DriverFactory.getDriver().findElement(By.xpath(cabecera)).click();
    }

    public Dimension obtenerSizeCabecera(String cabecera){
        waitForElementToAppear(tabla);
        return DriverFactory.getDriver().findElement(By.xpath("//th[contains(text(),'"+cabecera+"')]")).getSize();
    }

    public boolean estaVisiblePaginador(){
        return paginador.isDisplayed();
    }

    public boolean validarOpcionesPaginador(List<String> opciones){
        boolean iguales= false;
        for (int i=0; i<opciones.size(); i++){
            iguales= listadoPaginador.get(i).getText().contains(opciones.get(i).trim());
        }
        return iguales;
    }

    public void scrollAlFondoDeLaPagina(){
        scrollEndPage();
    }

    public void clickFlechaSelectPaginador(){
        waitForElementToAppear(flechaSelectPaginador);
        flechaSelectPaginador.click();
    }

    public void desplegarFiltro(){
        waitForElementToAppear(botonLupaFiltro);
        botonLupaFiltro.click();
    }

    public boolean validarFiltros(List<String> filtros){
        boolean iguales= false;
        for (int i=0; i<filtros.size();i++){
            iguales=listadoFiltros.get(i).getText().contains(filtros.get(i).trim());
        }
        return iguales;
    }

    public boolean validarBotones(List<String> botones){
        boolean iguales= false;
        for (int i=0; i<botones.size();i++){
            iguales=listadoBotones.get(i).getText().contains(botones.get(i).trim());
        }
        return iguales;
    }

    public void escribirEnCampoFiltroPorFolio(String texto){
        waitForElementToAppear(inputFiltroPorFolio);
        inputFiltroPorFolio.clear();
        inputFiltroPorFolio.sendKeys(texto);
    }

    public void clickEnBotoneraFiltro(String boton) {
        DriverFactory.getDriver()
                .findElement(By
                        .xpath("//div[contains(@class,'search-bar__buttonset')]//button[contains(text(),'"+boton.trim()+"')]"))
                .click();
    }

    public boolean validarFiltrosVacios(){
        return inputFiltroPorFolio.getText().trim().isEmpty();
    }


}

