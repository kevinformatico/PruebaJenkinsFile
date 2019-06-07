package stepdef;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.SharedDriver;
import io.cucumber.datatable.DataTable;
import pageobject.DetalleGarantiasPO;
import pageobject.EscritorioComercialPO;
import pageobject.PaginaInicioPO;
import pageobject.Vista360ResumenPersonaPO;

public class GarantiasDefinition {

    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    DetalleGarantiasPO detalleGarantiasPO;

    public GarantiasDefinition(SharedDriver driver, DetalleGarantiasPO detalleGarantiasPO,Vista360ResumenPersonaPO vista360ResumenPersonaPO, PaginaInicioPO paginaInicioPO, EscritorioComercialPO escritorioComercialPO){
        this.paginaInicioPO=paginaInicioPO;
        this.detalleGarantiasPO=detalleGarantiasPO;
        this.vista360ResumenPersonaPO=vista360ResumenPersonaPO;
        this.escritorioComercialPO=escritorioComercialPO;
    }

    @When("ingreso a detalle de garantias persona")
    public void ingreso_a_detalle_de_garantias(){
        vista360ResumenPersonaPO.clickBoxGarantias();
    }

    @Then("el sistema muestra una tabla con las siguientes columnas")
    public void el_sistema_muestra_una_tabla_con_las_siguientes_columnas(DataTable columnas){
        detalleGarantiasPO.imprimirTablaCompleta();
        //util.sleep(10);
    }
}