import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebFormsTest {

    WebDriver driver;

    private static final String formURL = "https://www.selenium.dev/selenium/web/web-form.html";

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test
    void testTextInputField() {
        driver.get(formURL);

        WebElement textInput = driver.findElement(By.name("my-text"));
        textInput.sendKeys("Selenium Test");

        assertEquals("Selenium Test", textInput.getDomProperty(
                "value"));
    }

    @Test
    void testTextareaField() throws InterruptedException {
        driver.get(formURL);

        WebElement textarea = driver.findElement(By.name("my-textarea"));
        textarea.sendKeys("Testing the textarea functionality");
        assertEquals("Testing the textarea functionality", textarea.getDomProperty("value"));
    }

    @Test
    void testDisabledInputField() {
        driver.get(formURL);

        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
        assertTrue(!disabledInput.isEnabled(), "The disabled input field should not be interactable.");
    }

    @Test
    void testReadonlyInputField() {
        driver.get(formURL);

        WebElement readonlyInput = driver.findElement(By.name("my-readonly"));
        String value = readonlyInput.getDomProperty("value");

        assertEquals("Readonly input", value);

        try {
            readonlyInput.clear();
        } catch (InvalidElementStateException e) {
            System.out.println("Caught expected InvalidElementStateException when trying to clear readonly field.");

            assertEquals("Readonly input", readonlyInput.getDomProperty("value"), "The value should not change as it's readonly.");
        }
    }

    @Test
    void testDropdownSelect() {
        driver.get(formURL);

        Select dropdown = new Select(driver.findElement(By.name("my-select")));
        dropdown.selectByVisibleText("Two");

        WebElement selectedOption = dropdown.getFirstSelectedOption();
        assertEquals("Two", selectedOption.getText(), "The selected option should be 'Option 2'.");
    }

   /* @Test
    void testDatalistSearch() {
        driver.get(formURL);

        WebElement datalistInput = driver.findElement(By.name("my-datalist"));
        datalistInput.sendKeys("New York");

        WebElement datalistOption = driver.findElement(By.cssSelector("datalist option[value='New York']"));
        assertTrue(datalistOption.isDisplayed(), "The selected city 'New York' should be available in the datalist.");
    }*/

    @Test
    void testCheckboxDefaultSelection() {
        driver.get(formURL);

        WebElement checkbox = driver.findElement(By.id("my-check-1"));
        assertTrue(checkbox.isSelected(), "The checkbox should be selected by default.");
    }

    @Test
    void testRadioButtonDefaultSelection() {
        driver.get(formURL);

        WebElement radioButton = driver.findElement(By.name("my-radio"));
        assertTrue(radioButton.isSelected(), "The radio button should be selected by default.");
    }

    @Test
    void testToggleCheckbox() {
        driver.get(formURL);

        WebElement checkbox = driver.findElement(By.name("my-check"));
        checkbox.click();

        assertTrue(!checkbox.isSelected(), "The checkbox should be toggled off.");


        WebElement checkradio = driver.findElement(By.id("my-radio-2"));
        checkradio.click();

        assertTrue(checkradio.isSelected(), "The checkbox should be toggled off.");
        WebElement checkradio2 = driver.findElement(By.id("my-radio-1"));
        assertTrue(!checkradio2.isSelected(), "The checkbox should be toggled off.");


    }

    @Test
    void testToggleRadioButton() {
        driver.get(formURL);

        WebElement radioButton = driver.findElement(By.name("my-radio"));
        radioButton.click();

        assertTrue(radioButton.isSelected(), "The radio button should be toggled on.");
    }

    @Test
    void testSubmitForm() {
        driver.get(formURL);

        WebElement submitButton = driver.findElement(By.cssSelector("body > main > div > form > div > div:nth-child(2) > button"));
        submitButton.click();

        WebElement confirmationMessage = driver.findElement(By.cssSelector("body > main > div > div:nth-child(1) > div > h1"));

        assertEquals("Form submitted", confirmationMessage.getText());
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
