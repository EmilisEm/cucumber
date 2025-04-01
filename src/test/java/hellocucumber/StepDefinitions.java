package hellocucumber;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class StepDefinitions {
    private final CommonBrowserActions commonBrowserActions = new CommonBrowserActions(Hooks.driver);
    private final DemoShopPage demoShopPage = new DemoShopPage(Hooks.driver);
    private final DemoqoPage demoqoPage = new DemoqoPage(Hooks.driver);

    @Given("I open the demo shop page")
    public void openDemoShop() {
        demoShopPage.openPage();
    }

    @And("I click the 'Add to wishlist' button")
    public void clickFavoriteButton() {
        demoShopPage.clickButtonInItemOverviewByText("Add to wishlist");
    }

    @And("I click the 'Add to cart' button and wait for loader to close")
    public void clickAddToCartButton() {
        demoShopPage.clickButtonInItemOverviewByText("Add to cart");
        commonBrowserActions.waitUntilElementDisplayed(By.cssSelector("[id='bar-notification']"));
    }

    @When("I open first gift card worth more than {string} with quantity {int}")
    public void addGiftCard(String price, int quantity) {
        commonBrowserActions.clickLinkInsideElementWithSelector(
                By.cssSelector("[class*=leftside]"), "Gift Cards");
        demoShopPage.openFirstProductWithPriceHigherThan(price);
        commonBrowserActions.fillInInputByClassWithValue("recipient-name", "recipient");
        commonBrowserActions.fillInInputByClassWithValue("sender-name", "sender");
        commonBrowserActions.fillInInputByClassWithValue("qty-input", Integer.valueOf(quantity).toString());
    }

    @And("I open custom jewellery with material {string} and length {int} and quantity {int}")
    public void selectMaterial(String material, int length, int quantity) {
        commonBrowserActions.clickLinkInsideElementWithSelector(
                By.cssSelector("[class*=side]"), "Jewelry");
        commonBrowserActions.clickLinkByText("Create Your Own Jewelry");

        var materialSelect = new Select(Hooks.driver.findElement(By.tagName("select")));
        materialSelect.selectByVisibleText(material);

        commonBrowserActions.fillInInputByClassWithValue("textbox", Integer.valueOf(length).toString());
        demoShopPage.clickRadioButtonWithTextInProductOverview("Star ");
        commonBrowserActions.fillInInputByClassWithValue("qty-input", Integer.valueOf(quantity).toString());
    }

    @Then("the subtotal in cart is {string}")
    public void validateSubTotal(String expectedTotal) {
        demoShopPage.clickWishlistLink();
        demoShopPage.clickAllAddToCartCheckboxes();
        commonBrowserActions.clickInputByValue("Add to cart");

        demoShopPage.validateSubTotalInCartOverview(expectedTotal);
    }

    @Given("I open the demoQA page")
    public void openDemoQA() {
        demoqoPage.openPage();
    }

    @Given("user account is newly created")
    public void smt(){};

    @When("I navigate to Widgets and open Progress Bar")
    public void navigateToProgressBar() {
        commonBrowserActions.clickElementContainingText(HtmlElement.H5, "Widgets");
        commonBrowserActions.clickElementContainingText(HtmlElement.SPAN, "Progress Bar");
    }

    @Then("I start and reset the progress bar")
    public void resetProgressBar() {
        commonBrowserActions.clickElementContainingText(HtmlElement.BUTTON, "Start");
        commonBrowserActions.waitUntilTextIsPresent("100%");
        commonBrowserActions.clickElementContainingText(HtmlElement.BUTTON, "Reset");
        commonBrowserActions.waitUntilTextIsPresent("0%");
    }

    @Given("I open the demoQA Elements page")
    public void openDemoQAElements() {
        demoqoPage.openPage();
        commonBrowserActions.clickElementContainingText(HtmlElement.H5, "Elements");
        commonBrowserActions.clickElementContainingText(HtmlElement.SPAN, "Web Tables");
    }

    @When("I create users until pagination loads second page")
    public void createAndDeleteUser() {
        commonBrowserActions.performActionUntilElementDisplayed(
                By.xpath("//span[@class='-totalPages' and text() = '2']"),
                demoqoPage.getCreateUserForDemoqoAction());

        var nextButton = commonBrowserActions.getElementsContainingText(HtmlElement.BUTTON, "Next").get(0);
        nextButton.click();

        demoqoPage.clickFirstDeleteButtonInUserList();
    }

    @Then("I validate the page count is {int}")
    public void validatePageCount(int expectedCount) {
        demoqoPage.validatePageCount(expectedCount);
    }

    @Given("I have a string value {string}")
    public void iHaveAStringValueValue(String value) {
        Assertions.assertNotNull(value);
    }

    @And("the string value \"{htmlElement}\" is converted to a custom type")
    public void theStringValueValueIsConvertedToACustomType(HtmlElement element) {
        Assertions.assertNotNull(element);
    }
}
