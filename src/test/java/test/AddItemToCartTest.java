package test;

import base.SetUpDriver;
import data.Product;
import data.RegisteredCustomer;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddedItemPopupPage;
import pages.HomePage;

import java.net.MalformedURLException;

public class AddItemToCartTest extends SetUpDriver {
    private HomePage homepage;
    private AddedItemPopupPage addedItemPopupPage;

    @Parameters({"port"})
//    @Parameters(value = {"browser"})
    @BeforeMethod
    public void setUp(String port) throws MalformedURLException {
//        setUpDriver(browser);
        initiateDriver(port);

        homepage = new HomePage(driver);
        addedItemPopupPage = new AddedItemPopupPage(driver);

        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();

        RegisteredCustomer registeredCustomer = new RegisteredCustomer();
        homepage.clickOnSignInButton().signIn(registeredCustomer.getEmail(), registeredCustomer.getPassword()).goToHomePage();
    }

    @AfterMethod
    public void endSession() {
        driver.quit();
    }

    @Test
    public void addProductToCastFromHomePage() {
        AddedItemPopupPage addedItemPopupPage = new AddedItemPopupPage(driver);
        homepage.addItemToCart(0);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(addedItemPopupPage.addedItemPopup));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(addedItemPopupPage.getAddedProductName(), Product.productName, "Name of added product is not as selected.");
        softAssert.assertEquals(Double.parseDouble(addedItemPopupPage.getAddedProductPrice()), Product.productPrice, "Price of added product is not as selected.");
        softAssert.assertEquals(addedItemPopupPage.getAddedProductQuantity(), "1", "Quantity of added product is not equals 1.");
        softAssert.assertAll();
    }

    @Test
    public void addProductToCastFromProductPage() {
        AddedItemPopupPage addedItemPopupPage = new AddedItemPopupPage(driver);
        homepage.goToProduct(0).addToCart(4, "M");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(addedItemPopupPage.addedItemPopup));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(addedItemPopupPage.getAddedProductName(), Product.productName, "Name of added product is not as selected.");
        softAssert.assertEquals(Double.parseDouble(addedItemPopupPage.getAddedProductPrice()), Product.productPrice * 4, "Price of added product is not as selected.");
        softAssert.assertEquals(addedItemPopupPage.getAddedProductQuantity(), "4", "Quantity of added product is not equals 1.");
        softAssert.assertAll();
    }
}
