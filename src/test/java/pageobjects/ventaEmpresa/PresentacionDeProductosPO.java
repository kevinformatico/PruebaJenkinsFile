package pageobjects.ventaEmpresa;

public class PresentacionDeProductosPO {

    String xpathResumenOportunidad= "//detalle-oportunidad-empresas[.//div[contains(@role,'ROLE_VISUALIZPREOPOR_VISUALIZAR')]]";

    String xpathHeaderPresentacionDeProductos= "//section[contains(@class,'view-ventas')]//div[contains(@class,'work-area__header')]";

    String xpathComponenteDatosAdicionales= "//section[contains(@class,'view-ventas')]//div[contains(@class,'card-aside') and .//span[contains(text(),'Datos adicionales')]]";

    String xpathComponenteDocumentosAdjuntos= "//section[contains(@class,'view-ventas')]//div[contains(@class,'card-aside') and .//span[contains(text(),'Documentos adjuntos')]]";

    String xpathComponenteProductosAGestionar = "//section[contains(@class,'view-ventas')]//div[contains(@ng-if,'vm.solicitudProductoCargada')]";

}
