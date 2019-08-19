package Generics.support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

import java.util.List;

public class Select extends Input {

    private final WebElement element;
    private final boolean isMulti;

    /**
     * Constructor. Verifica que el WebElement sea de la plataforma comercial, (div --> input.select). De lo contrario,
     * se lanzara el error UnexpectedTagNameException.
     *
     * @param element SELECT element to wrap
     * @throws UnexpectedTagNameException when element is not a SELECT
     */
    public Select(WebElement element) {
        super(element);
        String className =  element.findElement(By.tagName("input")).getAttribute("class");
        if(null==className|| className.toLowerCase().contains("select")){
            throw new UnexpectedClassNameException("select", className);
        }

        this.element = element;
        String value = element.getAttribute("multiple");

        isMulti = (value != null && !"false".equals(value));
    }


    @Override
    public WebElement getWrappedElement() {
        return element;
    }

    public boolean isMultiple() {
        return isMulti;
    }

    public List<WebElement> getOptions() {
        if(!isExpanded()) expand();
        return element.findElements(
                By.xpath(".//ul[contains(@class,'ui-select-choices')]//li[contains(@class,'ui-select-choices-group')]//div[contains(@class,'ui-select-choices-row')]"));
    }

    public WebElement expand(){
        element.findElement(By.xpath(".//i[contains(@class,'caret pull-right') and contains(@ng-click,'')]")).click();
        return element;
    }

    public boolean isExpanded(){
        return element.findElement(By.xpath("./div[contains(@class,'ui-select-container')]")).getAttribute("class").contains("open");
    }

    public WebElement getSelectedOption(){
        return element.findElement(By.xpath(".//span[contains(@class,'ui-select-match-text')]/span"));
    }

    public void selectByVisibleText(String s) {
        getOptions().forEach((e) -> {
            if(e.getText().contains(s)) {
                e.click();
            }
        });
    }

    public void selectByIndex(int i) {
        getOptions().forEach((e) ->{
            if(e.getAttribute("id").equals(String.format("ui-select-choices-row-1-%s",i))) e.click();
        });
    }

    public void selectByValue(String s) {
        getOptions().forEach((e) -> {
            if(e.findElement(By.tagName("a")).getText().equals(s)) e.click();
        });
    }
}
