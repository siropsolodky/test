package pageObject.yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import pageObject.PageObject;

public class YandexMarketCategoryPage extends PageObject {
    final By AllFilterLocator = By.cssSelector("[data-zone-name='all-filters-button'] a");
    final By ManufacturerCheckBoxLocator = By.cssSelector("[data-autotest-id='7893318'] input");

    public YandexMarketCategoryPage(WebDriver driver) {
        super(driver);
    }

    public void clickAllFilters() {
        driver.findElement(AllFilterLocator).click();
    }

    public boolean isManufacturerActive(String manufacturer) {
        return driver.findElements(ManufacturerCheckBoxLocator).stream()
                .filter(e -> e.getAttribute("name").contains(manufacturer))
                .findFirst()
                .orElseThrow(() -> new WebDriverException("Manufacturer '" + manufacturer + "' not found"))
                .isSelected();
    }
}
