@regression @smoke @porsche
  Feature: Porsche pricing functionality
    Background:
      Given user navigates to Porsche app
      When user stores the price and selects the model "718 Cayman"

    Scenario: Validating Porsche base price matches listing price
      Then user validates Base price is matched with listed price

      @equipment
      Scenario: Validating Porsche Price For Equipment
        And user adds Power Sport Seats 14-way with Memory Package
        Then user validates that Price For Equipment is added and price matches

