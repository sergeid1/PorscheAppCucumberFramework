package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utilities.BrowserUtils;
import utilities.Driver;

public class Hooks {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        System.out.println("Before Scenario Method");
    }

    @After
    public void teardown() {
        driver.quit();
        System.out.println("After Scenario Method");
    }
}
