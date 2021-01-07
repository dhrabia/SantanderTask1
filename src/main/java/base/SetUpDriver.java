package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SetUpDriver {

    public static WebDriver driver;

    public void setUpDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            if (System.getProperty("os.name").contains("Windows")) {
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            } else {
                System.setProperty("webdriver.chrome.driver", "chromedriver");
            }
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            if (System.getProperty("os.name").contains("Windows")) {
                System.setProperty("webdriver.gecko.driver", "chromedriver.exe");
            } else {
                System.setProperty("webdriver.gecko.driver", "chromedriver.exe");
            }
            driver = new FirefoxDriver();
        }
    }

    public void initiateDriver(String Port) throws MalformedURLException {
        if(Port.equalsIgnoreCase("9001"))
        {
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.chrome());
            driver.manage().window().maximize();
        }
        else if(Port.equalsIgnoreCase("9002")){
            driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), DesiredCapabilities.firefox());
            driver.manage().window().maximize();
        }
    }
}
