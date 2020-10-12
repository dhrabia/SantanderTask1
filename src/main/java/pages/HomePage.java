package pages;


import base.Page;
import data.Product;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends Page {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Sign in")
    private WebElement signInButton;

    @FindBy(css = ".product-container")
    private List<WebElement> listOfProducts;

    @FindBy(css = ".product-container .product-name")
    private List<WebElement> productNameList;

    @FindBy(css = ".product-container > .right-block .price")
    private List<WebElement> productPriceList;

    @FindBy(xpath = "//div[@class='product-container']//span[.='Quick view']")
    private List<WebElement> quickViewProductList;

    @FindBy(xpath = "//div[@class='product-container']//span[.='Add to cart']")
    private List<WebElement> addToCartButtonList;

    @FindBy(xpath = "//div[@class='product-container']//span[.='Add to cart']")
    private List<WebElement> productMoreButtonList;

    public AuthenticationPage clickOnSignInButton() {
        signInButton.click();
        return new AuthenticationPage(driver);
    }

    public QuickViewPage addItemToCart(int whichProduct) {
        Product product = new Product();
        product.setProductName(productNameList.get(whichProduct).getText());
        product.setProductPrice(Double.parseDouble(productPriceList.get(whichProduct).getText().substring(1)));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", addToCartButtonList.get(whichProduct));
        return new QuickViewPage(driver);
    }

    public ProductPage goToProduct(int whichProduct) {
        Product product = new Product();
        product.setProductName(productNameList.get(whichProduct).getText());
        product.setProductPrice(Double.parseDouble(productPriceList.get(whichProduct).getText().substring(1)));
        listOfProducts.get(whichProduct).click();
        return new ProductPage(driver);
    }

}
