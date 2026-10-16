package com.camping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {

    @Test
    public void testEmptyCartIsZero() {
        assertEquals(0, new ShoppingCart().total());
    }

    @Test
    public void testSingleTent() {
        assertEquals(350, new ShoppingCart().add("TENT_2P").total());
    }

    @Test
    public void testSingleSleepingBag() {
        assertEquals(180, new ShoppingCart().add("BAG_WARM").total());
    }

    @Test
    public void testRepeatedProductIncreasesQuantity() {
        ShoppingCart cart = new ShoppingCart();
        cart.add("BAG_WARM");
        cart.add("BAG_WARM");
        assertEquals(360, cart.total());
    }

    @Disabled
    @Test
    public void testSingleSleepingMat() {
        assertEquals(120, new ShoppingCart().add("MAT_INSULATED").total());
    }

    @Disabled
    @Test
    public void testAlternativeModelsHaveTheirOwnPrices() {
        assertEquals(300, new ShoppingCart().add("TENT_1P").total());
        assertEquals(150, new ShoppingCart().add("BAG_LIGHT").total());
        assertEquals(100, new ShoppingCart().add("MAT_BASIC").total());
    }

    @Disabled
    @Test
    public void testIncompleteComboChargesIndividualPrices() {
        assertEquals(530, new ShoppingCart().add("TENT_2P").add("BAG_WARM").total());
    }

    @Disabled
    @Test
    public void testAddingProductReturnsSameCart() {
        ShoppingCart cart = new ShoppingCart();
        assertSame(cart, cart.add("TENT_2P"));
        assertEquals(530, cart.add("BAG_WARM").total());
    }

    @Disabled
    @Test
    public void testFullComboCosts500() {
        assertEquals(500, new ShoppingCart()
            .add("TENT_2P").add("BAG_WARM").add("MAT_INSULATED").total());
    }

    @Disabled
    @Test
    public void testCheapestModelsAlsoFormCombo() {
        assertEquals(500, new ShoppingCart()
            .add("TENT_1P").add("BAG_LIGHT").add("MAT_BASIC").total());
    }

    @Disabled
    @Test
    public void testAddingProductCanLowerTotal() {
        ShoppingCart cart = new ShoppingCart().add("TENT_2P").add("BAG_WARM");
        assertEquals(530, cart.total());
        assertEquals(500, cart.add("MAT_BASIC").total());
    }

    @Disabled
    @Test
    public void testComboTakesMostExpensiveTent() {
        assertEquals(800, new ShoppingCart()
            .add("TENT_1P").add("TENT_2P").add("BAG_WARM").add("MAT_INSULATED").total());
    }

    @Disabled
    @Test
    public void testComboTakesMostExpensiveSleepingBag() {
        assertEquals(650, new ShoppingCart()
            .add("TENT_2P").add("BAG_LIGHT").add("BAG_WARM").add("MAT_INSULATED").total());
    }

    @Disabled
    @Test
    public void testComboTakesMostExpensiveSleepingMat() {
        assertEquals(600, new ShoppingCart()
            .add("TENT_2P").add("BAG_WARM").add("MAT_BASIC").add("MAT_INSULATED").total());
    }

    @Disabled
    @Test
    public void testComboTakesMostExpensiveProductsAcrossCategories() {
        assertEquals(950, new ShoppingCart()
            .add("TENT_1P").add("TENT_2P")
            .add("BAG_LIGHT").add("BAG_WARM").add("MAT_INSULATED").total());
    }

    @Disabled
    @Test
    public void testProductOrderDoesNotAffectTotal() {
        ShoppingCart firstCart = new ShoppingCart()
            .add("TENT_1P").add("TENT_2P")
            .add("BAG_LIGHT").add("BAG_WARM").add("MAT_INSULATED");
        ShoppingCart secondCart = new ShoppingCart()
            .add("MAT_INSULATED").add("BAG_WARM")
            .add("TENT_2P").add("BAG_LIGHT").add("TENT_1P");
        assertEquals(950, firstCart.total());
        assertEquals(firstCart.total(), secondCart.total());
    }

    @Disabled
    @Test
    public void testTwoFullCombos() {
        assertEquals(1000, new ShoppingCart()
            .add("TENT_2P").add("TENT_1P")
            .add("BAG_WARM").add("BAG_LIGHT")
            .add("MAT_INSULATED").add("MAT_BASIC").total());
    }

    @Disabled
    @Test
    public void testPartialSecondComboChargesIndividualPrice() {
        assertEquals(850, new ShoppingCart()
            .add("TENT_2P").add("TENT_2P").add("BAG_WARM").add("MAT_INSULATED").total());
    }

    @Disabled
    @Test
    public void testTwoCombosLeaveCheapestProductsAtIndividualPrices() {
        assertEquals(1450, new ShoppingCart()
            .add("TENT_1P").add("TENT_2P").add("TENT_2P")
            .add("BAG_LIGHT").add("BAG_WARM").add("BAG_WARM")
            .add("MAT_INSULATED").add("MAT_BASIC").total());
    }

    @Disabled
    @Test
    public void testSingleCategoryNeverFormsCombo() {
        assertEquals(540, new ShoppingCart()
            .add("BAG_WARM").add("BAG_WARM").add("BAG_WARM").total());
    }

    @Disabled
    @Test
    public void testBackpacksUseIndividualPrices() {
        assertEquals(450, new ShoppingCart().add("BACKPACK_LARGE").add("BACKPACK_SMALL").total());
    }

    @Disabled
    @Test
    public void testHeadlampsUseIndividualPrices() {
        assertEquals(140, new ShoppingCart().add("HEADLAMP_PRO").add("HEADLAMP_BASIC").total());
    }

    @Disabled
    @Test
    public void testRepeatedExtrasIncreaseQuantity() {
        assertEquals(660, new ShoppingCart()
            .add("BACKPACK_LARGE").add("BACKPACK_LARGE")
            .add("HEADLAMP_PRO").add("HEADLAMP_PRO").total());
    }

    @Disabled
    @Test
    public void testExtrasAloneNeverFormCombo() {
        assertEquals(590, new ShoppingCart()
            .add("BACKPACK_LARGE").add("BACKPACK_SMALL")
            .add("HEADLAMP_PRO").add("HEADLAMP_BASIC").total());
    }

    @Disabled
    @Test
    public void testComboWithExtras() {
        assertEquals(830, new ShoppingCart()
            .add("TENT_2P").add("BAG_WARM").add("MAT_INSULATED")
            .add("BACKPACK_LARGE").add("HEADLAMP_PRO").total());
    }

    @Disabled
    @Test
    public void testTwoCombosWithExtras() {
        assertEquals(1330, new ShoppingCart()
            .add("TENT_2P").add("TENT_1P")
            .add("BAG_WARM").add("BAG_LIGHT")
            .add("MAT_INSULATED").add("MAT_BASIC")
            .add("BACKPACK_LARGE").add("HEADLAMP_PRO").total());
    }

    @Disabled
    @Test
    public void testExtrasCannotReplaceTent() {
        assertEquals(630, new ShoppingCart()
            .add("BAG_WARM").add("MAT_INSULATED")
            .add("BACKPACK_LARGE").add("HEADLAMP_PRO").total());
    }

    @Disabled
    @Test
    public void testExtrasCannotReplaceSleepingBag() {
        assertEquals(800, new ShoppingCart()
            .add("TENT_2P").add("MAT_INSULATED")
            .add("BACKPACK_LARGE").add("HEADLAMP_PRO").total());
    }

    @Disabled
    @Test
    public void testExtrasCannotReplaceSleepingMat() {
        assertEquals(860, new ShoppingCart()
            .add("TENT_2P").add("BAG_WARM")
            .add("BACKPACK_LARGE").add("HEADLAMP_PRO").total());
    }

    @Disabled
    @Test
    public void testTotalDoesNotChangeCartAndAllowsFurtherAdditions() {
        ShoppingCart cart = new ShoppingCart()
            .add("TENT_2P").add("BAG_WARM").add("MAT_INSULATED")
            .add("BACKPACK_LARGE").add("HEADLAMP_PRO");
        assertEquals(830, cart.total());
        assertEquals(830, cart.total());
        assertEquals(1130, cart.add("TENT_1P").total());
    }

    @Disabled
    @Test
    public void testUnknownProductCodeIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new ShoppingCart().add("UNKNOWN"));
    }
}
