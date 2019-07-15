package Managers;

import Managers.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import pageobjects.EscritorioComercialPO;
import pageobjects.PaginaInicioPO;
import pageobjects.Vista360ResumenEmpresaPO;
import pageobjects.Vista360ResumenPersonaPO;
import pageobjects.ventaEmpresa.*;

public class PageObjectManager {

    private WebDriver driver;
    //Base
    private EscritorioComercialPO escritorioComercialPO;
    private PaginaInicioPO paginaInicioPO;
    private Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    private Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;
    //Venta Empresa
    private AnularOportunidadPO anularOportunidadPO;
    private ConfiguracionDeProductosPO configuracionDeProductosPO;
    private DatosAdicionalesPO datosAdicionalesPO;
    private DocumentosAdjuntosPO documentosAdjuntosPO;
    private PresentacionDeProductosPO presentacionDeProductosPO;

    public PageObjectManager(){
        this.driver= DriverFactory.getDriver();
    }

    public EscritorioComercialPO getEscritorioComercialPO() {
        return (escritorioComercialPO == null) ? new EscritorioComercialPO() : escritorioComercialPO;
    }

    public PaginaInicioPO getPaginaInicioPO() {
        return (paginaInicioPO == null) ? new PaginaInicioPO() : paginaInicioPO;
    }

    public Vista360ResumenPersonaPO getVista360ResumenPersonaPO() {
        return (vista360ResumenPersonaPO==null) ? new Vista360ResumenPersonaPO() : vista360ResumenPersonaPO;
    }

    public Vista360ResumenEmpresaPO getVista360ResumenEmpresaPO() {
        return (vista360ResumenEmpresaPO== null ) ? new Vista360ResumenEmpresaPO() : vista360ResumenEmpresaPO;
    }

    public AnularOportunidadPO getAnularOportunidadPO() {
        return (anularOportunidadPO==null) ? new AnularOportunidadPO() : anularOportunidadPO;
    }

    public ConfiguracionDeProductosPO getConfiguracionDeProductosPO() {
        return (configuracionDeProductosPO== null) ? new ConfiguracionDeProductosPO() : configuracionDeProductosPO;
    }

    public DatosAdicionalesPO getDatosAdicionalesPO() {
        return (datosAdicionalesPO== null) ? new DatosAdicionalesPO() : datosAdicionalesPO;
    }

    public DocumentosAdjuntosPO getDocumentosAdjuntosPO() {
        return (documentosAdjuntosPO== null) ? new DocumentosAdjuntosPO() : documentosAdjuntosPO;
    }

    public PresentacionDeProductosPO getPresentacionDeProductosPO() {
        return (presentacionDeProductosPO==null) ? new PresentacionDeProductosPO() : presentacionDeProductosPO;
    }
}
