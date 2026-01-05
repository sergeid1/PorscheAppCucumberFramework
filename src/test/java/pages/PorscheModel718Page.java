package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BrowserUtils;
import utilities.Driver;


public class PorscheModel718Page {
    WebDriver driver;
    WebDriverWait wait;

    public PorscheModel718Page() {
        driver = Driver.getDriver();
        wait = new WebDriverWait(driver, 5);
    }

    public String getListedPrice() {
        BrowserUtils.killUsercentrics();

        WebElement price = Driver.getDriver().findElement(
                By.xpath("//h3[normalize-space()='718 Cayman']/following::p[contains(text(),'$')][1]"));
        return price.getText().trim();
    }

    public void click718CaymanModel() {
        BrowserUtils.killUsercentrics();

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("""
                    const cards = document.querySelectorAll("div.cursor-pointer");
                    for (const card of cards) {
                        const title = card.querySelector("h3");
                        if (title && title.textContent.trim() === '718 Cayman') {
                            card.click();
                            return;
                        }
                    }
                """);
    }


}
