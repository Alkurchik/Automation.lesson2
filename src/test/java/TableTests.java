import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TableTests {
    static WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
//        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
//        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://192.168.1.50:4444/wd/hub"), options);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("test1");
//        driver.manage().window().maximize();
        System.out.println("test2");
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
