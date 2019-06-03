package stepdef;

import com.neovisionaries.ws.client.WebSocketException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import driver.BuilderMessages;
import driver.ChromeDevTools;
import driver.SharedDriver;
import pageobject.EscritorioComercialPO;
import pageobject.PaginaInicioPO;

import java.io.IOException;

public class GenericsDefinition {

    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;

    public GenericsDefinition(SharedDriver driver, PaginaInicioPO paginaInicioPO, EscritorioComercialPO escritorioComercialPO){
        this.paginaInicioPO=paginaInicioPO;
        this.escritorioComercialPO=escritorioComercialPO;
    }


    @Given("el usuario {string} ingresa a V360")
    public void el_usuario_string_ingresa_a_v360(String usuario){
        paginaInicioPO.get();
        paginaInicioPO.iniciarSesion(usuario, "Banco01").get();
        escritorioComercialPO.buscarPorRut("188640583");
    }

    @When("Se pierde la conexion de internet")
    public void se_pierde_la_conexion_de_internet() throws InterruptedException, WebSocketException, IOException {
        ChromeDevTools.sendWSMessage(BuilderMessages.buildNetworkEmulationOffline());
    }


}
