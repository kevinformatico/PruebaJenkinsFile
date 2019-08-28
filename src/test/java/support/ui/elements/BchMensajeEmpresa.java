package support.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import support.ui.throwable.UnexpectedClassNameException;

public class BchMensajeEmpresa implements WrapsElement {

    private final WebElement element;

    public BchMensajeEmpresa(WebElement element){
        String  className = element.getAttribute("class");
        String expected = "bch-mensaje-empresas";
        if(!className.contains(expected))throw new UnexpectedClassNameException(expected,className);
        this.element = element;
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }

    public String getTitulo(){
        return element.findElement(By.tagName("h5")).getText();
    }

    public String getSubtitulo(){
        return element.findElement(By.tagName("p")).getText();
    }

    public boolean isContainsSubtitulo(){
        try {
            return element.findElement(By.tagName("p")).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isSuccess(){
        return element.getAttribute("class").contains("success");
    }

    public boolean isWarning(){
        return element.getAttribute("class").contains("warning");
    }

    public boolean isError(){
        return element.getAttribute("class").contains("error");
    }

    public boolean isInformation(){
        return element.getAttribute("class").contains("information");
    }

    public boolean isCloseEnabled(){
        try {
            return element.findElement(By.xpath(".//a[contains(@class,'close-mensaje')]")).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public boolean isEntendidoEnabled(){
        return element.getAttribute("class").contains("bch-mensaje-entendido");
    }

}
