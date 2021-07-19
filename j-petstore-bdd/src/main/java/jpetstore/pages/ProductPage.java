package jpetstore.pages;

import jpetstore.utilis.PetCategories;
import net.serenitybdd.core.annotations.findby.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.thucydides.core.matchers.BeanMatchers.the;
import static net.thucydides.core.pages.components.HtmlTable.inTable;
import static org.hamcrest.Matchers.equalTo;

//Static Imports for Table

public class ProductPage extends BasePage {

    private static final String PRODUCT_TABLES = "//*[@id='Catalog']/table";
    private static final String ANCHOR = "//*[@id='Catalog']//table//td[normalize-space() = '";
    private static final String PROCEED_TO_CHECKOUT = "//a[contains(text(),'Proceed to Checkout')]";

    /**
     * Select the pet by providing a name
     * @param petCategories
     * @param petName
     * @return
     */
    public ProductPage selectPetByName(PetCategories petCategories , String petName) {

        waitForTextToAppear( petCategories.getValue() );

        List<WebElement> rows = inTable( $(PRODUCT_TABLES) )
                .filterRows(the("Name" , equalTo(petName) ) );

        rows.get(0).findElement(By.tagName("a")).click();

        return this;
    }

    /**
     *
     * This method will add a pet to the shopping cart
     *
     * @param specificProductName
     * @return
     */
    public ProductPage addToCartSpecificProduct(String specificProductName) {

        $(PRODUCT_TABLES).shouldContainText(specificProductName);

        String addToCartP1 = "']//parent::tr//a[contains(@href,'addItemToCart')]";

        String addToCartButton =  ANCHOR + specificProductName + addToCartP1;

        waitFor(addToCartButton).$(addToCartButton).click();

        return this;
    }

    /**
     * Add an item to the card by viewing details of the pet
     * @param specificProduct
     * @param details
     * @return
     */
    public ProductPage addToCardByViewingItemDetails( String specificProduct, String...details) {

        $(PRODUCT_TABLES).shouldContainText(specificProduct);

        String viewItemLinkP1 = "']//parent::tr//a[contains(@href,'viewItem')]";

        String viewItemLink =  ANCHOR +  specificProduct + viewItemLinkP1;

        waitFor(viewItemLink).$(viewItemLink).click();

        waitForAnyTextToAppear(details[0] , details [1] , details [2] );

        $("//a[@class = 'Button']").click();

        return this;
    }

    /**
     * Selecting a product from the search results
     * @param productName
     * @return
     */
    public ProductPage selectProductFromSearchTable( String productName ) {

        String searchResultP1 = "']//parent::tr//img//parent::a";

        String searchResultLink = ANCHOR + productName + searchResultP1;

        waitFor(searchResultLink).$(searchResultLink).click();

        return this;
    }

    /**
     * Interact with the shopping cart
     * @param productDescription
     * @param quantity
     * @return
     */
    public ProductPage shoppingCart( String productDescription ,  int quantity) {

        String quantityP1 = "']//following-sibling::td/input";

        String totalCostP1 = "']//following-sibling::td[4]";

        String quantityInput =  ANCHOR + productDescription + quantityP1;

        String totalCostLabel =  ANCHOR + productDescription + totalCostP1;


        if(quantity > 0 ) {

            Float totalvalueBeforeUpdate = Float.valueOf( $(totalCostLabel).getText().substring(5));

            $(quantityInput).typeAndEnter(Integer.toString(quantity));

            Float totalvalueAfterUpdate = Float.valueOf( $(totalCostLabel).getText().substring(5));

            if (totalvalueAfterUpdate < totalvalueBeforeUpdate) {
                return null;
            }

        }

        if(quantity == 0 ) {

            $(quantityInput).typeAndEnter(Integer.toString(quantity));

            waitForAbsenceOf(quantityInput).shouldNotBeVisible(By.xpath(quantityInput) );
        }

        return this;
    }

    /**
     * Remove item from shopping cart
     * @param productName
     * @return
     */
    public boolean removeItemFromCart( String productName) {

        String removeBtnP1 = "']//following-sibling::td[5]/a";
        String removeBtn = ANCHOR + productName + removeBtnP1;
        waitFor(removeBtn).$(removeBtn).click();
        return (!waitForAbsenceOf(removeBtn).isElementVisible(By.xpath(removeBtn)));
    }

    /**
     * Click on proceed to checkout
     * @return
     */
    public OrdersPage clickOnProceedToCheckout() {

        waitFor(PROCEED_TO_CHECKOUT).$(PROCEED_TO_CHECKOUT).click();

        return this.switchToPage(OrdersPage.class);
    }

}
