package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WildberriesScript {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));

        try {
            driver.get("https://www.wildberries.ru/");
            setPause(15);

            WebElement searchElement = driver.findElement(By.id("searchInput"));
            searchElement.sendKeys("мобильный телефон");
            setPause(10);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("applySearchBtn")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            setPause(15);

            WebElement product = driver.findElement(By.xpath("//div[@class='product-card__wrapper']/a"));
            product.click();
            setPause(30);

            WebElement buttonAddCart = driver.findElement(By.cssSelector("button.order__button.btn-main[aria-label='Добавить в корзину'][type='button']"));
            setPause(15);

            WebElement productInCart = driver.findElement(By.cssSelector("a[data-wba-header-name='Cart']"));
            if (productInCart.isDisplayed()) {
                System.out.println("Товар успешно добавлен в корзину!");
            } else {
                System.out.println("Не удалось добавить товар в корзину.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void setPause(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}