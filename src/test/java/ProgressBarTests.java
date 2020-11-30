import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ProgressBarTests {
    private WebDriver driver;
    private ProgressBarPage progressBarPage;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html");
        progressBarPage = new ProgressBarPage(driver);
    }

    @Test
    public void progressBar(){ progressBarPage.progressBar(); }

    @After
    public void tearDown(){
        driver.quit();
    }
}

