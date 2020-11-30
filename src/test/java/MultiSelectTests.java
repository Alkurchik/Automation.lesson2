import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class MultiSelectTests {

    private WebDriver driver;
    private MultiSelectPage easyLessons;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        easyLessons = new MultiSelectPage(driver);
    }

    @Test
    public void signInTest(){ easyLessons.selectElements(); }

    @After
    public void tearDown(){
        driver.quit();
    }

}
