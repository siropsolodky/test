import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.google.GoogleMainPage;
import pageObject.google.GoogleSearchResultPage;
import pageObject.yandexMarket.*;

public class SampleTest {
    private WebDriver driver;
    private CommonSteps commonSteps;

    @BeforeClass
    public static void prepareDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void createDriver() {
        driver = new ChromeDriver();
    }

    @After
    public void closeDriver() {
        driver.close();
    }

    @Test
    public void test1() {
        driver.get("https://www.google.com/");
        CommonSteps.checkUrl(driver,"https://www.google.com/");

        GoogleMainPage googleMain = new GoogleMainPage(driver);
        googleMain.enterSearchRequest("яндекс маркет");
        googleMain.submitSearch();

        GoogleSearchResultPage googleSearchResult = new GoogleSearchResultPage(driver);
        Assert.assertTrue(googleSearchResult.getLinkUrl(0).contains("market.yandex.ru"));
        googleSearchResult.clickLink(0);

        CommonSteps.checkUrl(driver,"https://market.yandex.ru/");
    }

    @Test
    public void test2() {
        driver.get("https://market.yandex.ru/");
        CommonSteps.checkUrl(driver,"https://market.yandex.ru/");

        // в поисковике ввести пылесосы => произойдет переход на страницу с пылесосами
        YandexMarketMainPage yandexMain = new YandexMarketMainPage(driver);
        yandexMain.enterSearchRequest("пылесосы");
        yandexMain.submitSearch();

        YandexMarketSearchResultPage yandexMarketSearch = new YandexMarketSearchResultPage(driver);
        yandexMarketSearch.clickCategory("пылесосы");

        YandexMarketCategoryPage yandexMarketCategory = new YandexMarketCategoryPage(driver);
        yandexMarketCategory.clickAllFilters();

        // Выполнить нажатие на кнопку Все фильтры  => Выбрать в списке Polaris и Vitek
        YandexMarketFilterPage yandexMarketFilter = new YandexMarketFilterPage(driver);
        yandexMarketFilter.clickManufacturer("Polaris");
        yandexMarketFilter.clickManufacturer("VITEK");

        // Установить  цену в поле  до = 6000 => Проверить что появилось окно с отоброжаемым количеством товаров
        yandexMarketFilter.enterPriceTo(6000);
        WebDriverWait driverWait = new WebDriverWait(driver, 5);
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(yandexMarketFilter.CounterLocator));

        // Нажать Показать подходящие => происходит переход на страницу с товарами
        yandexMarketFilter.clickShowFiltered();

        // Проверить что данные из фильтра отобразились в настройках с права => производитель Polaris и Vitek
        Assert.assertTrue(yandexMarketCategory.isManufacturerActive("Polaris"));
        Assert.assertTrue(yandexMarketCategory.isManufacturerActive("VITEK"));
    }
}
