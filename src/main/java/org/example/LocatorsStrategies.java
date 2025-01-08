package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

public class LocatorsStrategies {

    private static final String baseURL = "http://localhost:8082";

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get(baseURL);

        // Find the FIRST element with class="dot" attribute
        WebElement seekBar = driver.findElement(By.className("dot"));

        // To locate all elements identified by class="dot":
        List<WebElement> seekBars = driver.findElements(By.className("dot"));

        // to locate an element with a `.bi-play-fill` class, located inside a <div> container
        WebElement playButton = driver.findElement(By.cssSelector("div .bi-play-fill"));

        // or by id
        playButton = driver.findElement(By.id("masterPlay"));

        // locate element by name
        //  WebElement textFields = driver.findElement(By.name("searchText"));

        // locate by link text
        WebElement myLibraryPageLink = driver.findElement(By.linkText("My Library"));

        // locate by tag name
        myLibraryPageLink = driver.findElement(By.tagName("header"));


        try {
            WebElement notRealElement = driver.findElement(By.className("blabla"));
        } catch (NoSuchElementException e) {
            System.out.println("no such element");
        }

        // Compound class names not permitted
        try {
            playButton = driver.findElement(By.className("bi bi-play-fill"));
        } catch (InvalidSelectorException _) {

        }

        // instead, use css selector
        playButton = driver.findElement(By.cssSelector(".bi.bi-play-fill"));

        // first locate a parent element, then search the desired element within it
        WebElement volumeSeek = driver.findElement(By.className("vol")).findElement(By.className("dot"));

        // or chaining two locators at once
        By volumeSeekBy = new ByChained(By.className("vol"), By.className("dot"));
        volumeSeek = driver.findElement(volumeSeekBy);


        driver.quit();
    }
}