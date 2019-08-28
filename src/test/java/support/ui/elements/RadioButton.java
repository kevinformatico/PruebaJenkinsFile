package support.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import support.ui.throwable.UnexpectedClassNameException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class RadioButton extends Input {

    private final WebElement element;


    public RadioButton(WebElement element){
        super(element);
        String  className = element.findElement(By.tagName("label")).getAttribute("class");
        String expected = "bch-custom-check";
        if(!className.contains(expected))throw new UnexpectedClassNameException(expected,className);
        String expected2 = "radiobutton";
        if(!className.contains(expected2))throw new UnexpectedClassNameException(expected2,className);
        this.element = element;
    }

    public List<WebElement> getOptions(){
        return element.findElements(By.xpath(".//label[contains(@class,'bch-custom-check')]"));
    }

    @Override
    public void setValue(String valor){
        if(isEnabled()) {
            try {
                getOptions().stream()
                        .filter((e) -> e.getText().equals(valor))
                        .limit(1)
                        .collect(Collectors.toList())
                        .get(0)
                        .findElement(By.tagName("input"))
                        .click();
            }catch (IndexOutOfBoundsException e){
                throw new NoSuchElementException("No se encuentra el Radio button con el valor "+valor);
            }
        }else{
            throw new ElementNotInteractableException("Checkbox no esta habilitado");
        }


    }

    public boolean isEnabled(){
        return (!element.getAttribute("class").contains("disabled") && element.isEnabled());
    }

    @Override
    public boolean isDisabled(){
        return (element.getAttribute("class").contains("disabled") && !element.isEnabled());
    }

    @Override
    public String getValue() {
        return getOptions().stream()
                .filter((e)-> e.findElement(By.tagName("input")).getAttribute("checked").equals("checked"))
                .collect(Collectors.toList()).get(0).getText();
    }
}
