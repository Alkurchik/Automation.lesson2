import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class SeleniumEasyPage {

    private WebDriver driver;
    private EasyLessons easyLessons;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        easyLessons = new EasyLessons(driver);
    }

    @Test
    public void signInTest(){
        WebElement selecBox = driver.findElement(By.xpath("//*[@id=\"multi-select\"]"));
        Select sel = new Select(selecBox);
        List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"multi-select\"]/option"));
//        System.out.println(options.size());

        ArrayList<Integer> integerArray = new ArrayList<>();

        for (int i = 0; integerArray.size() < 3; i++) {
            double random = (Math.random() * options.size()-1);
            integerArray.add((int) random);
            System.out.println(Arrays.toString(integerArray.toArray()));
        }

        for (int i = 0; i < integerArray.size(); i++) {
            System.out.println("Получаем позицию опции: " + integerArray.toArray()[i]);
            String xPathV = String.format("//*[@id=\"multi-select\"]/option[%s]",integerArray.toArray()[i]);
            String getSelectOption = driver.findElement(By.xpath(xPathV)).getText();
            sel.selectByValue(getSelectOption);
            System.out.println("Получаем контент выбраной опции 1: " + getSelectOption);
            System.out.println("Получаем xPath выбраной опции: " + xPathV);
            driver.findElement(By.id("printAll")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String s = driver.findElement(By.cssSelector("p.getall-selected")).getText();
            System.out.println("Получаем сообщение выбраной опции по getAll: " + s);
            System.out.println("Получаем контент выбраной опции 2: " + getSelectOption);
            Assert.assertEquals(s.contains(getSelectOption), true );
        }

    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
