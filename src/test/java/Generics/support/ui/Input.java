package Generics.support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class Input implements WrapsElement {
    private final WebElement element;

    public Input(WebElement element){
        String  className = element.getAttribute("class");
        if(className.contains("form-group"))throw new UnexpectedClassNameException("form-group",className);
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

    private WebElement getInputTag(){
        return element.findElement(By.tagName("input"));
    }


}
