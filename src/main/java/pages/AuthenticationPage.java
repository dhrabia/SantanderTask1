package pages;

import base.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AuthenticationPage extends Page {

    public AuthenticationPage (WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email_create")
    private WebElement createAccountEmailInput;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButton;

    @FindBy(id = "email")
    private WebElement signInEmailInput;

    @FindBy(id = "passwd")
    private WebElement signInPasswordInput;

    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    private WebElement dangerAlert;


    public CreateAccountPage createAccount(String email) {
        fillTextbox(createAccountEmailInput, email);
        createAccountButton.click();
        return new CreateAccountPage(driver);
    }

    public MyAccountPage signIn(String email, String password) {
        fillTextbox(signInEmailInput, email);
        fillTextbox(signInPasswordInput, password);
        signInButton.click();
        return new MyAccountPage(driver);
    }

    public String getDangerAlertText() {
        wait.until(ExpectedConditions.visibilityOf(dangerAlert));
        return dangerAlert.getText();
    }
}
