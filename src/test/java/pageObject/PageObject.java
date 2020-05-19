package pageObject;

import org.openqa.selenium.WebDriver;

public class PageObject {
    protected final WebDriver driver;

    protected PageObject(WebDriver driver) {
        this.driver = driver;
    }
}
