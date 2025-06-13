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

    private InventoryPage inventoryPage;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void initializeInventoryTest() {
        inventoryPage = initializeToInventoryPage();
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
    public void testLoginPageHeaderElementsDisplayed() {
        softAssert.assertTrue(inventoryPage.getBurgerMenuBtn().isDisplayed(), "Menu button was not displayed");
        softAssert.assertTrue(inventoryPage.getAppLogo().isDisplayed(), "App logo was not displayed");
        softAssert.assertTrue(inventoryPage.getShoppingCartContainer().isDisplayed(), "Shopping cart button " +
                "was not displayed");
        softAssert.assertTrue(inventoryPage.getSecondaryHeaderTitle().isDisplayed(), "Secondary header was not" +
                "displayed");
        softAssert.assertTrue(inventoryPage.getProductSortMenu().isDisplayed(), "Sorting dropdown menu was not " +
                "displayed");

        softAssert.assertAll();
    }

    @Test
    public void testAppLogoText() {
        Assert.assertTrue(inventoryPage.getAppLogo().isDisplayed(), "App logo was not displayed");
        String appLogoText = inventoryPage.getAppLogo().getText();
        Assert.assertEquals(appLogoText, inventoryPage.getExpectedAppLogoText(), "App logo text was not correct");
    }

    @Test
    public void testProductsSubHeaderText() {
        Assert.assertTrue(inventoryPage.getSecondaryHeaderTitle().isDisplayed(), "Secondary header was not " +
                "displayed");
        String secondaryHeaderText = inventoryPage.getSecondaryHeaderTitle().getText();
        Assert.assertEquals(secondaryHeaderText, inventoryPage.getExpectedSecondaryHeaderTitleText(), "Secondary " +
                "header text was not correct");
    }

    @Test
    public void testPresenceOfInventoryContainer() {
        Assert.assertTrue(inventoryPage.getInventoryContainer().isDisplayed(), "Inventory container was not " +
                "displayed");
    }

    @Test
    public void testProductCardUiElements() {
        Assert.assertFalse(inventoryPage.getInventoryItems().isEmpty(), "No items were present in the " +
                "inventory list");
        WebElement productCard = inventoryPage.getInventoryItems().get(0);

        softAssert.assertTrue(inventoryPage.getInventoryItemNameElement(productCard).isDisplayed(), "Product name " +
                "was not displayed for the first item in the inventory container");
        softAssert.assertTrue(inventoryPage.getInventoryItemImageElement(productCard).isDisplayed(), "Product image " +
                "was not displayed for the first item in the inventory container");
        softAssert.assertTrue(inventoryPage.getInventoryItemDescriptionElement(productCard).isDisplayed(), "Product " +
                "description was not displayed for the first item in the inventory container");
        softAssert.assertTrue(inventoryPage.getInventoryItemPriceElement(productCard).isDisplayed(), "Product price " +
                "was not displayed for the first item in the inventory container");
        softAssert.assertTrue(inventoryPage.getInventoryItemAddToCartButtonElement(productCard).isDisplayed(), "Product " +
                "add to cart button was not displayed for the first item in the inventory container");

        softAssert.assertAll();
    }

    @Test
    public void testSortingMenuOptionsPresence() {
        List<String> expectedSortingOptions = Arrays.stream(SortingOption.values())
                .map(SortingOption::getValue)
                .collect(Collectors.toList());
        List<String> actualSortingOptions = inventoryPage.getSortingDropdownOptions();
        Assert.assertEquals(actualSortingOptions, expectedSortingOptions, "Sorting dropdown menu options did not " +
                "match expected values");
    }

    @Test
    public void testSortingAlphabetically() {
        inventoryPage.selectSortingOption(SortingOption.NAME_A_TO_Z);
        List<String> inventoryItemNames = inventoryPage.getInventoryItemNames().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> expectedItemNamesSorted = new ArrayList<>(inventoryItemNames);
        Collections.sort(expectedItemNamesSorted);

        Assert.assertEquals(inventoryItemNames, expectedItemNamesSorted, "Inventory items are not sorted " +
                "alphabetically");
    }

    @Test
    public void testSortingReverseAlphabetically() {
        inventoryPage.selectSortingOption(SortingOption.NAME_Z_TO_A);
        List<String> inventoryItemNames = inventoryPage.getInventoryItemNames().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> expectedItemNamesSorted = new ArrayList<>(inventoryItemNames);
        expectedItemNamesSorted.sort(Collections.reverseOrder());

        Assert.assertEquals(inventoryItemNames, expectedItemNamesSorted, "Inventory items are not sorted " +
                "reverse alphabetically");
    }

    @Test
    public void testSortingPriceLowToHigh() {
        inventoryPage.selectSortingOption(SortingOption.PRICE_LOW_TO_HIGH);

        List<Double> inventoryItemPrices = inventoryPage.getInventoryItems().stream()
                .map(inventoryPage::getInventoryItemPriceAsDouble)
                .collect(Collectors.toList());

        List<Double> expectedInventoryItemPricesSorted = new ArrayList<>(inventoryItemPrices);
        Collections.sort(expectedInventoryItemPricesSorted);

        Assert.assertEquals(inventoryItemPrices, expectedInventoryItemPricesSorted, "Inventory items are not " +
                "sorted by price low to high");
    }

    @Test
    public void testSortingPriceHighToLow() {
        inventoryPage.selectSortingOption(SortingOption.PRICE_HIGH_TO_LOW);

        List<Double> inventoryItemPrices = inventoryPage.getInventoryItems().stream()
                .map(inventoryPage::getInventoryItemPriceAsDouble)
                .collect(Collectors.toList());

        List<Double> expectedInventoryItemPricesSorted = new ArrayList<>(inventoryItemPrices);
        expectedInventoryItemPricesSorted.sort(Collections.reverseOrder());

        Assert.assertEquals(inventoryItemPrices, expectedInventoryItemPricesSorted, "Inventory items are not " +
                "sorted by price high to low");
    }

    @Test
    public void testAddToCartButtonReplacedWithRemoveButton() {
        WebElement inventoryItem = inventoryPage.getInventoryItems().get(0);
        inventoryPage.clickAddToCartButton(inventoryItem);
        Assert.assertTrue(inventoryPage.getInventoryItemRemoveButtonElement(inventoryItem).isDisplayed(), "Add to " +
                "cart button was not replaced by the Remove button after click");
    }

    @Test
    public void testShoppingCartBadgeCountIncrements() {
        WebElement inventoryItem = inventoryPage.getInventoryItems().get(0);
        int initialBadgeCount = inventoryPage.getShoppingCartBadgeValue();

        inventoryPage.clickAddToCartButton(inventoryItem);
        int updatedBadgeCount = inventoryPage.getShoppingCartBadgeValue();

        Assert.assertEquals(updatedBadgeCount, initialBadgeCount + 1, "Shopping cart badge count did not increment " +
                "after adding an item to the cart");
    }

    @Test
    public void testRemoveButtonReplacedWithAddToCartButton() {
        WebElement inventoryItem = inventoryPage.getInventoryItems().get(0);
        inventoryPage.clickAddToCartButton(inventoryItem);
        inventoryPage.clickRemoveButton(inventoryItem);
        Assert.assertTrue(inventoryPage.getInventoryItemAddToCartButtonElement(inventoryItem).isDisplayed(), "Remove " +
                "button was not replaced by the Add to Cart button after click");
    }

    @Test
    public void testShoppingCartBadgeCountDecrements() {
        WebElement inventoryItem = inventoryPage.getInventoryItems().get(0);
        inventoryPage.clickAddToCartButton(inventoryItem);
        int badgeCountAfterAdd = inventoryPage.getShoppingCartBadgeValue();

        inventoryPage.clickRemoveButton(inventoryItem);
        int badgeCountAfterRemove = inventoryPage.getShoppingCartBadgeValue();

        Assert.assertEquals(badgeCountAfterRemove, badgeCountAfterAdd - 1, "Shopping cart badge count did not " +
                "decrement after removing an item from the cart");
    }

    @Test
    public void testClickCartButton() {
        CartPage cartPage = inventoryPage.clickCartButton();
        Assert.assertTrue(cartPage.isPageLoaded(), "Clicking cart button on Inventory page failed to navigate to Cart page.");
    }

    @Test(dataProvider = "inventoryItems")
    public void testPresenceOfItemsByName(InventoryItem item) {
        Assert.assertTrue(inventoryPage.isItemPresent(item.getName()), "Item " + item.getName() + " was not found on the Inventory page.");
    }

    @Test(dataProvider = "inventoryItems")
    public void testInventoryItemInfo(InventoryItem itemTestData) {
        WebElement inventoryItem = inventoryPage.findInventoryItemByName(itemTestData.getName());
        Assert.assertNotNull(inventoryItem, "Item " + itemTestData.getName() + " was not found on the Inventory page.");

        String actualDescription = inventoryPage.getInventoryItemDescriptionElement(inventoryItem).getText();
        String actualPrice = inventoryPage.getInventoryItemPriceElement(inventoryItem).getText();
        String actualImageUrl = inventoryPage.getInventoryItemImageSrcPath(inventoryItem);

        softAssert.assertEquals(actualDescription, itemTestData.getDescription(), "Description for item " + itemTestData.getName() +
                " did not match expected value.");
        softAssert.assertEquals(actualPrice, itemTestData.getPrice(), "Price for item " + itemTestData.getName() +
                " did not match expected value.");
        softAssert.assertEquals(actualImageUrl, itemTestData.getImageUrl(), "Image URL for item " + itemTestData.getName() +
                " did not match expected value.");
        softAssert.assertAll();
    }

    @Test
    public void testAbsenceOfNonExistentItem() {
        String itemName = "Invalid Item";
        Assert.assertFalse(inventoryPage.isItemPresent(itemName), "Item " + itemName + " was unexpectedly found on the Inventory page.");
    }

    @Test
    public void testClickingBurgerMenuButton() {
        inventoryPage.clickBurgerMenuButton();
        Assert.assertFalse(inventoryPage.getMenuComponent().isMenuHidden(), "Menu did not appear after clicking burger menu button on Inventory page");
    }

    @Test
    public void testAbsenceOfBurgerMenu() {
        Assert.assertTrue(inventoryPage.getMenuComponent().isMenuHidden(), "Burger menu unexpectedly displayed on Inventory page initialization");
    }

    @Test(dataProvider = "inventoryItems")
    public void testClickInventoryItemName(InventoryItem item) {
        InventoryItemPage itemPage = inventoryPage.clickInventoryItem(item.getName());
        Assert.assertTrue(itemPage.isPageLoaded(), "Inventory Item page for " + item.getName() + " did not correctly " +
                "load");
        Assert.assertEquals(itemPage.getInventoryDetailsName().getText(), item.getName(), "Clicking name for "+
                item.getName() + " did not correctly navigate to the Inventory Item page for that item. Instead, navigated to " +
                itemPage.getInventoryDetailsName().getText());
    }

    @Test(dataProvider = "inventoryItems")
    public void testClickInventoryItemImage(InventoryItem item) {
        InventoryItemPage itemPage = inventoryPage.clickInventoryItemImage(item.getName());
        Assert.assertTrue(itemPage.isPageLoaded(), "Inventory Item page for " + item.getName() + " did not correctly " +
                "load");
        Assert.assertEquals(itemPage.getInventoryDetailsName().getText(), item.getName(), "Clicking image for "+
                item.getName() + " did not correctly navigate to the Inventory Item page for that item. Instead, navigated to " +
                itemPage.getInventoryDetailsName().getText());
    }

}
