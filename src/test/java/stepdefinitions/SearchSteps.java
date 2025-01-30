package stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.Assertions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SearchSteps {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(SearchSteps.class);

    @Given("I open Chrome")
    public void openChromeBrowser() {
        try {
            // TODO: Make this configurable
            System.setProperty("webdriver.chrome.driver", "C:/webdrivers/Chrome/chromedriver.exe");
            driver = new ChromeDriver();
            logger.info("Chrome  launched successfully.");
            assertFalse(driver.getWindowHandles().isEmpty(), "Chrome  did not open");
            logger.info("Assertion passed: Chrome browser is open.");

        } catch (Exception e) {
            logger.error("Error opening Chrome browser: {}", e.getMessage(), e);
        }
    }
    @Given("I open Firefox")
    public void openFirefoxBrowser() {
        try {
            // TODO: Make this configurable
            System.setProperty("webdriver.firefox.driver", "C:/webdrivers/FireFox/geckodriver.exe");
            driver = new FirefoxDriver();
            logger.info("Firefox launched successfully.");
            assertFalse(driver.getWindowHandles().isEmpty(), "Firefox  did not open");
            logger.info("Assertion passed: Firefox browser is open.");

        } catch (Exception e) {
            logger.error("Error opening Firefox browser: {}", e.getMessage(), e);
        }
    }

    @Given("I open Edge")
    public void openEdgeBrowser() {
        try {
            // TODO: Make this configurable
            System.setProperty("webdriver.edge.driver", "C:/webdrivers/Edge/msedgedriver.exe");
            driver = new EdgeDriver();
            logger.info("Edge launched successfully.");
            assertFalse(driver.getWindowHandles().isEmpty(), "Edge did not open");
            logger.info("Assertion passed: Edge is open.");

        } catch (Exception e) {
            logger.error("Error opening Edge : {}", e.getMessage(), e);
        }
    }

    @When("I navigate to {string}")
    public void navigateTo(String url) {
        driver.get(url);
    }

    @When("I search for {string}")
    public void searchFor(String query) {
        try {
            logger.info("Starting search for query: {}", query);
            WebElement searchBox;
            if (driver.getCurrentUrl().contains("google")) {
                searchBox = driver.findElement(By.name("q"));
                logger.info("Google detected, using search box element with name 'q'.");
            } else {
                searchBox = driver.findElement(By.name("q"));
                logger.info("Non-Google search engine detected, using search box element with name 'q'.");
            }
            searchBox.sendKeys(query);
            logger.info("Entered query: {}", query);
            searchBox.submit();
            logger.info("Search submitted.");

        } catch (Exception e) {
            logger.error("Error occurred during search: {}", e.getMessage(), e);
            // TODO The following finally  statement needs to removed post
        } finally {

            logger.info("Closing browser after search.");
            driver.quit();
        }
    }

    @Then("the top result should be {string}")
    public void validateTopResult(String expectedUrl) {
        try {
            logger.info("Validating top result against expected URL: {}", expectedUrl);
            // TODO change this two a string compare instead of a read
            WebElement topResult = driver.findElement(By.cssSelector("h3"));
            topResult.click();
            logger.info("Clicked on the top result.");

            String actualUrl = driver.getCurrentUrl();
            logger.info("Navigated to: {}", actualUrl);

            Assertions.assertEquals(expectedUrl, actualUrl, "Top result URL does not match!");
            logger.info("Validation successful: Expected URL matches actual URL.");
        } catch (Exception e) {
            logger.error("Error occurred during top result validation: {}", e.getMessage(), e);
        } finally {
            logger.info("Closing browser after validation.");
            driver.quit();
        }
    }
}