package pages;

import base.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddedItemPopupPage extends Page {
    public AddedItemPopupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "layer_cart")
    public WebElement addedItemPopup;

//    @FindBy(xpath = "//[@class='icon-ok' and contains(text(), 'Product successfully added to your shopping cart')]")
//    private WebElement okIcon;

    @FindBy(xpath = "//*[@id='layer_cart_product_title']")
    private WebElement productName;

    @FindBy(css = "#layer_cart_product_price")
    private WebElement productPrice;

    @FindBy(css = "#layer_cart_product_quantity")
    private WebElement productQuantity;

    @FindBy(css = ".continue[title='Continue shopping']")
    private WebElement continueShoppingButton;

    public String getAddedProductName() {
        return productName.getText();
    }

    public String getAddedProductPrice() {
        return productPrice.getText().substring(1);
    }

    public String getAddedProductQuantity() {
        return productQuantity.getText();
    }

    public HomePage continueShopping() {
        continueShoppingButton.click();
        return new HomePage(driver);
    }
}
