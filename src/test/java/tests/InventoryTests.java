package tests;

import framework.base.BaseTest;
import framework.utils.InventoryDataLoader;
import framework.utils.InventoryItem;
import framework.utils.enums.SortingOption;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.InventoryItemPage;
import pages.InventoryPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryTests extends BaseTest {

    private static final ThreadLocal<InventoryPage> inventoryPage = new ThreadLocal<>();
    private static final ThreadLocal<SoftAssert> softAssert = new ThreadLocal<>();

    @BeforeMethod
    public void initializeInventoryTest() {
        inventoryPage.set(initializeToInventoryPage());
        softAssert.set(new SoftAssert());
    }

    @DataProvider(name = "inventoryItems")
    public static Object[][] provideInventoryItems() {
        try {
            List<InventoryItem> items = InventoryDataLoader.loadInventoryItems();
            Object[][] data = new Object[items.size()][1];
            for (int i = 0; i < items.size(); i++) {
                data[i][0] = items.get(i);
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInventoryPageHeaderElementsDisplayed() {
        softAssert.get().assertTrue(inventoryPage.get().getBurgerMenuBtn().isDisplayed(), "Menu button was not displayed");
        softAssert.get().assertTrue(inventoryPage.get().getAppLogo().isDisplayed(), "App logo was not displayed");
        softAssert.get().assertTrue(inventoryPage.get().getShoppingCartContainer().isDisplayed(), "Shopping cart button " +
                "was not displayed");
        softAssert.get().assertTrue(inventoryPage.get().getSecondaryHeaderTitle().isDisplayed(), "Secondary header was not" +
                "displayed");
        softAssert.get().assertTrue(inventoryPage.get().getProductSortMenu().isDisplayed(), "Sorting dropdown menu was not " +
                "displayed");

        softAssert.get().assertAll();
    }

    @Test
    public void testAppLogoText() {
        Assert.assertTrue(inventoryPage.get().getAppLogo().isDisplayed(), "App logo was not displayed");
        String appLogoText = inventoryPage.get().getAppLogo().getText();
        Assert.assertEquals(appLogoText, inventoryPage.get().getExpectedAppLogoText(), "App logo text was not correct");
    }

    @Test
    public void testProductsSubHeaderText() {
        Assert.assertTrue(inventoryPage.get().getSecondaryHeaderTitle().isDisplayed(), "Secondary header was not " +
                "displayed");
        String secondaryHeaderText = inventoryPage.get().getSecondaryHeaderTitle().getText();
        Assert.assertEquals(secondaryHeaderText, inventoryPage.get().getExpectedSecondaryHeaderTitleText(), "Secondary " +
                "header text was not correct");
    }

    @Test
    public void testPresenceOfInventoryContainer() {
        Assert.assertTrue(inventoryPage.get().getInventoryContainer().isDisplayed(), "Inventory container was not " +
                "displayed");
    }

    @Test
    public void testProductCardUiElements() {
        Assert.assertFalse(inventoryPage.get().getInventoryItems().isEmpty(), "No items were present in the " +
                "inventory list");
        WebElement productCard = inventoryPage.get().getInventoryItems().get(0);

        softAssert.get().assertTrue(inventoryPage.get().getInventoryItemNameElement(productCard).isDisplayed(), "Product name " +
                "was not displayed for the first item in the inventory container");
        softAssert.get().assertTrue(inventoryPage.get().getInventoryItemImageElement(productCard).isDisplayed(), "Product image " +
                "was not displayed for the first item in the inventory container");
        softAssert.get().assertTrue(inventoryPage.get().getInventoryItemDescriptionElement(productCard).isDisplayed(), "Product " +
                "description was not displayed for the first item in the inventory container");
        softAssert.get().assertTrue(inventoryPage.get().getInventoryItemPriceElement(productCard).isDisplayed(), "Product price " +
                "was not displayed for the first item in the inventory container");
        softAssert.get().assertTrue(inventoryPage.get().getInventoryItemAddToCartButtonElement(productCard).isDisplayed(), "Product " +
                "add to cart button was not displayed for the first item in the inventory container");

        softAssert.get().assertAll();
    }

    @Test
    public void testSortingMenuOptionsPresence() {
        List<String> expectedSortingOptions = Arrays.stream(SortingOption.values())
                .map(SortingOption::getValue)
                .collect(Collectors.toList());
        List<String> actualSortingOptions = inventoryPage.get().getSortingDropdownOptions();
        Assert.assertEquals(actualSortingOptions, expectedSortingOptions, "Sorting dropdown menu options did not " +
                "match expected values");
    }

    @Test
    public void testSortingAlphabetically() {
        inventoryPage.get().selectSortingOption(SortingOption.NAME_A_TO_Z);
        List<String> inventoryItemNames = inventoryPage.get().getInventoryItemNames().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> expectedItemNamesSorted = new ArrayList<>(inventoryItemNames);
        Collections.sort(expectedItemNamesSorted);

        Assert.assertEquals(inventoryItemNames, expectedItemNamesSorted, "Inventory items are not sorted " +
                "alphabetically");
    }

    @Test
    public void testSortingReverseAlphabetically() {
        inventoryPage.get().selectSortingOption(SortingOption.NAME_Z_TO_A);
        List<String> inventoryItemNames = inventoryPage.get().getInventoryItemNames().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> expectedItemNamesSorted = new ArrayList<>(inventoryItemNames);
        expectedItemNamesSorted.sort(Collections.reverseOrder());

        Assert.assertEquals(inventoryItemNames, expectedItemNamesSorted, "Inventory items are not sorted " +
                "reverse alphabetically");
    }

    @Test
    public void testSortingPriceLowToHigh() {
        inventoryPage.get().selectSortingOption(SortingOption.PRICE_LOW_TO_HIGH);

        List<Double> inventoryItemPrices = inventoryPage.get().getInventoryItems().stream()
                .map(inventoryPage.get()::getInventoryItemPriceAsDouble)
                .collect(Collectors.toList());

        List<Double> expectedInventoryItemPricesSorted = new ArrayList<>(inventoryItemPrices);
        Collections.sort(expectedInventoryItemPricesSorted);

        Assert.assertEquals(inventoryItemPrices, expectedInventoryItemPricesSorted, "Inventory items are not " +
                "sorted by price low to high");
    }

    @Test
    public void testSortingPriceHighToLow() {
        inventoryPage.get().selectSortingOption(SortingOption.PRICE_HIGH_TO_LOW);

        List<Double> inventoryItemPrices = inventoryPage.get().getInventoryItems().stream()
                .map(inventoryPage.get()::getInventoryItemPriceAsDouble)
                .collect(Collectors.toList());

        List<Double> expectedInventoryItemPricesSorted = new ArrayList<>(inventoryItemPrices);
        expectedInventoryItemPricesSorted.sort(Collections.reverseOrder());

        Assert.assertEquals(inventoryItemPrices, expectedInventoryItemPricesSorted, "Inventory items are not " +
                "sorted by price high to low");
    }

    @Test
    public void testAddToCartButtonReplacedWithRemoveButton() {
        WebElement inventoryItem = inventoryPage.get().getInventoryItems().get(0);
        inventoryPage.get().clickAddToCartButton(inventoryItem);
        Assert.assertTrue(inventoryPage.get().getInventoryItemRemoveButtonElement(inventoryItem).isDisplayed(), "Add to " +
                "cart button was not replaced by the Remove button after click");
    }

    @Test
    public void testShoppingCartBadgeCountIncrements() {
        WebElement inventoryItem = inventoryPage.get().getInventoryItems().get(0);
        int initialBadgeCount = inventoryPage.get().getShoppingCartBadgeValue();

        inventoryPage.get().clickAddToCartButton(inventoryItem);
        int updatedBadgeCount = inventoryPage.get().getShoppingCartBadgeValue();

        Assert.assertEquals(updatedBadgeCount, initialBadgeCount + 1, "Shopping cart badge count did not increment " +
                "after adding an item to the cart");
    }

    @Test
    public void testRemoveButtonReplacedWithAddToCartButton() {
        WebElement inventoryItem = inventoryPage.get().getInventoryItems().get(0);
        inventoryPage.get().clickAddToCartButton(inventoryItem);
        inventoryPage.get().clickRemoveButton(inventoryItem);
        Assert.assertTrue(inventoryPage.get().getInventoryItemAddToCartButtonElement(inventoryItem).isDisplayed(), "Remove " +
                "button was not replaced by the Add to Cart button after click");
    }

    @Test
    public void testShoppingCartBadgeCountDecrements() {
        WebElement inventoryItem = inventoryPage.get().getInventoryItems().get(0);
        inventoryPage.get().clickAddToCartButton(inventoryItem);
        int badgeCountAfterAdd = inventoryPage.get().getShoppingCartBadgeValue();

        inventoryPage.get().clickRemoveButton(inventoryItem);
        int badgeCountAfterRemove = inventoryPage.get().getShoppingCartBadgeValue();

        Assert.assertEquals(badgeCountAfterRemove, badgeCountAfterAdd - 1, "Shopping cart badge count did not " +
                "decrement after removing an item from the cart");
    }

    @Test
    public void testClickCartButton() {
        CartPage cartPage = inventoryPage.get().clickCartButton();
        Assert.assertTrue(cartPage.isPageLoaded(), "Clicking cart button on Inventory page failed to navigate to Cart page.");
    }

    @Test(dataProvider = "inventoryItems")
    public void testPresenceOfItemsByName(InventoryItem item) {
        Assert.assertTrue(inventoryPage.get().isItemPresent(item.getName()), "Item " + item.getName() + " was not found on the Inventory page.");
    }

    @Test(dataProvider = "inventoryItems")
    public void testInventoryItemInfo(InventoryItem itemTestData) {
        WebElement inventoryItem = inventoryPage.get().findInventoryItemByName(itemTestData.getName());
        Assert.assertNotNull(inventoryItem, "Item " + itemTestData.getName() + " was not found on the Inventory page.");

        String actualDescription = inventoryPage.get().getInventoryItemDescriptionElement(inventoryItem).getText();
        String actualPrice = inventoryPage.get().getInventoryItemPriceElement(inventoryItem).getText();
        String actualImageUrl = inventoryPage.get().getInventoryItemImageSrcPath(inventoryItem);

        softAssert.get().assertEquals(actualDescription, itemTestData.getDescription(), "Description for item " + itemTestData.getName() +
                " did not match expected value.");
        softAssert.get().assertEquals(actualPrice, itemTestData.getPrice(), "Price for item " + itemTestData.getName() +
                " did not match expected value.");
        softAssert.get().assertEquals(actualImageUrl, itemTestData.getImageUrl(), "Image URL for item " + itemTestData.getName() +
                " did not match expected value.");
        softAssert.get().assertAll();
    }

    @Test
    public void testAbsenceOfNonExistentItem() {
        String itemName = "Invalid Item";
        Assert.assertFalse(inventoryPage.get().isItemPresent(itemName), "Item " + itemName + " was unexpectedly found on the Inventory page.");
    }

    @Test
    public void testClickingBurgerMenuButton() {
        inventoryPage.get().clickBurgerMenuButton();
        Assert.assertFalse(inventoryPage.get().getMenuComponent().isMenuHidden(), "Menu did not appear after clicking burger menu button on Inventory page");
    }

    @Test
    public void testAbsenceOfBurgerMenu() {
        Assert.assertTrue(inventoryPage.get().getMenuComponent().isMenuHidden(), "Burger menu unexpectedly displayed on Inventory page initialization");
    }

    @Test(dataProvider = "inventoryItems")
    public void testClickInventoryItemName(InventoryItem item) {
        InventoryItemPage itemPage = inventoryPage.get().clickInventoryItem(item.getName());
        Assert.assertTrue(itemPage.isPageLoaded(), "Inventory Item page for " + item.getName() + " did not correctly " +
                "load");
        Assert.assertEquals(itemPage.getInventoryDetailsName().getText(), item.getName(), "Clicking name for "+
                item.getName() + " did not correctly navigate to the Inventory Item page for that item. Instead, navigated to " +
                itemPage.getInventoryDetailsName().getText());
    }

    @Test(dataProvider = "inventoryItems")
    public void testClickInventoryItemImage(InventoryItem item) {
        InventoryItemPage itemPage = inventoryPage.get().clickInventoryItemImage(item.getName());
        Assert.assertTrue(itemPage.isPageLoaded(), "Inventory Item page for " + item.getName() + " did not correctly " +
                "load");
        Assert.assertEquals(itemPage.getInventoryDetailsName().getText(), item.getName(), "Clicking image for "+
                item.getName() + " did not correctly navigate to the Inventory Item page for that item. Instead, navigated to " +
                itemPage.getInventoryDetailsName().getText());
    }

}
