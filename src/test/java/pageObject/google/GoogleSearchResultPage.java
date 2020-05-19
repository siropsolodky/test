package pageObject.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.PageObject;

import java.util.List;

public class GoogleSearchResultPage extends PageObject {
    final By SearchResultLocator = By.cssSelector("div[class='g']");
    final By SearchResultLinkLocator = By.cssSelector("div.rc a");

    private List<WebElement> getSearchResult() {
        return driver.findElements(SearchResultLocator);
    }

    public GoogleSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getLinkUrl(int index) {
        return getSearchResult().get(index).findElement(SearchResultLinkLocator).getAttribute("href");
    }

    public void clickLink(int index) {
        getSearchResult().get(index).findElement(SearchResultLinkLocator).click();
    }
}
