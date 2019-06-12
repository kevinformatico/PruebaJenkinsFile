package stepdef;

import cl.bancochile.qa.pcpageobjects.EscritorioComercialPO;
import cl.bancochile.qa.pcpageobjects.PaginaInicioPO;
import org.openqa.selenium.WebDriver;

public class StepDefinition {
	
	PaginaInicioPO paginaInicioPO;
	EscritorioComercialPO escritorioComercialPO;

	public StepDefinition(WebDriver driver, PaginaInicioPO paginaInicioPO, EscritorioComercialPO escritorioComercialPO) {
			this.paginaInicioPO=paginaInicioPO;
			this.escritorioComercialPO=escritorioComercialPO;
		}












}
