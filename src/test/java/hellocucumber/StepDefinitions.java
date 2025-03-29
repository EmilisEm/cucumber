package hellocucumber;

import io.cucumber.java.en.*;
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

    @When("I add first gift card worth more than {string} with quantity {int} to cart and wishlist")
    public void addGiftCard(String price, int quantity) {
        commonBrowserActions.clickLinkInsideElementWithSelector(
                By.cssSelector("[class*=leftside]"), "Gift Cards");
        demoShopPage.openFirstProductWithPriceHigherThan(price);
        commonBrowserActions.fillInInputByClassWithValue("recipient-name", "recipient");
        commonBrowserActions.fillInInputByClassWithValue("sender-name", "sender");
        commonBrowserActions.fillInInputByClassWithValue("qty-input", Integer.valueOf(quantity).toString());
        demoShopPage.clickButtonInItemOverviewByText("Add to cart");
        commonBrowserActions.waitUntilElementDisplayed(By.cssSelector("[id='bar-notification']"));
        demoShopPage.clickButtonInItemOverviewByText("Add to wishlist");
    }

    @And("Add custom jewellery with material {string} and length {int} and quantity {int} and add to cart and wishlist")
    public void selectMaterial(String material, int length, int quantity) {
        commonBrowserActions.clickLinkInsideElementWithSelector(
                By.cssSelector("[class*=side]"), "Jewelry");
        commonBrowserActions.clickLinkByText("Create Your Own Jewelry");

        var materialSelect = new Select(Hooks.driver.findElement(By.tagName("select")));
        materialSelect.selectByVisibleText(material);

        commonBrowserActions.fillInInputByClassWithValue("textbox", Integer.valueOf(length).toString());
        demoShopPage.clickRadioButtonWithTextInProductOverview("Star ");
        commonBrowserActions.fillInInputByClassWithValue("qty-input", Integer.valueOf(quantity).toString());
        demoShopPage.clickButtonInItemOverviewByText("Add to cart");
        commonBrowserActions.waitUntilElementDisplayed(By.cssSelector("[id='bar-notification']"));
        demoShopPage.clickButtonInItemOverviewByText("Add to wishlist");
    }

    @Then("I validate that the subtotal in cart is {string}")
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

    @When("I create a new user and delete a user")
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
}
