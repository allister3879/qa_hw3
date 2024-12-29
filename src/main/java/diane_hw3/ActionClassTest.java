package diane_hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        actions.moveToElement(hoverElement).perform();
        System.out.println("Tooltip displayed: " + hoverElement.getAttribute("title"));
    }
    
    public void testDragAndDrop(WebDriver driver) {
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        System.out.println("Drag-and-drop completed!");
    }
    
    public void testRightClick(WebDriver driver) {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        WebElement rightClickElement = driver.findElement(By.cssSelector(".context-menu-one"));
        Actions actions = new Actions(driver);
        actions.contextClick(rightClickElement).perform();
        WebElement contextMenuItem = driver.findElement(By.cssSelector(".context-menu-item span"));
        System.out.println("Context menu opened, option found: " + contextMenuItem.getText());
    }
    
    public void testDoubleClick(WebDriver driver) {
        driver.get("https://api.jquery.com/dblclick/");
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
        WebElement box = driver.findElement(By.cssSelector("div"));
        Actions actions = new Actions(driver);
        actions.doubleClick(box).perform();
        System.out.println("Double click performed on the box!");
    }

}
