package test;

import base.Page;
import base.SetUpDriver;
import data.Customer;
import data.RegisteredCustomer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.MyAccountPage;

import java.net.MalformedURLException;

import static base.Page.isElementDisplayed;
import static base.Page.randomNumber;

public class UserRegistrationTest extends SetUpDriver {

    private HomePage homepage;
    private AuthenticationPage authpage;
    private CreateAccountPage createAccountPage;
    private Customer customer;
    private MyAccountPage myAccountPage;


    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(String browser) throws MalformedURLException {
        setUpDriver(browser);
//        initiateDriver(port);

        homepage = new HomePage(driver);
        authpage = new AuthenticationPage(driver);
        createAccountPage = new CreateAccountPage(driver);
        customer = new Customer();
        myAccountPage = new MyAccountPage(driver);

        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        customer.setFirstname("Leo");
        customer.setLastname("Messi");
        customer.setPassword("qwerty123");
        customer.setEmail(customer.getFirstname() + "." + customer.getLastname() + randomNumber() + "@gmail.com");
        customer.setAddress("Francuska 11");
        customer.setCity("Dallas");
        customer.setState("Texas");
        customer.setPostcode("40015");
        customer.setCountry("United States");
        customer.setMobilephone("123456789");

        homepage.clickOnSignInButton();
    }

    @AfterMethod
    public void endSession() {
        driver.quit();
    }

    @Test
    public void registerCustomerTest() throws InterruptedException {
        authpage.createAccount(customer.getEmail());
        createAccountPage.fillPersonalInformation(customer.getFirstname(), customer.getLastname(), customer.getEmail(), customer.getPassword())
                         .fillAddress(customer.getFirstname(), customer.getLastname(), customer.getAddress(), customer.getCity(), customer.getState(), customer.getPostcode(), customer.getCountry(), customer.getMobilephone())
                         .submitRegisterAccount();
        Assert.assertTrue(Page.isElementDisplayed(myAccountPage.myAccountHeader), "Page My Account doesn't displayed.");
        Assert.assertEquals(myAccountPage.getAccountName(), customer.getFirstname() + " " + customer.getLastname(), "Account name is not as expected.");
    }

    @Test
    public void tryCreateAccountWithNoAddressInformations() {
        authpage.createAccount(customer.getEmail());
        createAccountPage.fillPersonalInformation(customer.getFirstname(), customer.getLastname(), customer.getEmail(), customer.getPassword())
                         .submitRegisterAccount();
        Assert.assertTrue(isElementDisplayed(createAccountPage.dangerAlert), "Alert about missing required informations doesn't displayed.");
        Assert.assertFalse(Page.isElementDisplayed(myAccountPage.myAccountHeader), "Page My Account displayed. Account created.");
    }

    @Test
    public void tryCreateAccountWithPasswordLessThan5Signs() {
        authpage.createAccount(customer.getEmail());
        createAccountPage.fillPersonalInformation(customer.getFirstname(), customer.getLastname(), customer.getEmail(), "1234")
                         .fillAddress(customer.getFirstname(), customer.getLastname(), customer.getAddress(), customer.getCity(), customer.getState(), customer.getPostcode(), customer.getCountry(), customer.getMobilephone())
                         .submitRegisterAccount();
        Assert.assertTrue(isElementDisplayed(createAccountPage.dangerAlert), "Alert doesn't displayed.");
        Assert.assertFalse(Page.isElementDisplayed(myAccountPage.myAccountHeader), "Page My Account displayed. Account created.");
    }

    @Test
    public void tryCreateAccountWithExistEmail() throws InterruptedException {
        RegisteredCustomer customer = new RegisteredCustomer();
        authpage.createAccount(customer.getEmail());
        Assert.assertEquals(authpage.getDangerAlertText(), "An account using this email address has already been registered. Please enter a valid password or request a new one.");
    }

    @Test
    public void tryCreateAccountWithWhitespacesInPassword() {
        authpage.createAccount(customer.getEmail());
        createAccountPage.fillPersonalInformation(customer.getFirstname(), customer.getLastname(), customer.getEmail(), "     ")
                         .fillAddress(customer.getFirstname(), customer.getLastname(), customer.getAddress(), customer.getCity(), customer.getState(), customer.getPostcode(), customer.getCountry(), customer.getMobilephone())
                         .submitRegisterAccount();
        Assert.assertTrue(isElementDisplayed(createAccountPage.dangerAlert), "Alert doesn't displayed.");
        Assert.assertFalse(Page.isElementDisplayed(myAccountPage.myAccountHeader), "Page My Account displayed. Account created.");
    }
}
