package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.select.Collector;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ConfiguracionDeProductosPO extends BasePage {

    private Logger log = LogManager.getLogger(ConfiguracionDeProductosPO.class);

    public ConfiguracionDeProductosPO(){
        super(DriverFactory.getDriver());
    }

    String botonAgregarAOportunidad = ".//button[contains(@class,'agregar-producto') and contains(text(),'Agregar a oportunidad')]";
    String familia;
    WebElement familiaElement;

    final String SELECTOR_ASOCIAR_LIMITES="//article[.//span[contains(text(),'Asociar límites')]]//div[contains(@class,'actions')]/a[ .//i[contains(@class,'arrow-right')]]";
    final String SELECTOR_APODERADOS="//article[.//span[contains(text(),'Apoderados')]]//div[contains(@class,'actions')]/a[ .//i[contains(@class,'arrow-right')]]";
    final String SELECT_ELEMENT="%s//li//a[contains(span,'%s')]";
    final String SELECT_FIRST_ELEMENT="%s//li//a[1]";
    final String SELECT_RAMDOM_ELEMENT="%s//li//a[%s]";
    final String SELECT_BOTON_DESPLEGAR_LISTA="%s//i[contains(@ng-click,'event')]";
    final String SELECTOR_PRODUCTO_DE_FAMILIA="%s//cdn-detalle-familia-directive//div[label[contains(text(),'Producto')]]";
    final String SELECCIONAR_FAMILIA_XPATH = "//cdn-venta-taller-directive[contains(@on-agregar,'agregarProductoCarro')]//div[contains(@ng-repeat,'listadoFamilias') and .//div[contains(h5,'%s')]]";
    final String XPATH_PARA_FLECHA_DESPLEGAR_FAMILIA = "%s//div[contains(@class,'actions')]/a[contains(@ng-click,'productos')]";
    final String XPATH_PARA_CARRITO = "//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-class,'vm.compraActiva')]//div[contains(@class,'body')]//div[contains(@ng-repeat,'compraActiva')]//div[contains(p,'Línea de Crédito Privada')]";
    final String SELECTOR_INPUT_FORMULARIO=".//div[label='%s']";
    final String GET_ELEMENT_FROM_CARRO="//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-class,'vm.compraActiva')]//div[contains(@class,'body')]//div[contains(@ng-repeat,'compraActiva')]//div[contains(p,'%s')]";
    final String GET_ERROR_MESSAGE_OF_INPUT_FROM_INPUT=".//small[contains(@class,'invalid') and not(contains(@class,'ng-hide'))]";
    final String GET_ERROR_MESSAGE_OF_INPUT=".//small[contains(@class,'invalid') and not(contains(@class,'ng-hide')) and contains(text(),'%s')]";

    @FindBy(xpath = "//a[contains(@ng-click,'vm.asideFacilitador')]")
    private WebElement linkFacilitadoresYPreferenciasDeAtencion;

    @FindBy(xpath = "//div[contains(@ng-if,'vm.showCarro')]//a[contains(text(),'Continuar')]")
    private WebElement botonContinuarAPresentacionDelProducto;

    @FindBy(xpath = "//cdn-venta-taller-directive[contains(@on-agregar,'agregarProductoCarro')]//div[contains(@ng-repeat,'listadoFamilias') and .//div[contains(h5,'')]]")
    private List<WebElement> familias;

    @FindBy(xpath = "//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-class,'vm.compraActiva')]")
    private WebElement carroCompra;

    /*
    * Funcionalidades de la pagina
    * */

    public void pruebas(DataTable dataTable){
        log.debug("----------Pruebas------------");
        waitUntilEscritorioComercialIsLoaded();
        String nombreFamilia="Canales Remotos";
        expanderFamilia(nombreFamilia);
        seleccionoElProducto("Banconexion");
        waitUntilEscritorioComercialIsLoaded();
        ingresarValoresAlProducto(dataTable);
        waitUntilEscritorioComercialIsLoaded();

    }

    public void clickAsociarLimites(){
        if(familiaElement!=null) familiaElement.findElement(By.xpath(".//article[.//span[contains(text(),'Asociar límites')]]//div[contains(@class,'actions')]/a[ .//i[contains(@class,'arrow-right')]]")).click();
    }

    public boolean isFamiliaVisible(String familia){
        waitUntilEscritorioComercialIsLoaded();
        return familias.stream().anyMatch((f) -> f.getText().contains(familia));
    }

    public void clickApoderados(){
        if(familiaElement!=null) familiaElement.findElement(By.xpath(".//article[.//span[contains(text(),'Apoderados')]]//div[contains(@class,'actions')]/a[ .//i[contains(@class,'arrow-right')]]")).click();
    }

    public void expanderFamilia(String nombreFamilia){
        this.familia=nombreFamilia;
        waitUntilEscritorioComercialIsLoaded();
        WebElement familia = getFamiliaElement(nombreFamilia).findElement(By.xpath(".//div[contains(@class,'actions')]/a[contains(@ng-click,'productos')]"));
        waitForElementToAppear(familia);
        familia.click();
    }

    public void seleccionoElProducto(String nombreProducto){
        WebElement selectProducto= familiaElement.findElement(By.xpath(".//cdn-detalle-familia-directive//div[label[contains(text(),'Producto')]]"));
        seleccionarValorEnSelect(selectProducto, nombreProducto);
        waitUntilEscritorioComercialIsLoaded();
    }

    public void ingresarValoresAlProducto(DataTable datos){
        List<Map<String,String>> tablaProducto = datos.asMaps();
        for (Map<String, String> fila :tablaProducto){
            ingresarValorEnInput(fila.get("clave"), fila.get("valor"));
        }
    }

    public void clickLinkFacilitadoresYPreferenciasDeAtencion(){
        waitForElementToAppear(linkFacilitadoresYPreferenciasDeAtencion);
        linkFacilitadoresYPreferenciasDeAtencion.click();
    }

    public void continuarAPresentacionDelProducto(){
        waitUntilEscritorioComercialIsLoaded();
        botonContinuarAPresentacionDelProducto.click();
    }

    public String obtenerMensajeDeErrorDelInput(String mensaje){
        waitUntilEscritorioComercialIsLoaded();
        return familiaElement.findElement(By.xpath(String.format(GET_ERROR_MESSAGE_OF_INPUT, mensaje))).getText();
    }

    public Boolean existeMensajeDeErrorDelInput(String mensaje){
        waitUntilEscritorioComercialIsLoaded();
        return isVisible(familiaElement.findElement(By.xpath(String.format(GET_ERROR_MESSAGE_OF_INPUT, mensaje))));
    }

    public String obtenerValorDeInput(String campo){
        waitUntilEscritorioComercialIsLoaded();
        return familiaElement.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,campo))).getText();
    }

    public String obtenerEmisionCartolaText(){
        waitUntilEscritorioComercialIsLoaded();
        return familiaElement.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,"Emisión Cartola"))).getText();
    }

    public void ingresarMontoASolicitar(int monto){
        ingresarValorEnInput("Monto a Solicitar ($)", monto+"");
    }

    public void ingresarSpread(String porcentaje){
        ingresarValorEnInput("Spread(%)", porcentaje);
    }

    public String getMensajeDeErrorFromInput(String campo){
        waitUntilEscritorioComercialIsLoaded();
        WebElement input = familiaElement.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,campo)));
        return input.findElement(By.xpath(GET_ERROR_MESSAGE_OF_INPUT_FROM_INPUT)).getText();
    }

    public Boolean contieneErrorElInput(String campo){
        waitUntilEscritorioComercialIsLoaded();
        WebElement input = familiaElement.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,campo)));
        try {
            return input.findElement(By.xpath(GET_ERROR_MESSAGE_OF_INPUT_FROM_INPUT)).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public String getValorSpread(){
        waitUntilEscritorioComercialIsLoaded();
        return getValorDelCampo("Spread (%)");
    }


    /*
    * Utilidades
    * */



    public String getValorDelCampo(String campo){
        waitUntilEscritorioComercialIsLoaded();
        WebElement input = familiaElement.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,campo)));
        return input.findElement(By.xpath(".//input")).getAttribute("value");
    }


    public WebElement getFamiliaElement(String nombreFamilia){
        for (WebElement f: familias) {
            if(f.findElement(By.xpath(".//h5[contains(@class,'text-color-11')]"))
                    .getText().trim().equals(nombreFamilia)) {
                familiaElement = f;
                return f;
            }
        }
        return null;
    }

    public void clickAgregarAOportunidad(){
        waitUntilEscritorioComercialIsLoaded();
        familiaElement.findElement(By.xpath(botonAgregarAOportunidad)).click();
    }

    public void ingresarValorEnInput(String campo, String valor){
        WebElement input = familiaElement.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,campo)));
        waitUntilEscritorioComercialIsLoaded();
        if(input.findElement(By.xpath(".//input")).getAttribute("class").contains("select")){
            seleccionarValorEnSelect(input, valor);
        }else{
            input.findElement(By.xpath(".//input")).sendKeys(valor);
        }
    }

    public void limpiarValorEnInput(String campo){
        WebElement input = familiaElement.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,campo)));
        input.findElement(By.xpath(".//input")).clear();
    }

    public List<String> getValoresInput(String campo){
        WebElement input = familiaElement.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,campo)));
        return getTextFromSelectValues(input);
    }

    public boolean existeProductoEnElCarro(String nombreProducto){
        List<WebElement> productos = carroCompra.findElements(By.xpath(".//div[contains(@ng-repeat,'listProductos')]"));
        return productos.stream().anyMatch((f) -> f.getText().contains(familia));
    }

    public String getSeleccionDegravamen(String campo){
        WebElement input = familiaElement.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,campo)));
        WebElement checkSeleccion = input.findElement(By.xpath(".//label[contains(@class,'bch-custom-check')]//input[@value=1]"));
        if(isVisible(checkSeleccion)){
            return input.findElement(By.xpath(".//label[contains(@class,'bch-custom-check')]//input[@value=1]")).getText();
        }
        return null;
    }

    // Manejador de select

    public void clickFlechaSelect(WebElement element){
        element.findElement(By.xpath(".//i[contains(@ng-click,'event')]")).click();
    }

    public void clickFlechaSelect(String elementXpath){
        WebElement btnDesplegarLista = getDriver().findElement(By.xpath(String.format(SELECT_BOTON_DESPLEGAR_LISTA, elementXpath)));
        log.debug("Se despliega la lista para el elemento: "+btnDesplegarLista);
        click(btnDesplegarLista);
    }

    public void click(WebElement element){
        //System.out.println("El boton a hacer click es: " + element);
        element.click();
    }

    public List<String> getTextFromSelectValues(String elementXpath){
        ArrayList<String> valores = new ArrayList<>();
        clickFlechaSelect(elementXpath);//se expande
        List<WebElement> vals = getDriver().findElements(By.xpath(elementXpath+"//li//a[contains(span,'')]"));
        for (WebElement el : vals) {
            if(!el.getText().equals("")) valores.add(el.getText());
        }
        return valores;
    }

    public List<String> getTextFromSelectValues(WebElement elementXpath){
        ArrayList<String> valores = new ArrayList<>();
        clickFlechaSelect(elementXpath);//se expande
        List<WebElement> vals = elementXpath.findElements((By.xpath(elementXpath+".//li//a[contains(span,'')]")));
        for (WebElement el : vals) {
            if(!el.getText().equals("")) valores.add(el.getText());
        }
        return valores;
    }

    public ArrayList<WebElement> getWebElememtFromSelect(String elementXpath){
        ArrayList<WebElement> valores = new ArrayList<>();
        clickFlechaSelect(elementXpath);//se expande
        List<WebElement> vals = getDriver().findElements(By.xpath(elementXpath+"//li//a[contains(span,'')]"));
        for (WebElement el : vals) {
            if(!el.getText().equals("")) valores.add(el);
        }
        clickFlechaSelect(elementXpath);
        return valores;
    }

    public void seleccionarValorEnSelect(WebElement element, String valor){
        clickFlechaSelect(element);
        switch (valor){
            case "FIRST": element.findElement(By.xpath(".//li//a[1]")).click();
                break;
            case "RANDOM": element.findElement(By.xpath(".//li//a[1]")).click();
                break;
            default: element.findElement(By.xpath(String.format("//li//a[contains(span,'%s')]",valor))).click();
                break;
        }
    }

    public void seleccionarValorEnSelect(String elementXpath, String valor){
        clickFlechaSelect(elementXpath);
        WebElement valorSelect;
        if(valor.equals("FIRST")){
            valorSelect = getElementFrom(By.xpath(String.format(SELECT_FIRST_ELEMENT,elementXpath)));
        }else if(valor.equals("RANDOM")){
            // TODO: 2019-07-18 pgtoopx Validar numero dentro de la cantidad de opciones que tiene
            valorSelect=getElementFrom(By.xpath(String.format(SELECT_RAMDOM_ELEMENT,elementXpath,"1")));
        }else{
            valorSelect = getElementFrom(By.xpath(String.format(SELECT_ELEMENT,elementXpath,valor)));
        }
        log.debug("El xpath con el valor es: " +valorSelect);
        click(valorSelect);
    }

}
