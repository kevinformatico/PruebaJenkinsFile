package pageobjects.ventaEmpresa;

import Managers.driver.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;
import java.util.*;

public class AsociarLimitesPo extends BasePage {

    public AsociarLimitesPo(){
        super(DriverFactory.getDriver());
    }
    Logger log = Logger.getLogger(AsociarLimitesPo.class);
    final String SELECTOR_VALOR_RESUMEN="//div[contains(h3,'Asociar a Límites')]//article[contains(@class,'resume-box')]//ul[contains(li,'%S')]//li[2]";
    final String SELECTOR_TABLA_LIMITES="//article[contains(h5,'Límites disponibles')]//div[contains(@tabla-select,'') and contains(@class,'table')]//table[contains(@class,'tabla-interactiva')]";



    @FindBy(xpath = "//div[contains(@class,'caja-resumen-inicio')]//div[contains(p,'Producto')]//p[2]")
    private WebElement nombreProducto ;

    @FindBy(xpath = "//div[contains(@class,'modal-header')]//h3[contains(@class,'modal-title')]")
    private WebElement titulo;

    @FindBy(xpath = "//div[contains(h3,'Asociar a Límites')]//article[contains(@class,'resume-box')]//ul[contains(li,'Tipo de producto')]//li[2]")
    private WebElement tipoDeProducto;

    @FindBy(xpath = "//div[contains(h3,'Asociar a Límites')]//article[contains(@class,'resume-box')]//ul[contains(li,'Vencimiento operación')]//li[2]")
    private WebElement vencimientoOperacion;

    @FindBy(xpath = "//div[contains(h3,'Asociar a Límites')]//article[contains(@class,'resume-box')]//ul[contains(li,'Monto Nacional (M$)')]//li[2]")
    private WebElement montoNacional;

    @FindBy(xpath = "//div[contains(h3,'Asociar a Límites')]//article[contains(@class,'resume-box')]//ul[contains(li,'Monto USD')]//li[2]")
    private WebElement montoUsd;

    @FindBy(xpath = "//div[contains(h3,'Asociar a Límites')]//article[contains(@class,'resume-box')]//ul[contains(li,'Total Monto Reserva (M$)')]//li[2]")
    private WebElement totalMontoReserva;

    @FindBy(xpath = "//article[contains(h5,'Límites disponibles')]//div[contains(@tabla-select,'') and contains(@class,'table')]//table[contains(@class,'tabla-interactiva')]")
    private WebElement tablaLimites;

    @FindBy(xpath = "//button[contains(@class,'success') and text()='Asociar']")
    private WebElement btnAsociar;

    @FindBy(xpath = "//button[contains(@class,'close-modal')]")
    private WebElement btnCerrarModal;

    public void insertarMontoReservaALimites(String nroLimite, String monto){
        waitUntilEscritorioComercialIsLoaded();
        List<String> limites = getNrosLimites(SELECTOR_TABLA_LIMITES);
        log.debug("Limites: "+  limites);
        int indexLimite = getIndexTablaForNroLimite(limites.get(0), SELECTOR_TABLA_LIMITES);
        log.debug("Index limite 1: " + indexLimite);
        WebElement input =  getDriver().findElement(By.xpath(SELECTOR_TABLA_LIMITES+String.format("/tbody/tr[%s]/td[%s]//input[@type='text']",indexLimite, 6)));
        log.debug(input);
        input.sendKeys(monto);
    }

    public void asociarLimites(){
        waitUntilEscritorioComercialIsLoaded();
        btnAsociar.click();
    }

    public void cerrar(){
        btnCerrarModal.click();
    }
    public String getMontoNacionalLimite(){
        waitUntilEscritorioComercialIsLoaded();
        try {
            return montoNacional.getText();
        }catch (StaleElementReferenceException | NoSuchElementException e){
            return "";
        }
    }

    public String getMontoUSDLimite(){
        waitUntilEscritorioComercialIsLoaded();
        try {
        return montoUsd.getText();
        }catch (StaleElementReferenceException | NoSuchElementException e){
            return "";
        }
    }

    public String getTotalMontoReserva(){
        waitUntilEscritorioComercialIsLoaded();
        log.debug("Total Monto Reserva: "+totalMontoReserva.getText());
        return totalMontoReserva.getText();
    }

    public  List<Map<String, String>> ExtraerDatosTabla(String tabla) {
        waitUntilEscritorioComercialIsLoaded();
        int Row_count = getDriver().findElements(By.xpath(tabla + "/tbody/tr")).size();
        log.debug("Cantidad de filas: " + Row_count);
        int Col_count = getDriver().findElements(By.xpath(tabla + "/tbody/tr[1]/td")).size();
        log.debug("Cantidad de columnas: " + Col_count);
        int Head_count = getDriver().findElements(By.xpath(tabla+ "/thead/tr/th")).size();
        log.debug("Cantidad de columnas en el thead: " + Head_count);
        List<Map<String, String>> datosTabla = new ArrayList<>();
        for(int row=1;row<=Row_count;row++){
            HashMap<String, String> objeto = new HashMap<>();
            for (int col=1; col<=Col_count;col++){
                String nombreColumna = getDriver().findElement(By.xpath(tabla+String.format("/thead/tr/th[%s]",col))).getText();
                String valor = getDriver().findElement(By.xpath(tabla+String.format("/tbody/tr[%s]/td[%s]",row, col))).getText();
                objeto.put(nombreColumna, valor);
            }

            datosTabla.add(objeto);
        }
        log.debug("Datos extraidos de la Tabla: "+ datosTabla);
        return datosTabla;
    }

    public int getIndexTablaForNroLimite(String nroLimite, String tabla){
        waitUntilEscritorioComercialIsLoaded();
        int Row_count = getDriver().findElements(By.xpath(tabla + "/tbody/tr")).size();
        log.debug("Cantidad de filas: " + Row_count);
        for(int row=1;row<=Row_count;row++){
             if(getDriver().findElement(By.xpath(tabla+String.format("/tbody/tr[%s]/td[%s]",row, 1))).getText().equals(nroLimite)){
                 return row;
             }
        }
        return 0;
    }

    public ArrayList<String> getNrosLimites(String tabla){
        waitUntilEscritorioComercialIsLoaded();
        int Row_count = getDriver().findElements(By.xpath(tabla + "/tbody/tr")).size();
        log.debug("Cantidad de filas: " + Row_count);
        ArrayList<String> nroLimites = new ArrayList<>();
        for(int row=1;row<=Row_count;row++){
            nroLimites.add(getDriver().findElement(By.xpath(tabla+String.format("/tbody/tr[%s]/td[%s]",row, 1))).getText());
        }
        return nroLimites;
    }

}
