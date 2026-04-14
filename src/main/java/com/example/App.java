package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        // Firefox setup (FIXED)
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");  // IMPORTANT for Jenkins
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new FirefoxDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.manage().window().maximize();

        try {

            // =====================================
            // 1. SAUCEDEMO LOGIN
            // =====================================
            driver.get("https://www.saucedemo.com/");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                    .sendKeys("standard_user");

            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();

            System.out.println("✅ SauceDemo Login Successful");

            Thread.sleep(2000);


            // =====================================
            // 2. PRACTICE TEST AUTOMATION LOGIN
            // =====================================
            driver.get("https://practicetestautomation.com/practice-test-login/");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                    .sendKeys("student");

            driver.findElement(By.id("password")).sendKeys("Password123");
            driver.findElement(By.id("submit")).click();

            System.out.println("✅ PracticeTestAutomation Login Successful");

            Thread.sleep(2000);


            // =====================================
            // 3. AUTOMATION EXERCISE LOGIN
            // =====================================
            driver.get("https://automationexercise.com/");

            // Handle ads / overlays safely
            try {
                Thread.sleep(2000);
                driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[id^='aswift']")));
                driver.switchTo().defaultContent();
            } catch (Exception ignored) {}

            // Click Signup/Login using JS (avoids ad overlay issue)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='/login']")));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.querySelector(\"a[href='/login']\").click();");

            // Login form
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")))
                    .sendKeys("rakshith3129@gmail.com");  // change if needed

            driver.findElement(By.name("password")).sendKeys("Neraj@2931");
            driver.findElement(By.xpath("//button[text()='Login']")).click();

            System.out.println("✅ AutomationExercise Login Successful");

            Thread.sleep(3000);


            // =====================================
            // 4. ADD PRODUCT TO CART
            // =====================================
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Products"))).click();

            // Wait for product and click "Add to cart"
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("(//a[contains(text(),'Add to cart')])[1]")
            ));

            js.executeScript(
                    "document.querySelectorAll(\"a[href*='add_to_cart']\")[0].click();"
            );

            System.out.println("✅ Product Added to Cart");

            Thread.sleep(3000);


        } catch (Exception e) {
            System.out.println("❌ Error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
