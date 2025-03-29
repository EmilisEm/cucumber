Feature: Lab1 Testing

  Scenario Outline: Adding a gift card and custom jewelry to the cart
    Given I open the demo shop page
    When I open first gift card worth more than <card_min_price> with quantity <card_quantity>
    And I click the 'Add to cart' button and wait for loader to close
    And I click the 'Add to wishlist` button
    And I open custom jewellery with material <item_material> and length <jewellery_length> and quantity <jewellery_quantity>
    And I click the 'Add to cart' button and wait for loader to close
    And I click the 'Add to wishlist` button
    Then I validate that the subtotal in cart is "<expected_price>"
    Examples:
      | card_min_price| card_quantity | item_material   | jewellery_length | jewellery_quantity | expected_price |
      | "99"          | 5000          | "Silver (1 mm)" | 5000             | 26                 | 1002600.00     |
      | "99"          | 5001          | "Silver (1 mm)" | 4900             | 23                 | 1002100.00     |

  Scenario: Resetting progress bar
    Given I open the demoQA page
    When I navigate to Widgets and open Progress Bar
    Then I start and reset the progress bar

  Scenario: Creating and deleting a user
    Given I open the demoQA Elements page
    When I create a new user and delete a user
    Then I validate the page count is 1
