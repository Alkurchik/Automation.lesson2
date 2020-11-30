import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;

public class PageFactoryTests {

    private WebDriver driver;
    Lesson4PageFactory lesson4PageFactory;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.tut.by/");
        lesson4PageFactory = PageFactory.initElements(driver, Lesson4PageFactory.class);
        lesson4PageFactory.setDriver(driver);
    }

    @Test
    public void logInTest(){
        lesson4PageFactory.logIn("seleniumtests@tut.by", "123456789zxcvbn");
        Assert.assertEquals("Selenium Test", lesson4PageFactory.getSuccessLogin());
        lesson4PageFactory.takeScreenshot("src1");
        lesson4PageFactory.clickLogOut();
        lesson4PageFactory.takeScreenshot("src2");
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
