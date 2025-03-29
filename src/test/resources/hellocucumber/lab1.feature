Feature: Lab1 Testing

  Scenario: Adding a gift card and custom jewelry to the cart
    Given I open the demo shop page
    When I add first gift card worth more than "99" with quantity 5000 to cart and wishlist
    And Add custom jewellery with material "Silver (1 mm)" and length 5000 and quantity 26 and add to cart and wishlist
    Then I validate that the subtotal in cart is "1002600.00"

  Scenario: Resetting progress bar
    Given I open the demoQA page
    When I navigate to Widgets and open Progress Bar
    Then I start and reset the progress bar

  Scenario: Creating and deleting a user
    Given I open the demoQA Elements page
    When I create a new user and delete a user
    Then I validate the page count is 1
