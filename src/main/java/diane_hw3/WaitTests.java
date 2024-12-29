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

