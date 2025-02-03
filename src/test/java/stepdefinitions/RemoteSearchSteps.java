package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.jupiter.api.Assertions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class RemoteSearchSteps {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(RemoteSearchSteps.class);

    @Given("I open {string}")
    public void openBrowser(String browser) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            switch (browser.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            // Connect to the Selenium Grid hub
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            logger.info("{} launched successfully.", browser);
            assertFalse(driver.getWindowHandles().isEmpty(), browser + " did not open");
            logger.info("Assertion passed: {} browser is open.", browser);

        } catch (Exception e) {
            logger.error("Error opening {} browser: {}", browser, e.getMessage(), e);
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
        } finally {
            logger.info("Closing browser after search.");
            driver.quit();
        }
    }

    @Then("the top result should be {string}")
    public void validateTopResult(String expectedUrl) {
        try {
            logger.info("Validating top result against expected URL: {}", expectedUrl);
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