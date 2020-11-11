import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by christian.draeger on 30.06.16.
 */
@RunWith(Parameterized.class)
public class WithConstructorIT {
    private WebDriver driver;
    private MainPage mainPage;

    @Parameterized.Parameters
    public static Collection<Object[]> blabla() {
        return Arrays.asList(new Object[][] { {"seleniumtests@tut.by", "123456789zxcvbn"}, {"seleniumtests2@tut.by", "123456789zxcvbn"}
        });
    }

    private String login;
    private String password;

    public WithConstructorIT(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/alex/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.tut.by/");
        mainPage = new MainPage(driver);

    }

    @Test
    public void logInTest(){
        MainPage newMainPage = mainPage.logIn(login, password);
        String success = newMainPage.getSuccessLogin();
        Assert.assertEquals("Selenium Test", success);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}