package com.mfloether;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LoginTest {
    @Test
    public void successfulLoginShowsProductsPage() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.saucedemo.com");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            String pageTitle = driver.findElement(By.className("title")).getText();
            Assert.assertEquals(pageTitle, "Products");
        } finally {
            driver.quit();
        }
    }
}
