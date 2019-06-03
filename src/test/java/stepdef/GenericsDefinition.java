package stepdef;

import cucumber.api.java.en.Given;
import driver.SharedDriver;
import pageobject.EscritorioComercialPO;
import pageobject.PaginaInicioPO;

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


}
