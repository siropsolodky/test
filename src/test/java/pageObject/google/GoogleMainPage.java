package pageObject.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObject.PageObject;

public class GoogleMainPage extends PageObject {
    private final By searchInputLocator = By.cssSelector("form[action='/search'] input[type='text']");
    private final By searchSubmitLocator = By.cssSelector("form[action='/search'] input[name='btnK']");

    public GoogleMainPage(WebDriver driver) {
        super(driver);
    }

    public void enterSearchRequest(String request) {
        driver.findElement(searchInputLocator).sendKeys(request);
    }

    public void submitSearch() {
        driver.findElement(searchSubmitLocator).click();
    }
}
