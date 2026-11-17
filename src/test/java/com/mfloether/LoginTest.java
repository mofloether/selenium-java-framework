package com.mfloether;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void successfulLoginShowsProductsPage() {
        login("standard_user", "secret_sauce");

        String pageTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("title"))).getText();
        Assert.assertEquals(pageTitle, "Products");
    }

    @Test
    public void failedLoginDisplaysErrorMessage() {
        login("invalid_user", "wrong_password");

        String errorMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']")))
                .getText();
        Assert.assertTrue(errorMessage.contains("Username and password do not match"),
                "Expected an invalid-credentials error, but got: " + errorMessage);
    }

    @Test
    public void productPageDisplaysProductTitleAfterLogin() {
        login("standard_user", "secret_sauce");

        String productTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")))
                .getText();
        Assert.assertFalse(productTitle.isBlank(), "Expected at least one product title to be visible");
    }

    private void login(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();
    }
}
