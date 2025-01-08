import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SpotifyLoginTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8082/login");
    }

    @Test
    public void testLogin() {

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("user@example.com");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("password123");

        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement successMessage = driver.findElement(By.id("toast-container"));
        assertTrue(successMessage.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
