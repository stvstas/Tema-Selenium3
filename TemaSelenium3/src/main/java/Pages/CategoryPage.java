package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * Created by vstancu on 11/14/2016.
 */
public class CategoryPage {
    private WebDriver driver;

    public CategoryPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@id='main_products_container']/ul/li//a[@class = 'price-offer']")
    private List<WebElement> itemsPrice;

    @FindBy(xpath = "//div[@id='main_products_container']//p[@class='text-xl']/a")
    private List<WebElement> itemsName;

    @FindBy(xpath = "//div[@id='main_products_container']/ul/li//div[@class ='div-offer']/div[1]/a")
    private List<WebElement> itemsShopName;

    @FindBy(xpath = "//ul[@class='breadcrumb']//li[@class='ns']/span")
    private WebElement totalItemResults;

    //order by price Ascending
    @FindBy(xpath = "//ul[@class = 'nav-tab']/li[2]")
    private WebElement priceButton;


    public List<ProductPage> getProducts(){
        List<ProductPage> products = new ArrayList<>();
        for(WebElement price : itemsPrice){
            ProductPage p = new ProductPage();
            p.setPrice(price.getText());
            products.add(p);
        }
        for(WebElement name: itemsName){
            products.get(itemsName.indexOf(name)).setName(name.getText());
        }
        return products;
    }

    public String getProduct(String name){
        List<ProductPage> products = getProducts();
        for(ProductPage myProduct : products){
            if(myProduct.getName().equals(name)){
                return myProduct.getPrice();
            }
        }
        return null;
    }

    public void checkTotalItems(Integer totalItems){
        String totalAvailableItems = totalItemResults.getText();
        System.out.println("There are available " + totalAvailableItems + " items.");
        assertTrue(Integer.parseInt(totalAvailableItems) == totalItems);
    }



    public void priceChecks(String biggestPriceItem){
        priceButton.click();
        System.out.println("The lowest price is " + itemsPrice.get(0).getText() + " and the item can be found in the shop " + itemsShopName.get(0).getAttribute("title") + ".");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        priceButton.click();
        assertTrue(itemsPrice.get(0).getText().equals(biggestPriceItem));
        System.out.println("The biggest price is " + itemsPrice.get(0).getText() + " and the item can be found in the shop " + itemsShopName.get(0).getAttribute("title") + ".");
    }
}
