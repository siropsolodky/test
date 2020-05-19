package pageObject.yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import pageObject.PageObject;

public class YandexMarketFilterPage extends PageObject {
    public final By CounterLocator = By.cssSelector("div.n-filter-panel-counter");
    final By ManufacturerCheckBoxLocator = By.cssSelector("[data-filter-id='7893318'] label");
    final By PriceToLocator = By.cssSelector("#glf-priceto-var");
    final By ShowFilteredLocator = By.cssSelector("a.button_action_show-filtered");

    public YandexMarketFilterPage(WebDriver driver) {
        super(driver);
    }

    public void clickManufacturer(String manufacturer) throws WebDriverException {
        driver.findElements(ManufacturerCheckBoxLocator).stream()
                .filter(e -> e.getText().contains(manufacturer))
                .findFirst()
                .orElseThrow(() -> new WebDriverException("Manufacturer '" + manufacturer + "' not found"))
                .click();
    }

    public void enterPriceTo(int price) {
        driver.findElement(PriceToLocator).sendKeys(String.valueOf(price));
    }

    public void clickShowFiltered() {
        driver.findElement(ShowFilteredLocator).click();
    }
}
