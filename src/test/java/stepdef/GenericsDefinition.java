package stepdef;

import cl.bancochile.qa.pcpageobjects.*;
import com.neovisionaries.ws.client.WebSocketException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import com.pgtoopx.BuilderMessages;
import com.pgtoopx.ChromeDevTools;
import cucumber.api.java.hu.De;
import driver.DriverFactory;
import driver.SharedDriver;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class GenericsDefinition {

    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    DetalleGarantiasPO detalleGarantiasPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    WebDriver driver;
    public GenericsDefinition(SharedDriver driver){
        this.driver= DriverFactory.getDriver();
        this.paginaInicioPO=new PaginaInicioPO(this.driver);
        this.escritorioComercialPO= new EscritorioComercialPO(this.driver);
        this.detalleGarantiasPO = new DetalleGarantiasPO(this.driver);
        this.vista360ResumenPersonaPO = new Vista360ResumenPersonaPO(this.driver);
    }


    @Given("el usuario {string} ingreso a V360")
    public void el_usuario_string_ingresa_a_v360(String usuario){
        paginaInicioPO.iniciarSesion(usuario, "Banco01");
    }

    @Given("busco el rut {string}")
    public void busco_el_rut_string(String rut){
        escritorioComercialPO.buscarPorRut(rut);
    }

    @When("Se pierde la conexion de internet")
    public void se_pierde_la_conexion_de_internet() throws InterruptedException, WebSocketException, IOException {
        ChromeDevTools.sendWSMessage(BuilderMessages.buildNetworkEmulationOffline());
    }


}
