import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class SeleniumAlerts {
    private WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
    }

    @Test
    public void alertAccept(){
        driver.findElement(By.xpath("//div[text()=\"Java Script Alert Box\"]/..//button[@class=\"btn btn-default\"]")).click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void alertDismiss(){
        driver.findElement(By.xpath("//div[text()=\"Java Script Confirm Box\"]/..//button[@class=\"btn btn-default btn-lg\"]")).click();
        driver.switchTo().alert().dismiss();
        String confirmText = driver.findElement(By.id("confirm-demo")).getText();
        System.out.println(confirmText);
        Assert.assertTrue(confirmText.contains("Cancel"));
    }

    @Test
    public void alertSendKeys(){
        driver.findElement(By.xpath("//button[text()=\"Click for Prompt Box\"]")).click();
        String text = "text text text text";
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().accept();
        String confirmText = driver.findElement(By.id("prompt-demo")).getText();
        System.out.println(confirmText);
        Assert.assertTrue(confirmText.contains(text));
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
