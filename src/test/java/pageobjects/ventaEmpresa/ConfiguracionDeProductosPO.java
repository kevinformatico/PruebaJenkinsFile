package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;
import support.ui.elements.*;

import java.util.List;
import java.util.stream.Collectors;

public class ConfiguracionDeProductosPO extends BasePage {

    private Logger log = LogManager.getLogger(ConfiguracionDeProductosPO.class);

    public ConfiguracionDeProductosPO(){
        super(DriverFactory.getDriver());
    }

    final String BOTON_AGREGAR_A_OPORTUNIDAD = ".//button[contains(@class,'agregar-producto') and contains(text(),'Agregar a oportunidad')]";
    WebElement familiaElement;
    String nombreProducto;
    String familia;

    final String SELECTOR_PRODUCTO_POR_FAMILIA =".//cdn-detalle-familia-directive//div[label[contains(text(),'Producto')]]";

    final String SELECTOR_ASOCIAR_LIMITES="//article[.//span[contains(text(),'Asociar límites')]]//div[contains(@class,'actions')]/a[ .//i[contains(@class,'arrow-right')]]";
    final String SELECTOR_APODERADOS="//article[.//span[contains(text(),'Apoderados')]]//div[contains(@class,'actions')]/a[ .//i[contains(@class,'arrow-right')]]";
    final String SELECT_ELEMENT="%s//li//a[contains(span,'%s')]";
    final String SELECT_FIRST_ELEMENT="%s//li//a[1]";
    final String SELECT_RAMDOM_ELEMENT="%s//li//a[%s]";
    final String SELECT_BOTON_DESPLEGAR_LISTA="%s//i[contains(@ng-click,'event')]";
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

    @FindBy(xpath = "//section[contains(@class,'bch-mensaje-empresas') and not(contains(@class,'ng-hide'))]")
    private List<WebElement> bchMensajesEmpresas;

    @FindBy(xpath = "//section[contains(@class,'bch-mensaje-empresas') and not(contains(@class,'ng-hide')) and contains(@class,'warning')]")
    private List<WebElement> warningBchMensajesEmpresa;

    @FindBy(xpath = "//div[contains(label,'Indicador RUT cheques')]//div[contains(@class,'form-group')]")
    private WebElement checkboxIndicadorRutCheques;

    public void pruebas() {
        log.debug("----------Pruebas------------");
        waitUntilEscritorioComercialIsLoaded();
        String nombreFamilia = "Cuentas";
        expanderFamilia(nombreFamilia);
        seleccionoElProducto("Cta. Cte. Normal - PYME");
        //ingresarValorAlProducto("Plan Tarifario", "ARMADA1");
        //ingresarValorAlProducto("Aumento programado de cupo", "No");
        waitUntilEscritorioComercialIsLoaded();
        RadioButton radioButton = new RadioButton(checkboxIndicadorRutCheques);
        List<WebElement> options = radioButton.getOptions();

        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        String classname = "bch-custom-check radiobutton col-sm-3 pl-0";
        //String valor = js.executeScript("return angular.element(document.getElementsByClassName('"+classname+"')[0],this)[0].control.checked").toString();
        WebElement element = radioButton.getWrappedElement().findElement(By.tagName("input"));
        log.debug(element);
        String valor = js.executeScript("return angular.element(arguments[0],this)[0]", element).toString();
        log.debug("Valor : "+valor);

        log.debug(options.stream().map(WebElement::getText).collect(Collectors.toList()));
        log.debug(radioButton.getValue());
        radioButton.setValue("No");
        waitUntilEscritorioComercialIsLoaded();
        waitFor(4);
        log.debug(radioButton.getValue());
        waitUntilEscritorioComercialIsLoaded();
        String valor2 = js.executeScript("return angular.element(document.getElementsByClassName('"+classname+"')[0],this)[0].control.checked").toString();

        log.debug("Valor 2:"+valor2);

    }

