package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserUtils;
import utilities.Driver;

public class PorscheConfiguratorPage {
    WebDriver driver;
    WebDriverWait wait;

    public PorscheConfiguratorPage() {
        driver = Driver.getDriver();
        wait = new WebDriverWait(driver, 10);
    }

    public String getBaseMSRP() {
        BrowserUtils.killUsercentrics();

        By baseMsrpValue = By.xpath("//p[normalize-space()='Base MSRP']/following-sibling::p[1]");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(baseMsrpValue));
        for (WebElement el : driver.findElements(baseMsrpValue)) {
            try {
                if (el.isDisplayed() && !el.getText().trim().isEmpty()) {
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center'});", el);
                    return el.getText().trim();
                }
            } catch (StaleElementReferenceException ignored) {}
        }
        throw new RuntimeException("Base MSRP not found");
    }

    public int getPriceForEquipment() {
        BrowserUtils.killUsercentrics();

        By priceForEquipment = By.xpath("//p[normalize-space()='Price for Equipment']/following-sibling::p");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(priceForEquipment));
        for (WebElement el : driver.findElements(priceForEquipment)) {
            if (el.isDisplayed() && !el.getText().isEmpty()) {
                return BrowserUtils.extractPriceAsInt(el.getText());
            }
        }
        throw new RuntimeException("Price For Equipment not found");
    }

    public int getPowerSportSeatsPrice() {
        WebElement priceEl = driver.findElement(
                By.xpath("//p[normalize-space()='Power Sport Seats (14-way) with Memory Package']/following-sibling::p/span")
        );
        return BrowserUtils.extractPriceAsInt(priceEl.getText());
    }


    public void addPowerSportSeats() {
        BrowserUtils.killUsercentrics();

        WebElement option = driver.findElement(
                By.xpath("//p[normalize-space()='Power Sport Seats (14-way) with Memory Package']"));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", option);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].closest('label').click();", option);
    }


}
