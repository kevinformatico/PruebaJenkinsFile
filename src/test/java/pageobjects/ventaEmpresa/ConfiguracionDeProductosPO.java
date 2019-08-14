package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfiguracionDeProductosPO extends BasePage {

    private Logger log = LogManager.getLogger(ConfiguracionDeProductosPO.class);

    public ConfiguracionDeProductosPO(){
        super(DriverFactory.getDriver());
    }

    String botonAgregarAOportunidad = "//button[contains(@class,'agregar-producto') and contains(text(),'Agregar a oportunidad')]";
    String familia;

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
    final String SELECTOR_INPUT_FORMULARIO="%s//div[label='%s']";
    final String GET_ELEMENT_FROM_CARRO="//div[contains(@role,'ROLE_VTAPYMECONTR_EJECUTAR')]/div[contains(@ng-class,'vm.compraActiva')]//div[contains(@class,'body')]//div[contains(@ng-repeat,'compraActiva')]//div[contains(p,'%s')]";
    final String GET_ERROR_MESSAGE_OF_INPUT_FROM_INPUT="%s//small[contains(@class,'invalid') and not(contains(@class,'ng-hide'))]";
    final String GET_ERROR_MESSAGE_OF_INPUT="//small[contains(@class,'invalid') and not(contains(@class,'ng-hide')) and contains(text(),'%s')]";

    @FindBy(xpath = "//a[contains(@ng-click,'vm.asideFacilitador')]")
    private WebElement linkFacilitadoresYPreferenciasDeAtencion;

    @FindBy(xpath = "//div[contains(@ng-if,'vm.showCarro')]//a[contains(text(),'Continuar')]")
    private WebElement botonContinuarAPresentacionDelProducto;

    @FindBy(xpath = "")
    private WebElement componenteFamilias;


    /*
    * Funcionalidades de la pagina
    * */

    public void clickAsociarLimites(){
        getElementFrom(By.xpath(selectorFamilia(familia)+SELECTOR_ASOCIAR_LIMITES)).click();
    }

    public void clickApoderados(){
        getElementFrom(By.xpath(selectorFamilia(familia)+SELECTOR_APODERADOS)).click();
    }

    public void expanderFamilia(String nombreFamilia){
        this.familia=nombreFamilia;
        waitUntilEscritorioComercialIsLoaded();
        WebElement familia = getElementFrom(By.xpath(String.format(XPATH_PARA_FLECHA_DESPLEGAR_FAMILIA,selectorFamilia(nombreFamilia))));
        familia.click();
    }

    public void seleccionoElProducto(String nombreProducto){
        String selectProductoXpath = String.format(SELECTOR_PRODUCTO_DE_FAMILIA,selectorFamilia(this.familia));
        seleccionarValorEnSelect(selectProductoXpath, nombreProducto);
        waitUntilEscritorioComercialIsLoaded();
    }

    public void ingresarValoresAlProducto(DataTable datos){
        List<Map<String,String>> tablaProducto = datos.asMaps();
        log.debug("---Ingresando Valores a la tabla---");
        for (Map<String, String> fila :tablaProducto){
            log.debug(fila.get("clave")+": "+ fila.get("valor"));
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
        WebElement smallMessageError= getElementFrom(By.xpath(String.format(GET_ERROR_MESSAGE_OF_INPUT,mensaje)));
        return smallMessageError.getText();
    }

    public Boolean existeMensajeDeErrorDelInput(String mensaje){
        waitUntilEscritorioComercialIsLoaded();
        WebElement smallMessageError= getElementFrom(By.xpath(String.format(GET_ERROR_MESSAGE_OF_INPUT,mensaje)));
        return isVisible(smallMessageError);
    }

    public String obtenerValorDeInput(String campo){
        String inputDiv=String.format(SELECTOR_INPUT_FORMULARIO,selectorFamilia(familia),campo);
        WebElement input = getElementFrom(By.xpath(inputDiv));
        return input.getText();
    }

    public String obtenerEmisionCartolaText(){
        String inputDiv=String.format(SELECTOR_INPUT_FORMULARIO,selectorFamilia(familia),"Emisión Cartola");
        return getElementFrom(By.xpath(inputDiv+"//p")).getText();
    }

    public void ingresarMontoASolicitar(int monto){
        ingresarValorEnInput("Monto a Solicitar ($)", monto+"");
    }

    public void ingresarSpread(String porcentaje){
        ingresarValorEnInput("Spread(%)", porcentaje);
    }

    public String getMensajeDeErrorFromInput(String campo){
        String inputDiv=String.format(SELECTOR_INPUT_FORMULARIO,selectorFamilia(familia),campo);
        WebElement mensajeError=getElementFrom(By.xpath(String.format(GET_ERROR_MESSAGE_OF_INPUT_FROM_INPUT,inputDiv)));
        return mensajeError.getText();
    }

    public Boolean contieneErrorElInput(String campo){
        waitUntilEscritorioComercialIsLoaded();
        String inputDiv=String.format(SELECTOR_INPUT_FORMULARIO,selectorFamilia(familia),campo);
        try {
            getDriver().findElement(By.xpath(String.format(GET_ERROR_MESSAGE_OF_INPUT_FROM_INPUT,inputDiv)));
            return true;
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
        String inputDiv=String.format(SELECTOR_INPUT_FORMULARIO,selectorFamilia(familia),campo);
        return getDriver().findElement(By.xpath(inputDiv+"//input")).getAttribute("value");
    }
    public String selectorFamilia(String nombreFamilia){
        return String.format(SELECCIONAR_FAMILIA_XPATH, nombreFamilia);
    }

    public void clickAgregarAOportunidad(){
        waitUntilEscritorioComercialIsLoaded();
        getElementFrom(By.xpath(selectorFamilia(familia)+botonAgregarAOportunidad)).click();
    }

    public void ingresarValorEnInput(String campo, String valor){
        String inputDiv=String.format(SELECTOR_INPUT_FORMULARIO,selectorFamilia(familia),campo);
        waitUntilEscritorioComercialIsLoaded();
        log.debug("Xpath input: "+inputDiv);
        if(getElementFrom(By.xpath(inputDiv+"//input")).getAttribute("class").contains("select")){
            log.debug("el input es select");
            seleccionarValorEnSelect(inputDiv, valor);
        }else{
            log.debug("el input es text");
            getElementFrom(By.xpath(inputDiv+"//input")).sendKeys(valor);
        }
        //log.debug("Se realiza el tab");
        //getElementFrom(By.xpath(inputDiv+"//input")).sendKeys(Keys.TAB);
    }

    public void limpiarValorEnInput(String campo){
        String inputDiv=String.format(SELECTOR_INPUT_FORMULARIO,selectorFamilia(familia),campo);
        getElementFrom(By.xpath(inputDiv+"//input")).clear();
    }

    public List<String> getValoresInput(String campo){
        return getTextFromSelectValues(String.format(SELECTOR_INPUT_FORMULARIO,selectorFamilia(familia),campo));
    }

    public boolean existeProductoEnElCarro(String nombreProducto){
        WebElement producto = getDriver().findElement(By.xpath(String.format(GET_ELEMENT_FROM_CARRO, nombreProducto)));
        return isVisible(producto);
    }

    public String addSpaceToFormText(String valor){
        return " "+valor.trim()+" ";
    }

    public String getSeleccionDegravamen(String campo){
        String inputDiv=String.format(SELECTOR_INPUT_FORMULARIO,selectorFamilia(familia),campo);
        if(isVisible(getDriver().findElement(By.xpath(inputDiv+"//label[contains(@class,'bch-custom-check')]//input[@value=1]")))){
            return getDriver().findElement(By.xpath(inputDiv+"//label[contains(@class,'bch-custom-check')]//input[@value=1]")).getText();
        }
        return null;
    }

    // Manejador de select

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

    public void seleccionarValorEnSelect(String elementXpath, String valor){
        clickFlechaSelect(elementXpath);
        log.debug("El select tiene el xpath: "+elementXpath);
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
