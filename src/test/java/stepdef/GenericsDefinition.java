package stepdef;

import Generics.*;
import com.neovisionaries.ws.client.WebSocketException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.pgtoopx.BuilderMessages;
import com.pgtoopx.ChromeDevTools;
import driver.DriverFactory;
import driver.SharedDriver;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pageobjects.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenericsDefinition {

    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    DetalleGarantiasPO detalleGarantiasPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    AsideGarantiasEmpresaPO asideGarantiasEmpresaPO;
    Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;
    ArrayList<byte[]> screenshotList;
    ConexionDB conexionDB = new ConexionDB();
    public GenericsDefinition(SharedDriver driver,
                              ArrayList<byte[]> screenshotList,
                              PaginaInicioPO paginaInicioPO,
                              EscritorioComercialPO escritorioComercialPO,
                              DetalleGarantiasPO detalleGarantiasPO,
                              Vista360ResumenPersonaPO vista360ResumenPersonaPO,
                              AsideGarantiasEmpresaPO asideGarantiasEmpresaPO,
                              Vista360ResumenEmpresaPO vista360ResumenEmpresaPO)
    {
      this.screenshotList=screenshotList;
      this.paginaInicioPO= paginaInicioPO;
      this.escritorioComercialPO= escritorioComercialPO;
      this.detalleGarantiasPO=detalleGarantiasPO;
      this.vista360ResumenPersonaPO= vista360ResumenPersonaPO;
      this.asideGarantiasEmpresaPO= asideGarantiasEmpresaPO;
      this.vista360ResumenEmpresaPO=vista360ResumenEmpresaPO;
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
    public void ingreso_a_detalle_de_garantias_persona(){
        vista360ResumenPersonaPO.clickBoxGarantias();
    }

    @When("ingreso a detalle de garantias empresa")
    public void ingreso_a_detalle_de_garantias_empresa(){
        vista360ResumenEmpresaPO.clickBotonGarantiasEmpresa();
        asideGarantiasEmpresaPO.clickIrAGarantiasVigentesEHistoricas();
    }

    @When("me encuentro en el tab {string}")
    public void me_encuentro_en_el_tab_string(String tab){
        switch (tab.toLowerCase()){
            case "por constituir": detalleGarantiasPO.clickTabPorConstituir();
        }
    }

    @Then("el sistema muestra una tabla con las siguientes columnas")
    public void el_sistema_muestra_una_tabla_con_las_siguientes_columnas(DataTable columnas) throws SQLException {
        //detalleGarantiasPO.imprimirTablaCompleta();
        //Connection connection= conexionDB.getConexion();
        //ResultSet resultSet= conexionDB.RunQuery(connection,"select * from TBL_GARANTIA ORDER BY RUT");
        //System.out.println(ResultSetConverter.convert(resultSet));

        List<String> columnasEsperadas = columnas.asList(String.class);
        List<String> columnasActuales = ManejadorTablaFrontEnd.getNameColumns(detalleGarantiasPO.getTabla());
        Assert.assertArrayEquals("no contiene las mismas columnas", columnasEsperadas.toArray(), columnasActuales.toArray());
        for(int i=0; i<columnasEsperadas.size(); i++){
            //Verify.verify(columnasEsperadas.get(i).toString().contains(columnasActuales.get(i).toString()));
        }
        System.out.println("Columnas esperadas: "+columnasEsperadas);
        System.out.println("Columnas Actuales: "+columnasActuales);
    }

    @Then("el sistema muestra el mensaje {string}")
    public void el_sistema_muestra_el_mensaje_string(String mensaje){
        detalleGarantiasPO.esperarAQueCargueLaPagina();
        DriverFactory.getDriver().findElement(By.xpath("//h5[contains(text(),'"+mensaje+"')]"));
        byte[] screenshotByte = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        screenshotList.add(screenshotByte);
    }

    @Then("el nombre del tab indica {string}")
    public void el_nombre_del_tab_indica_string(String mensaje){
        detalleGarantiasPO.esperarAQueCargueLaPagina();
        DriverFactory.getDriver().findElement(By.xpath("//a[contains(text(),'"+mensaje+"')]"));
    }


}
