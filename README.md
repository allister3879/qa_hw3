Diana Narynbekova, SE-2217

# WaitTests.java
```java
package diane_hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class WaitTests {
    public static void main(String[] args) {
    	System.setProperty("webdriver.edge.driver", "C:\\Program Files\\msedgedriver.exe");

        WebDriver driver = new EdgeDriver();

        try {
            System.out.println("Applying implicit wait (10 seconds)...");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            System.out.println("Navigating to YouTube...");
            driver.get("https://www.youtube.com");

            System.out.println("Applying explicit wait to locate the search box...");
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchBox = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search_query")));
            
            System.out.println("Search box is visible. Entering 'ASMR' in the search box...");
            searchBox.sendKeys("ASMR");
            searchBox.submit();
            Thread.sleep(2000);
            
            System.out.println("Applying fluent wait (15 seconds, polling every 2 seconds) to locate the 3rd video result...");
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(15))  
                    .pollingEvery(Duration.ofSeconds(2)) 
                    .ignoring(NoSuchElementException.class);
            
            WebElement thirdVideo = fluentWait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver driver) {
                    String xpath = "(//a[@id='video-title'])[" + 3 + "]"; 
                    return driver.findElement(By.xpath(xpath));
                }
            });
            
            System.out.println("3rd video located. Clicking to open it...");
            thirdVideo.click();
            Thread.sleep(3000);

            System.out.println("3rd video opened successfully!");
            

        } catch (Exception e) {
            System.out.println("An error occurred during the test: " + e.getMessage());
        } finally {
            System.out.println("Closing the browser...");
            driver.quit();
        }
    }
}
```
The code demonstrates the use of different wait strategies in Selenium. It first applies an **implicit wait** of 10 seconds, then navigates to YouTube. Next, it applies an **explicit wait** to ensure the search box is visible, enters a search query, and submits it. Afterward, it uses a **fluent wait** with a 15-second timeout and 2-second polling intervals to locate the third video in the search results. Once the video is found, it clicks on it to open the video. The browser is closed after the actions are completed.

## Demo
https://github.com/user-attachments/assets/324ab58a-4b72-4382-a577-eaf03a6972c8

# ActionClassTest.java
```java
package diane_hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

public class ActionClassTest {

    public static void main(String[] args) {

        System.setProperty("webdriver.edge.driver", "C:\\Program Files\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        ActionClassTest tests = new ActionClassTest();
        tests.testMouseHovering(driver);
        tests.testDragAndDrop(driver);
        tests.testRightClick(driver);
        tests.testDoubleClick(driver);

        driver.quit();
    }

    public void testMouseHovering(WebDriver driver) {
        driver.get("https://jqueryui.com/tooltip/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement hoverElement = driver.findElement(By.id("age"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement)
               .pause(Duration.ofSeconds(2))  
               .perform();
        System.out.println("Tooltip displayed: " + hoverElement.getAttribute("title"));
    }

    public void testDragAndDrop(WebDriver driver) {
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(source)
               .pause(Duration.ofSeconds(1))  
               .moveToElement(target)
               .pause(Duration.ofSeconds(1))  
               .release()
               .perform();
        System.out.println("Drag-and-drop completed!");
    }

    public void testRightClick(WebDriver driver) {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement rightClickElement = driver.findElement(By.cssSelector(".context-menu-one"));
        Actions actions = new Actions(driver);
        actions.contextClick(rightClickElement)
               .pause(Duration.ofSeconds(2))  
               .perform();
        WebElement contextMenuItem = driver.findElement(By.cssSelector(".context-menu-item span"));
        System.out.println("Context menu opened, option found: " + contextMenuItem.getText());
    }

    public void testDoubleClick(WebDriver driver) {
        driver.get("https://api.jquery.com/dblclick/");
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        WebElement box = driver.findElement(By.cssSelector("div"));
        Actions actions = new Actions(driver);
        actions.doubleClick(box)
               .pause(Duration.ofSeconds(2))  
               .perform();
        System.out.println("Double click performed on the box!");
    }

}
```
This code demonstrates the use of the **Actions class** for various mouse interactions. It first applies an **implicit wait** of 10 seconds and then executes four tests. In the **mouse hovering test**, it hovers over an element and displays the tooltip text. The **drag-and-drop test** drags an item and drops it onto a target, with pauses between actions. The **right-click test** opens a context menu on a specific element and displays the selected option. Finally, the **double-click test** simulates a double-click on a box element.

## Demo
https://github.com/user-attachments/assets/0b770ae1-1196-4908-b9c5-ab5e2b68e5a3

# SelectionTest.java
```java
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
            driver.get("https://demoqa.com/select-menu");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

            Thread.sleep(2000);

            WebElement singleSelectDropdown = driver.findElement(By.id("oldSelectMenu"));
            Select singleSelect = new Select(singleSelectDropdown);
            singleSelect.selectByVisibleText("Yellow");
            System.out.println("Selected single option by visible text: Yellow");

            Thread.sleep(2000);

            WebElement multiSelectDropdown = driver.findElement(By.id("cars"));
            Select multiSelect = new Select(multiSelectDropdown);

            multiSelect.selectByIndex(0);
            Thread.sleep(1000);
            multiSelect.selectByValue("audi");
            System.out.println("Selected multiple options by index and value.");

            Thread.sleep(2000);

            List<WebElement> selectedOptions = multiSelect.getAllSelectedOptions();
            System.out.println("Selected options:");
            for (WebElement option : selectedOptions) {
                System.out.println("- " + option.getText());
            }

            Thread.sleep(2000);
            multiSelect.deselectByIndex(0);
            System.out.println("Deselected option at index 0.");

            Thread.sleep(2000);
            selectedOptions = multiSelect.getAllSelectedOptions();
            System.out.println("Validating final selections...");
            for (WebElement option : selectedOptions) {
                System.out.println("Still selected: " + option.getText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
```
This code automates the selection and deselection of options in dropdown menus. It opens a webpage with single and multiple select dropdowns, applying an **implicit wait** and maximizing the browser window. It selects an option from the single-select dropdown by visible text. For the multi-select dropdown, it selects options by index and value, then prints the selected options. Afterward, it deselects an option and validates the final selections by printing the remaining selected options.

## Demo
https://github.com/user-attachments/assets/e2b79003-a053-443d-b7a2-c1ee55bbcc3c
