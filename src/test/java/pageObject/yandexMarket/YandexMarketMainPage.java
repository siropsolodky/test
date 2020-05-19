package pageObject.yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObject.PageObject;

public class YandexMarketMainPage extends PageObject {
    final By searchInputLocator = By.cssSelector("#header-search");
    final By searchSubmitLocator = By.cssSelector("form[action='/search'] button[type='submit']");

    public YandexMarketMainPage(WebDriver driver){
        super(driver);
    }

    public void enterSearchRequest(String request) {
        driver.findElement(searchInputLocator).sendKeys(request);
    }

    public void submitSearch() {
        driver.findElement(searchSubmitLocator).click();
    }

}
