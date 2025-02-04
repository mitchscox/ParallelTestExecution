package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BasePage {

    @FindBy(name = "q")
    private WebElement searchBox;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String query) {
        searchBox.sendKeys(query);
        searchBox.submit();
    }
}