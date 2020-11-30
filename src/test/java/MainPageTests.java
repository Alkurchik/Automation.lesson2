import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class MainPageTests {

    private WebDriver driver;
    private MainPage mainPage;

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
    public void signInTest(){
        MainPage newMainPage = mainPage.clickSignIn();
        String heading = newMainPage.getHeadingText();
        Assert.assertEquals("Вход", heading);
        mainPage.takeScreenshot();
    }

    @Test
    public void logInLogOutTest(){
        MainPage newMainPage = mainPage.logIn("seleniumtests@tut.by", "123456789zxcvbn");
        String success = newMainPage.getSuccessLogin();
        mainPage.takeScreenshot();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainPage.clickLogOut();
        mainPage.takeScreenshot();
        Assert.assertEquals("Selenium Test", success);
    }

    @Test
    public void failedLogInTest(){
        MainPage newMainPage = mainPage.faildLogIn("seleniumtests5@tut.by", "123456789zxcvbn");
        String success = newMainPage.getErrrorText();
        Assert.assertEquals("Неверное имя пользователя или пароль", success);
    }


    @After
    public void tearDown(){
        driver.quit();
    }

}
