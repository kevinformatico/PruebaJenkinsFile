package support.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CustomBchMensajeEmpresa extends BchMensajeEmpresa {

    private final WebElement element;

    public CustomBchMensajeEmpresa(WebElement element){
        super(element);
        this.element = element;
    }

    @Override
    public String getSubtitulo(){
        return element.findElement(By.tagName("span")).getText();
    }
}
