import configuration.WebDriverConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import java.time.Duration;

public class WildberriesScriptTest {

    private static final Logger logger = LogManager.getLogger(WildberriesScriptTest.class);
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = WebDriverConfig.getChromeDriver();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddToCart() {
        driver.get("https://www.wildberries.ru/");
        Utils.setPause(15);

        WebElement searchElement = driver.findElement(By.id("searchInput"));
        searchElement.sendKeys("мобильный телефон");
        Utils.setPause(10);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("applySearchBtn")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        Utils.setPause(15);

        WebElement product = driver.findElement(By.xpath("//div[@class='product-card__wrapper']/a"));
        product.click();
        Utils.setPause(15);

        WebElement productInCart = driver.findElement(By.cssSelector("a[data-wba-header-name='Cart']"));
        Utils.setPause(15);

        if (!productInCart.isDisplayed()) {
            logger.error("Не удалось добавить товар в корзину.");
            throw new RuntimeException("Не удалось добавить товар в корзину.");
        } else {
            logger.info("Товар успешно добавлен в корзину!");
        }
    }
}