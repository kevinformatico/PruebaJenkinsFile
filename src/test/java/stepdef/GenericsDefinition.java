package stepdef;

import Generics.ConexionDB;
import com.neovisionaries.ws.client.WebSocketException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.pgtoopx.BuilderMessages;
import com.pgtoopx.ChromeDevTools;
import driver.SharedDriver;
import io.cucumber.datatable.DataTable;
import pageobjects.DetalleGarantiasPO;
import pageobjects.EscritorioComercialPO;
import pageobjects.PaginaInicioPO;
import pageobjects.Vista360ResumenPersonaPO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericsDefinition {

    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    DetalleGarantiasPO detalleGarantiasPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    ConexionDB conexionDB = new ConexionDB();
    public GenericsDefinition(SharedDriver driver,
                              PaginaInicioPO paginaInicioPO,
                              EscritorioComercialPO escritorioComercialPO,
                              DetalleGarantiasPO detalleGarantiasPO,
                              Vista360ResumenPersonaPO vista360ResumenPersonaPO)
    {
      this.paginaInicioPO= paginaInicioPO;
      this.escritorioComercialPO= escritorioComercialPO;
      this.detalleGarantiasPO=detalleGarantiasPO;
      this.vista360ResumenPersonaPO= vista360ResumenPersonaPO;
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
    @When("ingreso a detalle de garantias persona")
    public void ingreso_a_detalle_de_garantias(){
        vista360ResumenPersonaPO.clickBoxGarantias();
    }

    @Then("el sistema muestra una tabla con las siguientes columnas")
    public void el_sistema_muestra_una_tabla_con_las_siguientes_columnas(DataTable columnas) throws SQLException {
        //detalleGarantiasPO.imprimirTablaCompleta();
        Connection connection = conexionDB.getConexion();
        ResultSet rs = conexionDB.RunQuery(connection, "select * from TBL_GARANTIA ORDER BY RUT");
        System.out.println(rs);
        //util.sleep(10);
    }


}
