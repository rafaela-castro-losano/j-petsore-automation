package jpetstore.cucumber.steps.serenity;

import jpetstore.pages.*;
import jpetstore.utilis.PetCategories;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class PetStoreSteps extends ScenarioSteps {

    BasePage basePage;
    LoginPage loginPage;
    AccountsPage accountsPage;
    DashBoardPage dashBoardPage;
    ProductPage productPage;
    OrdersPage ordersPage;

    /******************************************************************************
     * ****************************************************************************
     * Base Page Steps
     * ****************************************************************************
     * copy methods from BasePage
     * add @Step with description
     ******************************************************************************/

    @Step("Signing out from the store")
    public DashBoardPage signOut() {
        return basePage.signOut();
    }

    @Step("Navigating to Login Page")
    public LoginPage navigateToLoginPage(){
        return basePage.navigateToSignOnPage();
    }
    @Step("Navigating to products page by cliking on : {0} link on header")
    public ProductPage navigateToProductCategory(PetCategories productCategory) {

        return basePage.navigateToProductCategory(productCategory);
    }

    @Step("Navigating to Products Page by clicking on shopping cart")
    public ProductPage navigateToShoppingCartPage() {

        return basePage.navigateToShoppingCart();
    }

    @Step("Navigating to Help Page")
    public HelpPage navigateToHelpPage() {

        return basePage.navigateToHelpPage();
    }

    @Step("Clicking on Company Logo & navigating to the DashBoard")
    public DashBoardPage navigateToDashBoard() {

        return basePage.navigateToDashBoard();
    }

    @Step("Navigating to Accounts Page")
    public AccountsPage navigateToMyAccountsPage() {

        return basePage.navigateToAccountPage();
    }

    @Step("Searching for product: {0}")
    public ProductPage searchForProduct(String productName) {

        return basePage.searchForProduct(productName);
    }

    /******************************************************************************
     * ****************************************************************************
     * Login Page Steps
     * ****************************************************************************
     ******************************************************************************/

    @Step("Getting Invalid Login Message")
    public String getMessageOnInvalidLogin() {
        return loginPage.getMessageOnInvalidLogin();
    }

    @Step("Navigating to user registration page")
    public AccountsPage navigateToRegistrationPage() {
        return loginPage.navigateToRegistrationPage();
    }

    @Step("Login into the application with userName: {0} & password: {1}")
    public DashBoardPage doLogin(String userName, String password) {
        basePage.clickSignOnLink();
        return loginPage.doLogin(userName,password);
    }

    /******************************************************************************
     * ****************************************************************************
     * Accounts Page Steps
     * ****************************************************************************
     ******************************************************************************/

    @Step("Adding new user information with username: {0}, password:{1}, repeatPassword:{2}")
    public AccountsPage addNewUserInformation(String userName, String password, String repeatPassword) {
        return accountsPage.addNewUserInformation(userName,password,repeatPassword);
    }

    @Step("Adding account information- firstname:{0} , lastName:{1}," +
            " email:{2}, phone:{3}, addr1:{4}, addr2: {5}, city:{6}, "  +
            "state:{7}, zip:{8}, country:{9} ")
    public AccountsPage addAccountInformation(String firstName, String lastName, String email,
                                              String phone, String address1, String address2,
                                              String city, String state, String zip, String country) {

        return accountsPage.addAccountInformation(firstName,lastName,email,phone,address1,address2,
                city,state,zip,country);
    }

    public AccountsPage addProfileInformation(String language, String favouriteCat, boolean myList, boolean myBanner) {

        return  accountsPage.addProfileInformation(language, favouriteCat, myList,
                myBanner);
    }

    @Step("Saving account information")
    public DashBoardPage clickSaveAccountInformation() {

        return accountsPage.clickSaveAccountInformation();

    }

    /******************************************************************************
     * ****************************************************************************
     * DashBoard Page Steps
     * ****************************************************************************
     ******************************************************************************/

    @Step("Getting the greeting message")
    public String getGreetingMessage() {
        return dashBoardPage.getGreetingMessage();
    }

    @Step("Selecting {0} petcategory from center display")
    public ProductPage selectProductFromCenterDisplay(PetCategories petCategories) {
        return dashBoardPage.selectProductFromCenterDisplay(petCategories);
    }

    @Step("Selecting {0} petcategory from sidebar")
    public ProductPage selectProductFromSideBarDisplay(PetCategories petCategories) {
        return dashBoardPage.selectProductFromSideBarDisplay(petCategories);
    }

    /************************************************************************************************
     * **********************************************************************************************
     *
     * PRODUCTS PAGE STEPS
     *
     *************************************************************************************************
     *************************************************************************************************/

    @Step("Selecting a pet with petcategory:{0} , and petName:{1}")
    public ProductPage selectPetByName( PetCategories petcategory , String petName) {

        return productPage.selectPetByName(petcategory, petName);
    }

    @Step("Adding {0} to the shopping cart")
    public ProductPage addToCartSpecificProduct(String specificProductName) {
        return productPage.addToCartSpecificProduct(specificProductName);
    }

    @Step("Selecting pet {0} by viewing details and adding it to cart")
    public ProductPage addToCardByViewingItemDetails( String specificProduct, String...details) {
        return productPage.addToCardByViewingItemDetails(specificProduct,details);
    }

    @Step("Selecting {0} from the search results")
    public ProductPage selectProductFromSearchTable( String productName ) {
        return productPage.selectProductFromSearchTable(productName);
    }

    @Step("Interacting with the shopping cart")
    public ProductPage shoppingCart( String productDescription, int quantity) {
        return productPage.shoppingCart(productDescription, quantity);
    }

    @Step("Remove item {0} from shopping cart")
    public boolean removeItemFromCart(String productName ) {

        return productPage.removeItemFromCart(productName);
    }

    @Step("Click on Proceed to checkout")
    public OrdersPage clickOnProceedToCheckout() {

        return productPage.clickOnProceedToCheckout();
    }

    /************************************************************************************************
     * **********************************************************************************************
     *
     * ORDERS PAGE STEPS
     *
     *************************************************************************************************
     *************************************************************************************************/
    @Step("Entering purchase information & placing order")
    public OrdersPage enterPaymentAndBillingDetails(String cardType,
                                                    String cardNumber,
                                                    String expiryDate,
                                                    String firstname,
                                                    String lastname,
                                                    String addr1,
                                                    String addr2,
                                                    String city,
                                                    String state,
                                                    String zip,
                                                    String country){


        return ordersPage.enterPaymentAndBillingDetails(cardType,
                cardNumber,
                expiryDate,
                firstname,
                lastname,
                addr1,
                addr2,
                city,
                state,
                zip,
                country);

    }



    @Step("Clicking on Ship to different checkbox" )
    public OrdersPage clickShipToDifferentAddress() {

        return ordersPage.clickShipToDifferentAddress();
    }

    @Step("Entering Shipping information")
    public OrdersPage enterShippingInfo(String firstName , String lastName , String addr1
            ,String addr2,String city ,String state , String zip, String country){

        return ordersPage.enterShippingInfo(firstName, lastName, addr1, addr2, city,
                state, zip, country);
    }

    @Step("Clicking on Continue Button")
    public OrdersPage clickOnContinueBtn(){

        return ordersPage.clickOnContinueBtn();
    }

    @Step("Clicking on Confirm button")
    public OrdersPage clickOnConfirmBtn(){

        return ordersPage.clickOnConfirmBtn();
    }

    @Step("Verify iof order has been placed")
    public void verifyIfOrderSubmitted(){

        ordersPage.verifyIfOrderSubmitted();
    }
}
