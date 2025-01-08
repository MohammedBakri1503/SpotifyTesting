package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {
    private WebDriver driver;

    private By successMessageBy = By.id("toast-container");

    public HomePage(WebDriver driver) {
        this.driver = driver;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Spotify Clone"));
    }

    public boolean isLoggedInSuccessfully() {
        WebElement successMessage = driver.findElement(successMessageBy);
        return successMessage.isDisplayed();
    }
}
