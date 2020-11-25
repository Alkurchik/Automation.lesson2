import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainTable {
    private WebElement tableElement;
    private WebDriver driver;

    public MainTable(WebElement tableElement, WebDriver driver){
        this.tableElement = tableElement;
        this.driver = driver;
    }

    public List<WebElement> getRows(){
        WebElement tableElement = driver.findElement(By.id("example"));
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

    public boolean findBySalary(String age, String salary)
    {
        List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
        for (WebElement row : rows)
        {
            List<WebElement> cells = tableElement.findElements(By.xpath(String.format("//tr/td[4][text()>%s]/../td[6][@data-order>%s]", age, salary)));
            System.out.println("количество на странице: " + cells.size());

            for (WebElement cell : cells)
            {
                System.out.println(cell.getText());
                System.out.println("-------------------");
                driver.findElement(By.xpath("//a[@id=\"example_next\"]")).click();


//                int age = Integer.parseInt(cells.get(1).getText());
//            if (cells.get(1).getText().equals(name))
//                if (age > getAge and salary <= getSalary) {
//                    System.out.println(cells.get(0).getText());
//                    System.out.println(cells.get(1).getText());
//                    System.out.println(cells.get(2).getText());
//                }
            }
        }
        return false;
    }
}