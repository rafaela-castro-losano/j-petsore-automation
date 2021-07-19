package jpetstore.pages;

import net.thucydides.core.webelements.Checkbox;

public class AccountsPage extends BasePage{

    private static final String USER_NAME_INPUT = "//*[@id='Catalog']//input[@name='username']";

    private static final String PASSWORD_INPUT = "//*[@id='Catalog']//input[@name='password']";

    private static final String REAPEATED_PASSWORD_INPUT = "//*[@id='Catalog']//input[@name='repeatedPassword']";

    private static final String FIRST_NAME_INPUT = "//*[@id='Catalog']//input[@name='account.firstName']";

    private static final String LAST_NAME_INPUT = "//*[@id='Catalog']//input[@name='account.lastName']";

    private static final String EMAIL_INPUT = "//*[@id='Catalog']//input[@name='account.email']";

    private static final String PHONE_INPUT = "//*[@id='Catalog']//input[@name='account.phone']";

    private static final String ADDR1_INPUT = "//*[@id='Catalog']//input[@name='account.address1']";

    private static final String ADDR2_INPUT = "//*[@id='Catalog']//input[@name='account.address2']";

    private static final String CITY_INPUT = "//*[@id='Catalog']//input[@name='account.city']";

    private static final String STATE_INPUT = "//*[@id='Catalog']//input[@name='account.state']";

    private static final String ZIP_INPUT = "//*[@id='Catalog']//input[@name='account.zip']";

    private static final String COUNTRY_INPUT = "//*[@id='Catalog']//input[@name='account.country']";

    private static final String LANGUAGE_PREFERENCE_DROPDOWN = "//*[@id='Catalog']//select[@name='account.languagePreference']";

    private static final String FAVOURITE_CATEGORY_DROPDOWN = "//*[@id='Catalog']//select[@name='account.favouriteCategoryId']";

    private static final String ENABLE_MY_LIST_CHECKBOX = "//*[@id='Catalog']//input[@name='account.listOption']";

    private static final String ENABLE_BANNER_CHECKBOX = "//*[@id='Catalog']//input[@name='account.bannerOption']";

    private static final String SAVE_ACCT_INFO_BUTTON = "//*[@id='Catalog']//input[@value='Save Account Information']";

    /**
     * Add new User Information
     * @param userName
     * @param password
     * @param repeatPassword
     * @return
     */
    public AccountsPage addNewUserInformation(String userName, String password, String repeatPassword) {

        waitForTextToAppear("User Information");
        $(USER_NAME_INPUT).type(userName);
        $(PASSWORD_INPUT).type(password);
        $(REAPEATED_PASSWORD_INPUT).type(repeatPassword);
        return this;
    }

    public AccountsPage addAccountInformation(String firstName, String lastName, String email,
                                              String phone, String address1, String address2,
                                              String city, String state, String zip, String country) {

        waitForTextToAppear("Account Information");
        $(FIRST_NAME_INPUT).type(firstName);
        $(LAST_NAME_INPUT).type(lastName);
        $(EMAIL_INPUT).type(email);
        $(PHONE_INPUT).type(phone);
        $(ADDR1_INPUT).type(address1);
        $(ADDR2_INPUT).type(address2);
        $(CITY_INPUT).type(city);
        $(COUNTRY_INPUT).type(country);
        $(STATE_INPUT).type(state);
        $(ZIP_INPUT).type(zip);

        return this;
    }

    /**
     *
     * Method to add Profile Information
     *
     * @param language
     * @param favouriteCat
     * @param myList
     * @param myBanner
     * @return
     */
    public AccountsPage addProfileInformation(String language, String favouriteCat, boolean myList, boolean myBanner) {

        waitForTextToAppear("Profile Information");
        $(LANGUAGE_PREFERENCE_DROPDOWN).selectByVisibleText(language);
        $(FAVOURITE_CATEGORY_DROPDOWN).selectByVisibleText(favouriteCat);

        //checkbox imported from WebElements
        Checkbox myListCheckBox = new Checkbox($(ENABLE_MY_LIST_CHECKBOX));

        myListCheckBox.setChecked(myList);

        new Checkbox( $(ENABLE_BANNER_CHECKBOX)).setChecked(myBanner);

        return this;
    }

    /**
     * Saving account information
     * @return
     */
    public DashBoardPage clickSaveAccountInformation() {

        waitFor(SAVE_ACCT_INFO_BUTTON).$(SAVE_ACCT_INFO_BUTTON).click();
        return this.switchToPage(DashBoardPage.class);
    }

}
