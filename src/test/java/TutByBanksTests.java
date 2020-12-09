import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class TutByBanksTests {
    private WebDriver driver;
    private TutByPages tutByPages;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.tut.by/");
        tutByPages = new TutByPages(driver);
    }

    @Test
    public void tutByBanks(){
        tutByPages.tutByBanks();
        Assert.assertEquals("Белгазпромбанк", driver.findElement(tutByPages.bankTitle).getText());
    }

    @Test
    public void tutByAfisha(){
        tutByPages.tutByAfisha();
        Assert.assertEquals("Довод", driver.findElement(tutByPages.filmTitle).getText());
    }

    @Test
    public void tutByCatalog(){
        tutByPages.tutByCatalog();
        Assert.assertEquals("Смартфон Apple iPhone 8 64GB", driver.findElement(tutByPages.productTitle).getText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
