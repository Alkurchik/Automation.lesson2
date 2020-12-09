import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsPage {
    private WebDriver driver;

    public AlertsPage(WebDriver driver) { this.driver = driver; }

    private By alert1 = By.xpath("//div[text()=\"Java Script Alert Box\"]/..//button[@class=\"btn btn-default\"]");
    private By alert2 = By.xpath("//div[text()=\"Java Script Confirm Box\"]/..//button[@class=\"btn btn-default btn-lg\"]");
    private By textConfirmAlert2 = By.id("confirm-demo");
    private By alert3 = By.xpath("//button[text()=\"Click for Prompt Box\"]");
    private By textConfirmAlert3 = By.id("prompt-demo");

    private void sleepThread(int timer){
        try {
            Thread.sleep(timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AlertsPage alert1Accept(){
        sleepThread(2000);
        driver.findElement(alert1).click();
        sleepThread(2000);
        driver.switchTo().alert().accept();
        return this;
    }

    public AlertsPage alert2Dismiss(){
        sleepThread(2000);
        driver.findElement(alert2).click();
        sleepThread(2000);
        driver.switchTo().alert().dismiss();
        String confirmText = driver.findElement(textConfirmAlert2).getText();
        Assert.assertTrue(confirmText.contains("Cancel"));
        return this;
    }

    public void alertSendKeys(String text){
        sleepThread(2000);
        driver.findElement(alert3).click();
        sleepThread(2000);
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().accept();
        String confirmText = driver.findElement(textConfirmAlert3).getText();
        Assert.assertTrue(confirmText.contains("123123123"));
    }
}
