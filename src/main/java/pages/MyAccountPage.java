package pages;

import base.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends Page {

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@class='page-heading' and contains(text(), 'My account')]")
    public WebElement myAccountHeader;

    @FindBy(xpath = "//a[@title='View my customer account']/span")
    private WebElement accountName;

    @FindBy(css = "img.logo[alt='My Store']")
    private WebElement mainLogo;

    public String getAccountName() {
        return accountName.getText();
    }

    public HomePage goToHomePage() {
        mainLogo.click();
        return new HomePage(driver);
    }
}
