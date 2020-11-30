import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Lesson4PageFactory {
    private WebDriver driver;

    public Lesson4PageFactory(WebDriver driver) {

        this.driver = driver;
    }

    public Lesson4PageFactory() {
        // do nothing
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"authorize\"]//a[text()=\"Войти\"]")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"authorize\"]//form[@class=\"auth-form\"]//input[@name=\"login\"]")
    private WebElement userEmailField;

    @FindBy(xpath = "//*[@id=\"authorize\"]//form[@class=\"auth-form\"]//input[@name=\"password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"authorize\"]//form[@class=\"auth-form\"]//input[@type=\"submit\"]")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id=\"authorize\"]//form[@class=\"auth-form\"]/div[@class=\"error-msg\"]")
    private WebElement errorMsg;

    @FindBy(xpath = "//*[@id=\"authorize\"]//span[@class=\"b-popup-close\"]")
    private WebElement closePopUp;

    @FindBy (xpath = "//*[@id=\"authorize\"]//span[text()=\"Selenium Test\"]")
    private WebElement logged_users;

    @FindBy(xpath = "b-auth-title")
    private WebElement heading;

    @FindBy(className = "uname")
    private WebElement profileButton;

    @FindBy(xpath = "//*[@id=\"authorize\"]//div[@class=\"b-popup-i\"]//a[@class=\"button wide auth__reg\"]")
    private WebElement logoutButton;

    public void takeScreenshot(String imageName){
        TakesScreenshot scrShot = (TakesScreenshot)driver;
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("/Users/alex/IdeaProjects/issoft.lesson2/images/" + imageName + ".png");
        try {
            FileHandler.copy(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Lesson4PageFactory clickLogOut(){
        profileButton.click();
        logoutButton.click();
        return this;
    }

    public Lesson4PageFactory clickSignIn(){
        signInButton.click();
        return this;
    }

    public Lesson4PageFactory typeUserEmail(String useremail){
        userEmailField.sendKeys(useremail);
        return this;
    }

    public Lesson4PageFactory typeUserPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public Lesson4PageFactory clickSubmitButton(){
        submitButton.submit();
        return this;
    }

    public String getSuccessLogin(){
        return logged_users.getText();
    }

    public Lesson4PageFactory logIn(String useremail, String password){
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
        return new Lesson4PageFactory(driver);
    }
}
