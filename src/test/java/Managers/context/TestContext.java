package Managers.context;

import Managers.driver.DriverFactory;
import Managers.driver.SharedDriver;
import org.openqa.selenium.WebDriver;
import pageobjects.EscritorioComercialPO;
import pageobjects.PaginaInicioPO;
import pageobjects.Vista360ResumenEmpresaPO;
import pageobjects.Vista360ResumenPersonaPO;
import pageobjects.ventaEmpresa.*;

import java.util.ArrayList;

public class TestContext {

    public WebDriver driver;
    public ScenarioContext scenarioContext;
    public PaginaInicioPO paginaInicioPO;
    public EscritorioComercialPO escritorioComercialPO;
    public Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    public Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;
    public ConfiguracionDeProductosPO configuracionDeProductosPO;
    public PresentacionDeProductosPO presentacionDeProductosPO;
    public DatosAdicionalesPO datosAdicionalesPO;
    public AsociarLimitesPo asociarLimitesPo;
    public ApoderadosPO apoderadosPO;
    public DocumentosAdjuntosPO documentosAdjuntosPO;
    public ArrayList<byte[]> screenshotList;

    public TestContext(SharedDriver driver){
        //TODO: Instanciación del driver se pierde por alguna razon
        this.driver=DriverFactory.getDriver();
        this.paginaInicioPO= new PaginaInicioPO();
        this.escritorioComercialPO= new EscritorioComercialPO();
        this.vista360ResumenPersonaPO= new Vista360ResumenPersonaPO();
        this.vista360ResumenEmpresaPO= new Vista360ResumenEmpresaPO();
        this.configuracionDeProductosPO= new ConfiguracionDeProductosPO();
        this.presentacionDeProductosPO= new PresentacionDeProductosPO();
        this.datosAdicionalesPO= new DatosAdicionalesPO();
        this.asociarLimitesPo= new AsociarLimitesPo();
        this.apoderadosPO= new ApoderadosPO();
        this.documentosAdjuntosPO= new DocumentosAdjuntosPO();
        this.scenarioContext= new ScenarioContext();
    }
}
