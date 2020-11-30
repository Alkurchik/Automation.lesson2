import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProgressBarPage {

    private WebDriver driver;

    public ProgressBarPage(WebDriver driver) { this.driver = driver; }

    private By progressBarButton = By.xpath("//*[@id=\"cricle-btn\"]");
    private By expectedProgress50 = By.xpath("//*[@id=\"circle\"]//div[@class=\"percenttext\"][text()=\"50%\"]");
    private By expectedProgress0 = By.xpath("//*[@id=\"circle\"]//div[@class=\"percenttext\"][text()=\"0%\"]");

    public void progressBar(){
        driver.findElement(progressBarButton).click();
        driver.findElement(expectedProgress50);
        driver.navigate().refresh();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(expectedProgress0);
    }
}
