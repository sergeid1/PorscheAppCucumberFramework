package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.PorscheConfiguratorPage;
import pages.PorscheModel718Page;
import pages.PorscheModelStartPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

public class PorscheSteps {

    WebDriver driver = Driver.getDriver();
    PorscheModelStartPage porscheModelStartPage = new PorscheModelStartPage();
    PorscheModel718Page porscheModel718Page = new PorscheModel718Page();
    PorscheConfiguratorPage porscheConfiguratorPage = new PorscheConfiguratorPage();
    String basePrice;
    String listedPrice;
    int equipmentBefore;
    int optionPrice;
    int equipmentAfter;

    @Given("user navigates to Porsche app")
    public void user_navigates_to_porsche_app() {
        driver.get(ConfigReader.getProperty("porscheURL"));
        BrowserUtils.killUsercentrics();

    }
    @When("user stores the price and selects the model {string}")
    public void user_stores_the_price_and_selects_the_model(String modelName) {
        porscheModelStartPage.click718CaymanCard();
        listedPrice = porscheModel718Page.getListedPrice();
        porscheModel718Page.click718CaymanModel();
        basePrice = porscheConfiguratorPage.getBaseMSRP();

    }
    @Then("user validates Base price is matched with listed price")
    public void user_validates_base_price_is_matched_with_listed_price() {
        int listedPriceInt = BrowserUtils.extractPriceAsInt(listedPrice);
        int basePriceInt = BrowserUtils.extractPriceAsInt(basePrice);
        Assert.assertEquals(basePriceInt, listedPriceInt);
    }

    @When("user adds Power Sport Seats 14-way with Memory Package")
    public void user_adds_power_sport_seats_with_memory_package() throws InterruptedException {
        equipmentBefore = porscheConfiguratorPage.getPriceForEquipment();
        optionPrice = porscheConfiguratorPage.getPowerSportSeatsPrice();
        porscheConfiguratorPage.addPowerSportSeats();
    }

    @Then("user validates that Price For Equipment is added and price matches")
    public void user_validates_that_price_for_equipment_is_added_and_price_matches() {
        equipmentAfter = porscheConfiguratorPage.getPriceForEquipment();
        Assert.assertTrue(equipmentAfter > equipmentBefore);
        Assert.assertEquals(equipmentAfter-equipmentBefore, optionPrice);


    }


}
