package stepdef;

import Generics.util;
import Managers.driver.DriverFactory;
import com.neovisionaries.ws.client.WebSocketException;
import com.pgtoopx.BuilderMessages;
import com.pgtoopx.ChromeDevTools;
import Managers.PageObjectManager;
import Managers.driver.SharedDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import pageobjects.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenericsDefinition {

    final String PERFIL_EMPRESA="empresa";
    final String PERFIL_PERSONA="persona";
    final String DEFAULT_USERNAME="aestela";
    final String DEFAULT_PASSWORD="Venta75";

    PageObjectManager pageObjectManager;

    PaginaInicioPO paginaInicioPO;
    EscritorioComercialPO escritorioComercialPO;
    Vista360ResumenPersonaPO vista360ResumenPersonaPO;
    Vista360ResumenEmpresaPO vista360ResumenEmpresaPO;
    ArrayList<byte[]> screenshotList;

    public GenericsDefinition(SharedDriver driver,
                              ArrayList<byte[]> screenshotList,
                              PaginaInicioPO paginaInicioPO,
                              EscritorioComercialPO escritorioComercialPO,
                              Vista360ResumenPersonaPO vista360ResumenPersonaPO,
                              Vista360ResumenEmpresaPO vista360ResumenEmpresaPO)
    {
        this.screenshotList=screenshotList;
        this.paginaInicioPO= paginaInicioPO;
        this.escritorioComercialPO= escritorioComercialPO;
        this.vista360ResumenPersonaPO= vista360ResumenPersonaPO;
        this.vista360ResumenEmpresaPO=vista360ResumenEmpresaPO;
    }


    @Given("el usuario {string} ingreso a V360")
    public void el_usuario_string_ingresa_a_v360(String usuario){
        paginaInicioPO.iniciarSesion(usuario, getPasswordForUsuario(usuario));
    }

    @Given("busco el rut {string}")
    public void busco_el_rut_string(String rut){
        escritorioComercialPO.buscarPorRut(rut);
    }

    @Given("busco el rut {string} con perfil empresa")
    public void  busco_el_rut_string_con_perfil_empresa(String rut){
        escritorioComercialPO.buscarPorRut(rut);
        configurarPerfil(PERFIL_EMPRESA);
    }

    @Given("busco el rut {string} con perfil persona")
    public void  busco_el_rut_string_con_perfil_persona(String rut){
        escritorioComercialPO.buscarPorRut(rut);
        configurarPerfil(PERFIL_PERSONA);
    }

    @Given("el usuario busca el rut {string} con perfil empresa")
    public void  el_usuario_busca_el_rut_string_con_perfil_empresa(String rut){
        paginaInicioPO.iniciarSesion(DEFAULT_USERNAME, getPasswordForUsuario(DEFAULT_USERNAME));
        escritorioComercialPO.buscarPorRut(rut);
        configurarPerfil(PERFIL_EMPRESA);
    }

    @Given("el usuario busca el rut {string} con perfil persona")
    public void  el_usuario_busca_el_rut_string_con_perfil_persona(String rut){
        paginaInicioPO.iniciarSesion(DEFAULT_USERNAME, getPasswordForUsuario(DEFAULT_USERNAME));
        escritorioComercialPO.buscarPorRut(rut);
        configurarPerfil(PERFIL_PERSONA);
    }

    @Given("ingreso a Vista 360 persona")
    public void ingreso_a_vista_360_persona(){
        configurarPerfil(PERFIL_PERSONA);
    }

    @Given("ingreso a Vista 360 empresa")
    public void ingreso_a_vista_360_empresa(){
        configurarPerfil(PERFIL_EMPRESA);
    }

    @When("Se pierde la conexion de internet")
    public void se_pierde_la_conexion_de_internet() throws InterruptedException, WebSocketException, IOException {
        ChromeDevTools.sendWSMessage(BuilderMessages.buildNetworkEmulationOffline());
    }

    @When("espero por {int} segundos")
    public void se_pierde_la_conexion_de_internet(int segundos) {
        util.waitFor(segundos);
    }

    @Then("pruebo algo")
    public void prueba() throws SQLException {
        //conexionDB = new ConexionDB("jdbc:oracle:thin:@152.139.146.170:1521/DIANA.bch.bancodechile.cl","Siebel","siebelqa");
        //Connection connection= conexionDB.getConexion();
        //ResultSet resultSet= conexionDB.RunQuery(connection,"SELECT soe.name as rut, sa.build AS folio, sa.warranty_type_cd AS tipo_garantia, sa.cfg_type_cd AS tipo_bien, sa.curcy_cd as moneda, sa.asset_value_amt AS valor_comercial, sa.rplcmnt_val_amt AS valor_garantia, sa.exch_date AS tasacion, sa.extension_cd AS seguro, sa.status_cd as estado FROM siebel.s_asset sa, siebel.s_org_ext soe WHERE sa.type_cd = 'Warranty' AND soe.par_row_id = sa.owner_accnt_id AND UPPER(sa.status_cd) in ('CONSTITUIDA', 'POR ALZAR') AND instr(soe.NAME,'-')>8;");
        //System.out.println(resultSet);
        //System.out.println(ResultSetConverter.convert(resultSet));

        //String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries() || {}; return network;";
        //String netData = ((JavascriptExecutor) DriverFactory.getDriver()).executeScript(scriptToExecute).toString();
        //System.out.println(netData);

        

    }

    private void configurarPerfil(String tipo){
        switch (tipo){
            case "empresa":
                if (!vista360ResumenPersonaPO.getTituloVista360().toLowerCase().contains("empresa")&&
                        vista360ResumenPersonaPO.isVisibleBtnCambiarEmpresaPersona()){
                    vista360ResumenPersonaPO.clickCambiarEmpresaPersona();
                }else if(vista360ResumenPersonaPO.getTituloVista360().toLowerCase().contains("empresa")) {
                    //hace nada xd
                }else{
                    Assert.fail("El rut ingresado no tiene perfil empresa");
                }
                break;
            case "persona":
                if(!vista360ResumenPersonaPO.getTituloVista360().toLowerCase().contains("persona")&&
                        vista360ResumenPersonaPO.isVisibleBtnCambiarEmpresaPersona()){
                    vista360ResumenPersonaPO.clickCambiarEmpresaPersona();
                }else if(vista360ResumenPersonaPO.getTituloVista360().toLowerCase().contains("persona")) {
                    //hace nada xd
                }else{
                    Assert.fail("El rut ingresado no tiene perfil persona");
                }
        }
    }

    private String getPasswordForUsuario(String usuario){
        switch (usuario){
            case "MNILOS": return "Venta01";
            default: return DEFAULT_PASSWORD;
        }
    }

}