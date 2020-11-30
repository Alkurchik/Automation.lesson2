import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class TableTests {
    static WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");
    }

    @Test
    public void findRow(){
        WebElement tableElement= driver.findElement(By.id("example"));
        TablePage mainTable = new TablePage(tableElement, driver);
        System.out.println(mainTable.getRows().size()); // так мы можем узнать сколько строк на странице
        System.out.println(mainTable.getValueFromCell(2,3)); // так мы можем получить конкретный
        //элемент на странице по координатам
        System.out.println(mainTable.findBySalary("61", "60000")); // так мы фильтруем таблицу по параметрам

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
