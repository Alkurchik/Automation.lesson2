import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TablePage {
    private WebElement tableElement;
    private WebDriver driver;

    private By tableId = By.id("example");
    private By nextButtonId = By.id("example_next");


    public TablePage(WebElement tableElement, WebDriver driver){
        this.tableElement = tableElement;
        this.driver = driver;
    }

    public List<WebElement> getRows(){
        WebElement tableElement = driver.findElement(tableId);
        List<WebElement> rows = tableElement.findElements(By.xpath(".//tr"));
        rows.remove(0);
        return rows;
    }

    public List<WebElement> getHeadings(){
        WebElement headingsRow = tableElement.findElement(By.xpath("//tr[1]"));
        List<WebElement> headingsColumns = headingsRow.findElements(By.xpath("//th"));
        return headingsColumns;
    }

    public List<List<WebElement>> getRowsWithColumns(){
        List<WebElement> rows = getRows();
        List<List<WebElement>> rowsWithColumns = new ArrayList<List<WebElement>>();
        for (WebElement row : rows){
            List<WebElement> rowWithColumn = row.findElements(By.xpath("//td"));
            rowsWithColumns.add(rowWithColumn);
        }
        return rowsWithColumns;
    }

    public String getValueFromCell(int rowNumber, int columnNumber){
        List<List<WebElement>> rowWithColumns = getRowsWithColumns();
        WebElement cell = rowWithColumns.get(rowNumber-1).get(columnNumber-1);
        return cell.getText();

    }

    public TablePage findElement(String item){
        System.out.println(tableElement.findElement(By.xpath(item)).getText());
        return this;
    }


    public boolean findBySalary(String age, String salary)
    {

        List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
        for (WebElement row : rows)
        {
            List<WebElement> td = row.findElements(By.xpath(".//td"));

            String item = String.format("//tr/td[4][text()>%s]/../td[6][@data-order>%s]", age, salary);
            List<WebElement> cells = tableElement.findElements(By.xpath(item));
            System.out.println("-------- количество на странице: " + cells.size());
            for (WebElement cell : cells)
                {
                    System.out.println(cell.getText());
                    //тут я хотел реализовать List<List<String>>
                }
            driver.findElement(nextButtonId).click();
            if (cells.size()==0) {break;}
        }
        return true;
    }
}