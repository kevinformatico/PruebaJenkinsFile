package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

import java.util.List;

public class ApoderadosPO extends BasePage {

    Logger log = Logger.getLogger(ApoderadosPO.class);
    final String FORM_APODERADOS="//div[contains(@class,'modal-body')]//div[contains(@ng-if,'vm.modoEdicion')]/form";
    final String SELECTOR_REPRESENTES_LIST=FORM_APODERADOS+"/div[contains(@ng-repeat,'representante')]";
    final String SELECTOR_REPRESENTES_BY_INDEX=FORM_APODERADOS+"/div[contains(@ng-repeat,'representante')][%s]";

    final String SELECTOR_INPUT_FORMULARIO=".//div[label='%s']";

    public ApoderadosPO(){
        super(DriverFactory.getDriver());
    }

    @FindBy(xpath = "//div[contains(@class,'modal-header')]//h3[contains(@class,'modal-title')]")
    private WebElement titulo;

    @FindBy(xpath = "//div[contains(@class,'caja-resumen-inicio')]//div[p[contains(text(),'Tipo de producto')]]/p[2]")
    private WebElement tipoDeProducto;
    
    @FindBy(xpath = "//div[contains(@class,'modal-body')]//div[contains(@ng-if,'vm.modoEdicion')]/form") 
    private WebElement formularioApoderados;

    @FindBy(xpath = "//div[contains(@class,'modal-footer')]//button['Guardar']")
    private WebElement btnGuardar;
    
    @FindBy(xpath = "//button[contains(@class,'close-modal')]") 
    private WebElement btnCerrarModal;

    public void cerrar(){
        btnCerrarModal.click();
    }

    public void ingresarApoderado(String rut){
        waitUntilEscritorioComercialIsLoaded();
        WebElement contenedorApoderado = formularioApoderados.findElement(By.xpath("./div[contains(@ng-repeat,'representante')]"));
        WebElement inputRut = contenedorApoderado.findElement(By.xpath(".//div[contains(@class,'form-group')]/input"));
        inputRut.sendKeys(rut);
        waitUntilEscritorioComercialIsLoaded();
        WebElement lupa = contenedorApoderado.findElement(By.xpath(".//button[@id='buscar_0']"));
        lupa.click();
        waitUntilEscritorioComercialIsLoaded();
        WebElement direccion = contenedorApoderado.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,"Dirección")));
        waitForElementToAppear(direccion);
        seleccionarValorEnSelect(direccion, "FIRST");
        WebElement telefono = contenedorApoderado.findElement(By.xpath(String.format(SELECTOR_INPUT_FORMULARIO,"Teléfono")));
        waitForElementToAppear(telefono);
        seleccionarValorEnSelect(telefono, "FIRST");
        waitUntilEscritorioComercialIsLoaded();
        btnGuardar.click();
    }


    public void clickFlechaSelect(WebElement select){
        final String SELECT_BOTON_DESPLEGAR_LISTA=".//i[contains(@ng-click,'event')]";
        log.debug("Desplegando select: "+ select);
        select.findElement(By.xpath(SELECT_BOTON_DESPLEGAR_LISTA)).click();
    }

    public void seleccionarValorEnSelect(WebElement select, String valor){
        final String SELECT_ELEMENT=".//li//a[contains(span,'%s')]";
        final String SELECT_FIRST_ELEMENT=".//li//a[1]";
        final String SELECT_RAMDOM_ELEMENT=".//li//a[%s]";
        clickFlechaSelect(select);
        if(valor.equals("FIRST")){
            select.findElement(By.xpath(SELECT_FIRST_ELEMENT)).click();
        }else if(valor.equals("RANDOM")){
            // TODO: 2019-07-18 pgtoopx Validar numero dentro de la cantidad de opciones que tiene
            select.findElement(By.xpath(String.format(SELECT_RAMDOM_ELEMENT,"1"))).click();
        }else{
            select.findElement(By.xpath(String.format(SELECT_ELEMENT,valor))).click();
        }
    }

}
