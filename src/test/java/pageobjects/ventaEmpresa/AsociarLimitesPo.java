package pageobjects.ventaEmpresa;

import Generics.ManejadorTablaFrontEnd;
import Generics.ManejadorTablaFrontEnd.*;
import Managers.driver.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void ExtraerDatosTabla() {
        waitUntilEscritorioComercialIsLoaded();
        log.debug(ManejadorTablaFrontEnd.extraerDatosDeTablaAJson(tablaLimites));

        String tabla="//article[contains(h5,'Límites disponibles')]//div[contains(@tabla-select,'') and contains(@class,'table')]//table[contains(@class,'tabla-interactiva')]";

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
            log.debug("Objeto actual: "+objeto);
            datosTabla.add(objeto);
        }
        log.debug("Datos Tabla: "+datosTabla);


    }
}
