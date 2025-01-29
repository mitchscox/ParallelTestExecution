package stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.Assertions;

public class SearchSteps {
    private WebDriver driver;

    @Given("I open Chrome")
    public void openChromeBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:/webdrivers/Chrome/chromedriver.exe");
        driver = new ChromeDriver();
        // TODO add assertion that chrome is open
    }
    @Given("I open Firefox")
    public void openFirefoxBrowser() {
        // TODO make this configurable
        System.setProperty("webdriver.gecko.driver", "C:/webdrivers/Firefox/geckodriver.exe");
        driver = new FirefoxDriver();
        // TODO Add assertion that firefox is open
    }
    @When("I navigate to {string}")
    public void navigateTo(String url) {
        driver.get(url);
    }

    @When("I search for {string}")
    public void searchFor(String query) {
        WebElement searchBox;
        //  Interestingly enough the element name is the same on all 3 search engines
        // TODO , check edge to see if same element name
        if (driver.getCurrentUrl().contains("google")) {
            searchBox = driver.findElement(By.name("q"));
        } else {
            searchBox = driver.findElement(By.name("q"));
        }
        searchBox.sendKeys(query);
        searchBox.submit();
        // TODO remove this after validation fixed
        driver.quit();
    }

    @Then("the top result should be {string}")
    public void validateTopResult(String expectedUrl) {
        WebElement topResult = driver.findElement(By.cssSelector("h3")); // Adjust selector as needed
        topResult.click();
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, actualUrl, "Top result URL does not match!");
        driver.quit();
    }
}