    public void clickAsociarLimites(){
        if(familiaElement!=null) familiaElement.findElement(By.xpath(".//article[.//span[contains(text(),'Asociar límites')]]//div[contains(@class,'actions')]/a[ .//i[contains(@class,'arrow-right')]]")).click();
        //TODO: generar throw para cuando la familia no este expandida
    }

    public void clickApoderados(){
        if(familiaElement!=null) familiaElement.findElement(By.xpath(".//article[.//span[contains(text(),'Apoderados')]]//div[contains(@class,'actions')]/a[ .//i[contains(@class,'arrow-right')]]")).click();
        //TODO: generar throw para cuando la familia no este expandida
    }

    public void clickLinkFacilitadoresYPreferenciasDeAtencion(){
        waitForElementToAppear(linkFacilitadoresYPreferenciasDeAtencion);
        linkFacilitadoresYPreferenciasDeAtencion.click();
    }

    public void continuarAPresentacionDelProducto(){
        waitUntilEscritorioComercialIsLoaded();
        botonContinuarAPresentacionDelProducto.click();
    }

    public void clickAgregarAOportunidad(){
        waitUntilEscritorioComercialIsLoaded();
        familiaElement.findElement(By.xpath(BOTON_AGREGAR_A_OPORTUNIDAD)).click();
    }

    public boolean isFamiliaVisible(String familia){
        waitUntilEscritorioComercialIsLoaded();
        return familias.stream().anyMatch((f) -> f.getText().contains(familia));
    }

    public void expanderFamilia(String nombreFamilia){
        waitUntilEscritorioComercialIsLoaded();
        this.familia=nombreFamilia;
        getFamiliaElement(nombreFamilia).findElement(By.xpath(".//div[contains(@class,'actions')]/a[contains(@ng-click,'productos')]")).click();
    }

    public void seleccionoElProducto(String nombreProducto){
        this.nombreProducto = nombreProducto;
        seleccionarValorEnSelect(new Select(familiaElement.findElement(By.xpath(SELECTOR_PRODUCTO_POR_FAMILIA))), nombreProducto);
        waitUntilEscritorioComercialIsLoaded();
    }

    public boolean isProductoVisible(String nombreProducto){
        waitUntilEscritorioComercialIsLoaded();
        Select s = new Select(familiaElement.findElement(By.xpath(SELECTOR_PRODUCTO_POR_FAMILIA)));
        return s.getOptions().stream().anyMatch((e)-> e.getText().equals(nombreProducto));
    }


    public String getSelectedProduct(){
        return this.nombreProducto;
    }

    public void ingresarValoresAlProducto(DataTable datos){
        datos.asMaps().forEach((e)-> {
            ingresarValorEnInput(getInputFromCampo(e.get("clave")), e.get("valor"));
        });
    }

    public void ingresarValorAlProducto(String campo, String valor){
        ingresarValorEnInput(getInputFromCampo(campo), valor);
    }

    //TODO: errores mejorar

