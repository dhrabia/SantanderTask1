package pages;

import base.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@name='Submit']/span[.='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(id = "quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = ".icon-plus")
    private WebElement quantityPlusIcon;

    @FindBy(id = "group_1")
    private WebElement sizeSelect;

    @FindBy(id = "our_price_display")
    private WebElement price;

    public AddedItemPopupPage addToCart(int quantity, String size) {
        for (int i = 1; i < quantity; i++) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            quantityPlusIcon.click();
        }
        Select select = new Select(sizeSelect);
        select.selectByVisibleText(size);
        addToCartButton.click();
        return new AddedItemPopupPage(driver);
    }
}
