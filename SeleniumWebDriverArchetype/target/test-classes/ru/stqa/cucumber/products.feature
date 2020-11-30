Feature: Work with products in the bag

  Scenario: Add products in the bag
    Given open main page
    Then while size of bag is not 3 - add new product
    And open bag page
    And delete all products