    public List<String> getMensajesDeErrorFromInput(String campo){
        waitUntilEscritorioComercialIsLoaded();
        return getInputFromCampo(campo).getVisiblesError().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public Boolean contieneErrorElInput(String campo){
        waitUntilEscritorioComercialIsLoaded();
        return getInputFromCampo(campo).isError();
    }

    //Manejadores especificos para formulario de la familia

    public String getEmisionCartolaText(){
        waitUntilEscritorioComercialIsLoaded();
        return getInputFromCampo("Emisión Cartola").getWrappedElement().findElement(By.tagName("p")).getText();
    }

    public String getValorSpread(){
        String campo= "Spread (%)";
        getInputFromCampo(campo).getWrappedElement().click();
        waitUntilEscritorioComercialIsLoaded();
        return getValorDelCampo(campo);
    }

    public String getValorSeguroDegravamen(){
        String campo= "Desgravamen Línea de Crédito";
        RadioButton radioButton = new RadioButton(getInputFromCampo(campo).getWrappedElement());
        return radioButton.getValue();

    }

    public String getValorDelCampo(String campo){
        return getInputFromCampo(campo).getValue();
    }

    public List<String> getValoresDelSelect(String campo){
        return new Select(getInputFromCampo(campo)).getOptions()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getMensajesBchEmpresas(){
        waitUntilEscritorioComercialIsLoaded();
        return bchMensajesEmpresas.stream()
                .map(BchMensajeEmpresa::new)
                .map(BchMensajeEmpresa::getTitulo)
                .collect(Collectors.toList());
    }

    public List<String> getMensajesBchEmpresasSinSubtitulos(){
        waitUntilEscritorioComercialIsLoaded();
        return bchMensajesEmpresas.stream()
                .map(BchMensajeEmpresa::new)
                .filter((m)-> !m.isContainsSubtitulo())
                .map(BchMensajeEmpresa::getTitulo)
                .collect(Collectors.toList());
    }

    public List<String> getWarningMensajesBchEmpresas(){
        waitUntilEscritorioComercialIsLoaded();
        return bchMensajesEmpresas.stream()
                .map(BchMensajeEmpresa::new)
                .filter(BchMensajeEmpresa::isWarning)
                .map(BchMensajeEmpresa::getTitulo)
                .collect(Collectors.toList());
    }

    public BchMensajeEmpresa getBchMensajesEmpresasWithTitulo(String titulo){
        return bchMensajesEmpresas.stream()
                .map(CustomBchMensajeEmpresa::new)
                .filter((m)-> m.getTitulo().equals(titulo))
                .limit(1)
                .collect(Collectors.toList()).get(0);
    }


    public void ingresarMontoASolicitar(String monto){
        ingresarValorEnInput(getInputFromCampo("Monto a Solicitar ($)"), monto);
    }

    public void ingresarSpread(String porcentaje){
        String campo ="Spread (%)";
        ingresarValorEnInput(getInputFromCampo(campo), porcentaje);
        getInputFromCampo(campo).getInputTag().sendKeys(Keys.TAB);
    }

    public boolean existeProductoEnElCarro(String nombreProducto){
        List<WebElement> productos = carroCompra.findElements(By.xpath(".//div[contains(@ng-repeat,'listProductos')]"));
        return productos.stream().anyMatch((f) -> f.getText().contains(familia));
    }

    /*
    * Utilidades
    * */

    private Input getInputFromCampo(String campo){
        return new Input(familiaElement.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,campo))));
    }

    private WebElement getFamiliaElement(String nombreFamilia){
        waitUntilEscritorioComercialIsLoaded();
        try{
            this.familiaElement= familias.stream().filter((f) -> f.findElement(By.xpath(".//h5[contains(@class,'text-color-11')]")).getText().trim().equals(nombreFamilia) )
                .limit(1)
                .collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e){
            throw new NoSuchElementException("No se encuentra la familia "+ nombreFamilia);
        }
        return this.familiaElement;
    }

    private void ingresarValorEnInput(Input input, String valor){
        // TODO: 2019-08-22 pgtoopx agregar todos los elementos que se utilizan en la pagina
        waitUntilEscritorioComercialIsLoaded();
        if(input.getWrappedElement().findElement(By.tagName("input")).getAttribute("class").contains("select")){
            seleccionarValorEnSelect(new Select(input.getWrappedElement()), valor);
        }else{
            input.setValue(valor);
        }
    }

    public void limpiarValorEnInput(String campo){
        waitUntilEscritorioComercialIsLoaded();
        getInputFromCampo(campo).getWrappedElement().click();
        waitUntilEscritorioComercialIsLoaded();
        getInputFromCampo(campo).clear();
    }

    private void seleccionarValorEnSelect(Select select, String valor){
        switch (valor){
            case "FIRST": select.selectFirstValue();
                break;
            case "RANDOM": select.selectByRandom();
                break;
            default: select.selectByVisibleText(valor);
                break;
        }
    }



}
