package diane_hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class SelectionTest {
    public static void main(String[] args) {
    	System.setProperty("webdriver.edge.driver", "C:\\Program Files\\msedgedriver.exe");

        WebDriver driver = new EdgeDriver();

        try {
            // Navigate to the demo site
            driver.get("https://demoqa.com/select-menu");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            // Task 1: Single Selection by Visible Text
            WebElement singleSelectDropdown = driver.findElement(By.id("oldSelectMenu"));
            Select singleSelect = new Select(singleSelectDropdown);
            singleSelect.selectByVisibleText("Yellow");
            System.out.println("Selected single option by visible text: Yellow");

            // Task 2: Multiple Selection by Index and Value
            WebElement multiSelectDropdown = driver.findElement(By.id("cars"));
            Select multiSelect = new Select(multiSelectDropdown);

            // Select options by index and value
            multiSelect.selectByIndex(0); // Selects the first option
            multiSelect.selectByValue("audi"); // Selects "Audi"
            System.out.println("Selected multiple options by index and value.");

            // Task 3: Retrieve All Selected Options
            List<WebElement> selectedOptions = multiSelect.getAllSelectedOptions();
            System.out.println("Selected options:");
            for (WebElement option : selectedOptions) {
                System.out.println("- " + option.getText());
            }

            // Task 4: Deselect an Option
            multiSelect.deselectByIndex(0); // Deselects the first option
            System.out.println("Deselected option at index 0.");

            // Task 5: Validate Selections
            selectedOptions = multiSelect.getAllSelectedOptions();
            System.out.println("Validating final selections...");
            for (WebElement option : selectedOptions) {
                System.out.println("Still selected: " + option.getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

