import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class TutByPages {
    private WebDriver driver;
    public TutByPages(WebDriver driver) {
        this.driver = driver;
    }


    private By menuButton = By.xpath("//li[@class=\"topbar__li b-topbar-aside\"]/a/span");
    private By searchInput = By.xpath("//*[@id=\"search\"]//div[@class=\"ip-holder\"]/div/input");

    private By financeButton = By.xpath("//ul[@class=\"b-topbar-more-list\"]/li/a[@title=\"Финансы\"]");
    private By afishaButton = By.xpath("//ul[@class=\"b-topbar-more-list\"]/li/a[@title=\"Афиша\"]");
    private By moreItems = By.xpath("//*[@id=\"mainmenu\"]//a[text()=\"Все разделы\"]");

    private By moreBanksButton = By.xpath("//*[@id=\"nav-more-banks\"]/a");
    private By kinoButton = By.xpath("//a[@title=\"Кино\"]");
    private By priceCatalog = By.xpath("//*[@id=\"resourcesContainer\"]//a[text()=\"Каталог цен\"]");

    private By goFilm = By.xpath("//*[@id=\"events-block\"]//span[text()=\"Довод\"]");
    private By belgazpromBankButton = By.xpath("//div[@class=\"b-cc m-detail\"]/a[contains(@href,'belgazprombank')]");
    private By goProduct = By.xpath("//strong[text()=\"Apple iPhone 8 64GB\"]");

    public By bankTitle = By.xpath("//*[@id=\"content-band\"]//h1[text()='Белгазпромбанк']");
    public By filmTitle = By.xpath("//*[@id=\"event-name\"]");
    public By productTitle = By.xpath("//div[@class=\"b-item-header\"]/h1[@class=\"big\"]");

    public TutByPages clickSignIn(By selector){
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(selector));
        driver.findElement(selector).click();
        return this;
    }

    public TutByPages tutByBanks(){
        clickSignIn(menuButton);
        clickSignIn(financeButton);
        clickSignIn(moreBanksButton);
        clickSignIn(belgazpromBankButton);
        return this;
    }

    public TutByPages tutByAfisha(){
        clickSignIn(menuButton);
        clickSignIn(afishaButton);
        clickSignIn(kinoButton);
        clickSignIn(goFilm);
        return this;
    }

    public TutByPages tutByCatalog(){
        clickSignIn(menuButton);
        clickSignIn(moreItems);
        clickSignIn(priceCatalog);
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        driver.findElement(searchInput).sendKeys("Apple iPhone 8 64GB");
        clickSignIn(goProduct);

        //Тут вопрос: в новой вкладке тест не ждет пока загрузится страница или появиться
        //элемент который в ассерте и + у меня установлен " ... implicitlyWait(5 ... " ?
        //пришлось поставить Thread.sleep

        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

}
