package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.BrowserUtils;
import utilities.Driver;

public class PorscheModelStartPage {

    WebDriver driver;

    public PorscheModelStartPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div[data-testid='card-link-718']")
    public WebElement porsche718CardLink;

    public void click718CaymanCard() {
        BrowserUtils.killUsercentrics();

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", porsche718CardLink);

        BrowserUtils.killUsercentrics();

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", porsche718CardLink);
    }

}
