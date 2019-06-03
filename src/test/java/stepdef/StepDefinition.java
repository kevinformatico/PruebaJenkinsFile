package stepdef;

import com.neovisionaries.ws.client.WebSocketException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import driver.BuilderMessages;
import driver.ChromeDevTools;
import driver.DriverFactory;
import driver.SharedDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.EscritorioComercialPO;
import pageobject.GoogleHomePO;
import pageobject.GoogleSearchPO;
import pageobject.PaginaInicioPO;

import java.io.IOException;

public class StepDefinition {
	
	PaginaInicioPO paginaInicioPO;
	EscritorioComercialPO escritorioComercialPO;

	public StepDefinition(SharedDriver driver, PaginaInicioPO paginaInicioPO, EscritorioComercialPO escritorioComercialPO) {
			this.paginaInicioPO=paginaInicioPO;
			this.escritorioComercialPO=escritorioComercialPO;
		}










	@When("Se pierde la conexion de internet")
	public void se_pierde_la_conexion_de_internet() throws InterruptedException, WebSocketException, IOException {
		ChromeDevTools.sendWSMessage(BuilderMessages.buildNetworkEmulationOffline());
	}

}
