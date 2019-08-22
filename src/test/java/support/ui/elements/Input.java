package support.ui.elements;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;
import support.ui.interfaces.IInput;
import support.ui.throwable.UnexpectedClassNameException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;

import java.util.List;

public class Input implements WrapsElement, IInput{
    private final WebElement element;

    public Input(WebElement element){
        String  className = element.getAttribute("class");
        String expected = "form-group";
        if(!className.contains(expected))throw new UnexpectedClassNameException(expected,className);
        this.element = element;
    }

    @Override
    public WebElement getWrappedElement() {
        return element;
    }

    public void setValue(String value){
        element.findElement(By.tagName("input")).sendKeys(value);
    }

    public String getValue(){
        return element.findElement(By.tagName("input")).getAttribute("value");
    }

    public boolean isDisabled(){
        String d = getInputTag().getAttribute("disabled");
        return !(d.isEmpty()|| d.toLowerCase().equals("true"));
    }

    public WebElement getInputTag(){
        return element.findElement(By.tagName("input"));
    }

    public void clear(){
        getInputTag().clear();
    }

    public boolean isError(){
        try {
            return !getVisiblesError().isEmpty();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public List<WebElement> getVisiblesError(){
        return element.findElements(By.xpath(".//small[contains(@class,'invalid') and not(contains(@class,'ng-hide'))]"));
    }


}
