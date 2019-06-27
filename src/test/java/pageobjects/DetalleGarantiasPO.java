package pageobjects;

import Generics.ManejadorTablaFrontEnd;
import Generics.util;
import com.google.gson.JsonArray;
import driver.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
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

    @FindBy(xpath = "//div[contains(@class,'search-bar__fieldset')]/div")
    private List<WebElement> seccionFiltros;

    @FindBy(xpath = "//div[contains(@class,'search-bar__buttonset')]//button")
    private List<WebElement> listadoBotones;

    @FindBy(xpath = "//ul[@class='search-bar__actions']//a")
    private WebElement botonLupaFiltro;

    @FindBy(xpath = "//input[@placeholder='Ej. 123456']")
    private WebElement inputFiltroPorFolio;






    public DetalleGarantiasPO() {
        super(DriverFactory.getDriver());
    }

    public JsonArray getJsonFromTabla(){
        waitForElementToAppear(tabla);
        return ManejadorTablaFrontEnd.extraerDatosDeTablaAJson(tabla);
    }

    public WebElement getTabla(){
        waitForElementToAppear(tabla);
        return tabla;
    }

    public void clickTab(String tab){
        driver.findElement(By.xpath("//a[contains(text(),'"+tab+"')]")).click();
    }

    public void esperarAQueCargueLaPagina(){
        waitUntilEscritorioComercialIsLoaded();
    }

    public void clickEnCabecera(String cabecera){
        waitForElementToAppear(tabla);
        DriverFactory.getDriver().findElement(By.xpath("//div[contains(@class,'table')]//th[contains(text(),'"+cabecera+"')]")).click();
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

    public void colapsarFiltro(){
        waitForElementToAppear(botonLupaFiltro);
        if(getClassDesplegarFiltro().contains("active")) {
            botonLupaFiltro.click();
        }else{
            Assert.fail("La sección filtro no esta desplegada");
        }
    }

    public String getClassDesplegarFiltro(){
        waitForElementToAppear(botonLupaFiltro);
        return botonLupaFiltro.getAttribute("class");
    }

    public List<String> getTextListadoFiltros(){
        ArrayList<String> listadoFiltros = new ArrayList<>();
        for (int i=0;i<this.listadoFiltros.size(); i++){
            listadoFiltros.add(this.listadoFiltros.get(i).getText());
        }
        return listadoFiltros;
    }

    public List<String> getTextListadoBotones(){
        ArrayList<String> listadoBotones = new ArrayList<>();
        for (int i=0;i<this.listadoBotones.size(); i++){
            listadoBotones.add(this.listadoBotones.get(i).getText());
        }
        return listadoBotones;
    }

    public void escribirEnCampoFiltroPorFolio(String texto){
        waitForElementToAppear(inputFiltroPorFolio);
        inputFiltroPorFolio.clear();
        inputFiltroPorFolio.sendKeys(texto);
    }

    public void clickEnBotoneraFiltro(String boton) {
        driver.findElement(By
                .xpath("//div[contains(@class,'search-bar__buttonset')]//button[contains(text(),'"+boton.trim()+"')]"))
                .click();
    }

    public List<WebElement> getSeccionFiltros() {
        return seccionFiltros;
    }

    public boolean validarMensaje(String mensaje){
        return driver.findElement(
                By.xpath("//section[contains(@class,'bch-mensaje-empresa')]//h5[contains(text(),'"+mensaje+"')]"))
                .getText().trim().contains(mensaje);
    }

    public void clickEnCuerpoDeLaCabecera(String cabecera){
        List<String> cabeceras = ManejadorTablaFrontEnd.getNameColumns(tabla);
        for(int i=0;i<cabeceras.size();i++){
            if(cabeceras.get(i).equals(cabecera)){
                driver.findElement(By.xpath("//div[contains(@ng-if,'listadoGarantias')]//tr[1]/td["+(i+1)+"]")).click();
            }
        }
    }

    public void seleccionarValorEnFiltro(String filtro, String valor){
        String seccionFiltro="//div[contains(@class,'search-bar__filters')]";
        String objFiltro="//div[contains(label, '"+filtro+"')]";
        String desplegarLista="//i[contains(@ng-click,'event')]";
        String seleccion= "//li//a[contains(span,'"+valor+"')]";
        if(driver.findElement(By.xpath(seccionFiltro+objFiltro+"//input")).getAttribute("class").contains("select")){
            driver.findElement(By.xpath(seccionFiltro+objFiltro+desplegarLista)).click();
            driver.findElement(By.xpath(seleccion)).click();
        }else{
            driver.findElement(By.xpath(seccionFiltro+objFiltro+"//input")).sendKeys(valor);
        }
    }
}

