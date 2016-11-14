package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.fail;

import java.util.List;

/**
 * Created by vstancu on 11/7/2016.
 */
public class ResultsPage {
    private WebDriver driver;

    public ResultsPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='nav-cnt cfix']/ul[1]//a[@href]")
    private List<WebElement> resultsMenu;

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfAllElements(resultsMenu));
    }

    public CategoryPage resultCategory(String categoryName){
        for(WebElement result : resultsMenu) {
            if (result.getText().contains(categoryName)) {
                result.click();
                CategoryPage categoryPage = PageFactory.initElements(driver, CategoryPage.class);
                return categoryPage;
            }
        }
        fail("No category found with the name " + categoryName);

        return null;
    }
    }