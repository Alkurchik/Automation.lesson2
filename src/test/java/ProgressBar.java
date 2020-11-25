import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProgressBar {
    private WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html");
    }

    @Test
    public void progressBar(){
        driver.findElement(By.xpath("//*[@id=\"cricle-btn\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"circle\"]//div[@class=\"percenttext\"][text()=\"50%\"]"));
        driver.navigate().refresh();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } //чтобы явно визиально оценить
        driver.findElement(By.xpath("//*[@id=\"circle\"]//div[@class=\"percenttext\"][text()=\"0%\"]"));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}

