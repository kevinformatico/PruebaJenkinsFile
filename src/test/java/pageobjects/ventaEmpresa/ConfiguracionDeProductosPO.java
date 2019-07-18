package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfiguracionDeProductosPO extends BasePage {

    public ConfiguracionDeProductosPO(){
        super(DriverFactory.getDriver());
    }

    String botonAgregarAOportunidad = "//button[contains(@class,'agregar-producto') and contains(text(),'Agregar a oportunidad')]";
    String familia;


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



    @FindBy(xpath = "//a[contains(@ng-click,'vm.asideFacilitador')]")
    private WebElement linkFacilitadoresYPreferenciasDeAtencion;

    @FindBy(xpath = "//div[contains(@ng-if,'vm.showCarro')]//a[contains(text(),'Continuar')]")
    private WebElement botonContinuarAPresentacionDelProducto;


    /*
    * Funcionalidades de la pagina
    * */

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


    /*
    * Utilidades
    * */

    public String selectorFamilia(String nombreFamilia){
        return String.format(SELECCIONAR_FAMILIA_XPATH, nombreFamilia);
    }

    public void clickAgregarAOportunidad(){
        getDriver().findElement(By.xpath(selectorFamilia(familia)+botonAgregarAOportunidad)).click();
    }

    public void ingresarValorEnInput(String campo, String valor){
        String inputDiv=String.format(SELECTOR_INPUT_FORMULARIO,selectorFamilia(familia),campo);
        if(getElementFrom(By.xpath(inputDiv+"//input")).getAttribute("class").contains("select")){
            seleccionarValorEnSelect(inputDiv, valor);
        }else{
            getElementFrom(By.xpath(inputDiv+"//input")).sendKeys(valor);
        }
    }

    public boolean existeProductoEnElCarro(String nombreProducto){
        WebElement producto = getDriver().findElement(By.xpath(String.format(GET_ELEMENT_FROM_CARRO, nombreProducto)));
        return isVisible(producto);
    }

    public String addSpaceToFormText(String valor){
        return " "+valor.trim()+" ";
    }


    // Manejador de select

    public void clickFlechaSelect(String elementXpath){
        WebElement btnDesplegarLista = getDriver().findElement(By.xpath(String.format(SELECT_BOTON_DESPLEGAR_LISTA, elementXpath)));
        click(btnDesplegarLista);
    }

    public void click(WebElement element){
        //System.out.println("El boton a hacer click es: " + element);
        element.click();
    }

    public ArrayList<String> getTextFromSelectValues(String elementXpath){
        ArrayList<String> valores = new ArrayList<>();
        clickFlechaSelect(elementXpath);//se expande
        List<WebElement> vals = getDriver().findElements(By.xpath(elementXpath+"//li//a[contains(span,'')]"));
        for (WebElement el : vals) {
            if(!el.getText().equals("")) valores.add(el.getText());
        }
        clickFlechaSelect(elementXpath);
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
        WebElement valorSelect;
        if(valor.equals("FIRST")){
            valorSelect = getElementFrom(By.xpath(String.format(SELECT_FIRST_ELEMENT,elementXpath)));
        }else if(valor.equals("RANDOM")){
            // TODO: 2019-07-18 pgtoopx Validar numero dentro de la cantidad de opciones que tiene
            valorSelect=getElementFrom(By.xpath(String.format(SELECT_RAMDOM_ELEMENT,elementXpath,"1")));
        }else{
            valorSelect = getElementFrom(By.xpath(String.format(SELECT_ELEMENT,elementXpath,valor)));
        }
        click(valorSelect);
    }



}
