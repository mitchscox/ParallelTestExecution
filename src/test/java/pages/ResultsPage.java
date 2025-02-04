package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultsPage extends BasePage {

    @FindBy(css = "h3")
    private WebElement topResult;

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public void clickTopResult() {
        topResult.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}