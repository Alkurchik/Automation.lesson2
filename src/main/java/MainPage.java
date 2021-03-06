import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private  WebDriver driver;
    public MainPage(WebDriver driver) {

        this.driver = driver;
    }

    private By signInButton = By.xpath("//*[@id=\"authorize\"]//a[text()=\"Войти\"]");
    private By userEmailField = By.xpath("//*[@id=\"authorize\"]//form[@class=\"auth-form\"]//input[@name=\"login\"]");
    private By passwordField = By.xpath("//*[@id=\"authorize\"]//form[@class=\"auth-form\"]//input[@name=\"password\"]");
    private By submitButton = By.xpath("//*[@id=\"authorize\"]//form[@class=\"auth-form\"]//input[@type=\"submit\"]");
    private By errorMsg = By.xpath("//*[@id=\"authorize\"]//form[@class=\"auth-form\"]/div[@class=\"error-msg\"]");
    //<div class="error-msg">Неверное имя пользователя или пароль</div>
    private By closePopUp = By.xpath("//*[@id=\"authorize\"]//span[@class=\"b-popup-close\"]");
    private By logged_users = By.xpath("//*[@id=\"authorize\"]//span[text()=\"Selenium Test\"]");
    private By heading = By.className("b-auth-title");
    private By profileButton = By.className("uname");
    private By logoutButton = By.xpath("//*[@id=\"authorize\"]//div[@class=\"b-popup-i\"]//a[@class=\"button wide auth__reg\"]");


    public MainPage clickSignIn(){
        driver.findElement(signInButton).click();
        return this;
    }

    public MainPage clickLogOut(){
        driver.findElement(profileButton).click();
        driver.findElement(logoutButton).click();
        return this;
    }

    public MainPage typeUserEmail(String useremail){
        driver.findElement(userEmailField).sendKeys(useremail);
        return this;
    }

    public void takeScreenshot(){
        TakesScreenshot scrShot = (TakesScreenshot)driver;
        File SrcFile= scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File("/Users/alex/IdeaProjects/issoft.lesson2/images/screenshot1.png");
        try {
            FileHandler.copy(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MainPage typeUserPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public MainPage clickSubmitButton(){
        driver.findElement(submitButton).submit();
        return this;
    }

    public String getHeadingText(){
        return driver.findElement(heading).getText();
    }

    public String getErrrorText(){
        return driver.findElement(errorMsg).getText();
    }

    public String getSuccessLogin(){
        return driver.findElement(logged_users).getText();
    }

    public void waiting(){
        WebDriverWait wait = (new WebDriverWait(driver, 10));
        wait.until(ExpectedConditions.presenceOfElementLocated(logged_users));
    }

    public MainPage logIn(String useremail, String password){
        this.clickSignIn();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.typeUserEmail(useremail);
        this.typeUserPassword(password);
        this.clickSubmitButton();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        this.waiting();
        return new MainPage(driver);
    }

    public MainPage faildLogIn(String useremail, String password){
        this.clickSignIn();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.typeUserEmail(useremail);
        this.typeUserPassword(password);
        this.clickSubmitButton();
        return new MainPage(driver);
    }

}
