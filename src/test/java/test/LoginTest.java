package test;

import base.Page;
import base.SetUpDriver;
import data.RegisteredCustomer;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.HomePage;
import pages.MyAccountPage;

import java.net.MalformedURLException;

public class LoginTest extends SetUpDriver {

    private HomePage homepage;
    private AuthenticationPage authpage;
    private RegisteredCustomer customer;
    private MyAccountPage myAccountPage;

    @Parameters({"port"})
    @BeforeMethod
    public void setUp(String port) throws MalformedURLException {
        initiateDriver(port);
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        homepage = new HomePage(driver);
        authpage = new AuthenticationPage(driver);
        customer = new RegisteredCustomer();
        myAccountPage = new MyAccountPage(driver);
        homepage.clickOnSignInButton();
    }

    @AfterMethod
    public void endSession() {
        driver.quit();
    }

    @Test
    public void signIn() {
        authpage.signIn(customer.getEmail(), customer.getPassword());
        Assert.assertTrue(Page.isElementDisplayed(myAccountPage.myAccountHeader), "Page My Account doesn't displayed.");
        Assert.assertEquals(myAccountPage.getAccountName(), customer.getFirstname() + " " + customer.getLastname(), "Account name is not as expected.");
    }

    @Test
    public void signInWithWrongPassword() {
        authpage.signIn(customer.getEmail(), "randompassword");
        Assert.assertEquals(authpage.getDangerAlertText(), "Authentication failed.");
    }

    @Test
    public void signInWithNoPassword() {
        authpage.signIn(customer.getEmail(), "");
        Assert.assertEquals(authpage.getDangerAlertText(), "Password is required.");
    }

    @Test
    public void signInWithNoEmail() {
        authpage.signIn("", customer.getPassword());
        Assert.assertEquals(authpage.getDangerAlertText(), "An email address required.");
    }

    @Test
    public void signInWithUppercasePassword() {
        authpage.signIn(customer.getEmail(), customer.getPassword().toUpperCase());
        Assert.assertEquals(authpage.getDangerAlertText(), "Authentication failed.");
    }


}
