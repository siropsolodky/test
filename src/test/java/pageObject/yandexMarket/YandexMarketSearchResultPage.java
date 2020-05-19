package pageObject.yandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import pageObject.PageObject;

public class YandexMarketSearchResultPage extends PageObject {
    final By CategoryLocator = By.xpath("//*[@class='headline']//a/div[2]");

    public YandexMarketSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public void clickCategory(String category) throws WebDriverException {
        driver.findElements(CategoryLocator).stream()
                .filter(e -> e.getText().toLowerCase().contains(category.toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new WebDriverException("Category '" + category + "' not found"))
                .click();
    }
}
