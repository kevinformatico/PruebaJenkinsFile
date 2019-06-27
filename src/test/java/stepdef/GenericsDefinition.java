package stepdef;

import com.neovisionaries.ws.client.WebSocketException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.pgtoopx.BuilderMessages;
import com.pgtoopx.ChromeDevTools;
import driver.SharedDriver;

import pageobjects.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenericsDefinition {

    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    DetalleGarantiasPO detalleGarantiasPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    AsideGarantiasEmpresaPO asideGarantiasEmpresaPO;
    Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;
    ArrayList<byte[]> screenshotList;
    //ConexionDB conexionDB;

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
        detalleGarantiasPO.takeScreenshot();
    }

    @Given("busco el rut {string}")
    public void busco_el_rut_string(String rut){
        escritorioComercialPO.buscarPorRut(rut);
    }

    @When("Se pierde la conexion de internet")
    public void se_pierde_la_conexion_de_internet() throws InterruptedException, WebSocketException, IOException {
        ChromeDevTools.sendWSMessage(BuilderMessages.buildNetworkEmulationOffline());
    }

    @Then("pruebo algo")
    public void prueba() throws SQLException {
        //conexionDB = new ConexionDB("jdbc:oracle:thin:@152.139.146.170:1521/DIANA.bch.bancodechile.cl","Siebel","siebelqa");
        //Connection connection= conexionDB.getConexion();
        //ResultSet resultSet= conexionDB.RunQuery(connection,"SELECT soe.name as rut, sa.build AS folio, sa.warranty_type_cd AS tipo_garantia, sa.cfg_type_cd AS tipo_bien, sa.curcy_cd as moneda, sa.asset_value_amt AS valor_comercial, sa.rplcmnt_val_amt AS valor_garantia, sa.exch_date AS tasacion, sa.extension_cd AS seguro, sa.status_cd as estado FROM siebel.s_asset sa, siebel.s_org_ext soe WHERE sa.type_cd = 'Warranty' AND soe.par_row_id = sa.owner_accnt_id AND UPPER(sa.status_cd) in ('CONSTITUIDA', 'POR ALZAR') AND instr(soe.NAME,'-')>8;");
        //System.out.println(resultSet);
        //System.out.println(ResultSetConverter.convert(resultSet));
    }
}