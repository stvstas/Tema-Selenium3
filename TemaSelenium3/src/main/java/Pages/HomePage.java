package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vstancu on 11/6/2016.
 */
public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.driver.get("http://www.shopmania.ro/");
        driver.manage().window().maximize();
    }

    @FindBy(xpath = "//input[@id='autocomplete_prod']")
    private WebElement searchField;

    @FindBy(xpath = "//form[@id='main_search']/div/span[2]/button")
    private WebElement searchButton;

    public ResultsPage searchItem(String itemName){

        searchField.sendKeys(itemName);
        searchButton.click();

        //go to the next page
        ResultsPage resultPage = PageFactory.initElements(driver, ResultsPage.class);
        resultPage.waitForPageToLoad();
        return resultPage;
    }

}
