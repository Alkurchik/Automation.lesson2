import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiSelectPage {

    private  WebDriver driver;

    public MultiSelectPage(WebDriver driver) {

        this.driver = driver;
        findSelectBox().arrayGenerator();
    }

    private By multiSelectBox = By.xpath("//*[@id=\"multi-select\"]");
    private By multiSelectOption = By.xpath("//*[@id=\"multi-select\"]/option");
    private By printAllButton = By.id("printAll");
    private By getAllCssSelector = By.cssSelector("p.getall-selected");
    List<WebElement> options;
    Select sel;


    private MultiSelectPage findSelectBox(){
        WebElement selecBox = driver.findElement(multiSelectBox);
        sel = new Select(selecBox);
        options = driver.findElements(multiSelectOption);
        return this;
    }

    ArrayList<Integer> integerArray = new ArrayList<>();

    private MultiSelectPage arrayGenerator(){
        for (int i = 0; i < 3; i++) {
            double random = (Math.random() * options.size()-1);
            integerArray.add((int) random);
        }
        return this;
    }

    public MultiSelectPage selectElements(){
        for (int i = 0; i < integerArray.size(); i++) {
            System.out.println("Получаем позицию опции: " + integerArray.toArray()[i]);
            String xPathV = String.format("//*[@id=\"multi-select\"]/option[%s]",integerArray.toArray()[i]);
            String getSelectOption = driver.findElement(By.xpath(xPathV)).getText();
            sel.selectByValue(getSelectOption);
            driver.findElement(printAllButton).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = driver.findElement(getAllCssSelector).getText();
            Assert.assertEquals(s.contains(getSelectOption), true );
        }
        return this;
    }
}
