package jpetstore.cucumber.steps;

import com.github.javafaker.Faker;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import jpetstore.cucumber.steps.serenity.PetStoreSteps;
import jpetstore.utilis.PetCategories;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserRegistrationsSteps {

    @Steps
    PetStoreSteps shopper;


    @Given("I navigate to the registration page")
    public void I_Navigate_To_Registration_page() {

        shopper.navigateToLoginPage();
        shopper.navigateToRegistrationPage();
    }

    // And I add new user information

    @And("I add new user information")
    public void I_add_new_user_info() {

        Faker faker = new Faker();
        String userName = "test" + faker.number().randomNumber(10, false);
        Serenity.setSessionVariable("userName").to(userName);

        String password = faker.internet().password();
        Serenity.setSessionVariable("password").to(password);

        String repeatPassword = password;

        shopper.addNewUserInformation(userName, password, repeatPassword);
    }

    @Given("^I add account information$")
    public void i_add_account_information() {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        Serenity.setSessionVariable("firstName").to(firstName);

        String lastName = faker.name().lastName();
        String emailId = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().cellPhone();
        String addr1 = faker.address().buildingNumber();
        String addr2 = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String zipCode = faker.address().zipCode();
        String country = faker.address().country();

        shopper.addAccountInformation(firstName, lastName, emailId, phoneNumber, addr1, addr2, city, state, zipCode,
                country);
    }

    @Given("^I add profile information$")
    public void i_add_profile_information() {

        shopper.addProfileInformation("english", "DOGS", true, true);
    }

    @Given("^I save my information$")
    public void i_save_my_information() {

        shopper.clickSaveAccountInformation();
    }

    @When("^I login with my credentials$")
    public void i_login_with_my_credentials() {

        String userName = Serenity.sessionVariableCalled("userName");
        String password = Serenity.sessionVariableCalled("password");

        shopper.doLogin(userName, password);
    }

    @Then("^I must be able to view the welcome greeting with my name$")
    public void i_must_be_able_to_view_the_welcome_greeting_with_my_name() {

        String result = shopper.getGreetingMessage();

        assertEquals("Welcome " + Serenity.sessionVariableCalled("firstName").toString() + "!", result);

    }


    /*
     * Login Step definations
     */
    @Given("I Login to the application with valid (.*) and (.*)")
    public void I_login_to_the_application(String userName, String password) throws InterruptedException {
        shopper.navigateToLoginPage();
        shopper.doLogin(userName, password);
    }

    @When("^i search for a (.*) it must show up in the search results$")
    public void i_search_for_a_Bulldog_it_must_show_up_in_the_search_results(String petType) {

        shopper.searchForProduct(petType);
        shopper.selectProductFromSearchTable(petType);
    }

    @When("^I view details about the pet (>*) and add it to cart$")
    public void i_view_details_about_the_pet_Male_Adult_Bulldog_and_add_it_to_cart(String pet) {

        shopper.addToCartSpecificProduct(pet);
    }

    @When("^I proceed to checkout$")
    public void i_proceed_to_checkout() {

        shopper.clickOnProceedToCheckout();
    }

    @When("^I click on Continue$")
    public void i_click_on_Continue() {

        shopper.clickOnContinueBtn();
    }

    @When("^I submit the order, the store must provide me a confirmation message on the placed order$")
    public void i_submit_the_order_the_store_must_provide_me_a_confirmation_message_on_the_placed_order() {

        shopper.clickOnConfirmBtn();
        shopper.verifyIfOrderSubmitted();
    }


    @And("^I enter my payment details (.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*),(.*)$")
    public void i_enter_my_payment_details_(String cardType, String cardNumber, String expiryDate, String firstname,
                                            String lastname, String addr1, String addr2, String city, String state, String zip, String country)
            throws Throwable {

        shopper.enterPaymentAndBillingDetails(cardType, cardNumber, expiryDate, firstname,
                lastname, addr1, addr2, city, state, zip, country);
    }

    @And("^I view details about the pet (.+) and add it to cart$")
    public void i_view_details_about_the_pet_and_add_it_to_cart(String pet) throws Throwable {

        shopper.addToCartSpecificProduct(pet);
    }


    @When("^I click on a category, then a category is displayed$")
    public void i_click_on_a_category_then_a_category_is_displayed(DataTable arg1) throws InterruptedException {

        List<String> searchVal =  arg1.asList(String.class);

        for( String s : searchVal ) {
            shopper.navigateToProductCategory(PetCategories.valueOf(s));
        }
    }
}
