package stepdef;

import driver.SharedDriver;
import pageobject.EscritorioComercialPO;
import pageobject.PaginaInicioPO;

public class StepDefinition {
	
	PaginaInicioPO paginaInicioPO;
	EscritorioComercialPO escritorioComercialPO;

	public StepDefinition(SharedDriver driver, PaginaInicioPO paginaInicioPO, EscritorioComercialPO escritorioComercialPO) {
			this.paginaInicioPO=paginaInicioPO;
			this.escritorioComercialPO=escritorioComercialPO;
		}












}
