import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class AlertsTests {
    private WebDriver driver;
    private AlertsPage alertsPage;
    
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        alertsPage = new AlertsPage(driver);
    }

    @Test
    public void alert1Accept(){ alertsPage.alert1Accept(); }

    @Test
    public void alertDismiss(){ alertsPage.alert2Dismiss(); }

    @Test
    public void alertSendKeys(){ alertsPage.alertSendKeys("text text text"); }

    @After
    public void tearDown(){
        driver.quit();
    }
}
