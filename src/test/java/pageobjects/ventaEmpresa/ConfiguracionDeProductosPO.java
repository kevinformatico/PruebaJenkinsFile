package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;
import support.ui.elements.Input;
import support.ui.elements.Select;

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

    public void pruebas(DataTable dataTable){
        log.debug("----------Pruebas------------");
        waitUntilEscritorioComercialIsLoaded();
        String nombreFamilia="Líneas";
        expanderFamilia(nombreFamilia);
        log.debug("La familia se expandio");
        seleccionoElProducto("Línea de Crédito Automática Personas");
        log.debug("Se selecciono el producto");
        ingresarValoresAlProducto(dataTable);
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

    public String getSelectedProduct(){
        return this.nombreProducto;
    }

    public void ingresarValoresAlProducto(DataTable datos){
        datos.asMaps().forEach((e)-> {
            ingresarValorEnInput(getInputFromCampo(e.get("clave")), e.get("valor"));
        });
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
        return getInputFromCampo("Emisión Cartola").getWrappedElement().getText();
    }

    public String getValorSpread(){
        waitUntilEscritorioComercialIsLoaded();
        return getValorDelCampo("Spread (%)");
    }

    public String getValorDelCampo(String campo){
        return getInputFromCampo(campo).getValue();
    }

    public void ingresarMontoASolicitar(int monto){
        ingresarValorEnInput(getInputFromCampo("Monto a Solicitar ($)"), monto+"");
    }

    public void ingresarSpread(String porcentaje){
        ingresarValorEnInput(getInputFromCampo("Spread(%)"), porcentaje);
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
        this.familiaElement= familias.stream().filter((f) -> f.findElement(By.xpath(".//h5[contains(@class,'text-color-11')]")).getText().trim().equals(nombreFamilia) )
                .limit(1)
                .collect(Collectors.toList()).get(0);
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
