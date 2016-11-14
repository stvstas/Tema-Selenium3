package Tema;
        import Pages.CategoryPage;
        import Pages.HomePage;
        import Pages.ResultsPage;
        import Pages.ProductPage;
        import org.junit.After;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.support.PageFactory;

        import java.util.List;
        import java.util.concurrent.TimeUnit;

/**
 * Created by vstancu on 11/6/2016.
 */
public class TestClass {


    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void before(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @After
    public void after() throws InterruptedException{
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void shopTest(){
        ResultsPage resultPage = homePage.searchItem("Samsung S7");
        CategoryPage categoryPage = resultPage.resultCategory("Telefoane mobile");
        categoryPage.checkTotalItems(85);
        categoryPage.priceChecks("3.899,90 RON");
        Assert.assertTrue(categoryPage.getProduct("Galaxy S7 Edge Dual Sim 32GB LTE 4G Negru Olympic Version Samsung").equals("3.899,90 RON"));
    }


}
