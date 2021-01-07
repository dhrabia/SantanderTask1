package pages;

import base.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage extends Page {

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "customer_firstname")
    private WebElement firstnameField;
    @FindBy(id = "customer_lastname")
    private WebElement lastnameField;
    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "firstname")
    private WebElement firstnameAddressField;
    @FindBy(id = "lastname")
    private WebElement lastnameAddressField;
    @FindBy(id = "address1")
    private WebElement addressField;
    @FindBy(id = "city")
    private WebElement cityField;
    @FindBy(id = "id_state")
    private WebElement stateField;
    @FindBy(id = "postcode")
    private WebElement postcodeField;
    @FindBy(id = "id_country")
    private WebElement countryField;
    @FindBy(id = "phone_mobile")
    private WebElement phoneMobileField;

    @FindBy(id = "submitAccount")
    private WebElement registerButton;
    @FindBy(css = ".alert-danger")
    public WebElement dangerAlert;

    public CreateAccountPage fillPersonalInformation(String firstname, String lastname, String email, String password) {
        fillTextbox(firstnameField, firstname);
        fillTextbox(lastnameField, lastname);
        fillTextbox(emailField, email);
        fillTextbox(passwordField, password);
        return this;
    }

    public CreateAccountPage fillAddress(String firstname, String lastname, String address, String city, String state, String postcode, String country, String mobilephone) {
        fillTextbox(firstnameAddressField, firstname);
        fillTextbox(lastnameAddressField, lastname);
        fillTextbox(addressField, address);
        fillTextbox(cityField, city);
        selectFromComboboxByVisibleText(stateField, state);
        fillTextbox(postcodeField, postcode);
        selectFromComboboxByVisibleText(countryField, country);
        fillTextbox(phoneMobileField, mobilephone);
        return this;
    }

    public CreateAccountPage submitRegisterAccount() {
        registerButton.click();
        return this;
    }
}
