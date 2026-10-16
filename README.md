# camping-rental-cart

An online camping equipment rental shop needs a shopping cart that calculates the
total rental price and automatically applies a camping combo offer.

All equipment is rented for **one day**. Rental dates, availability, deposits,
delivery, and checkout are outside the scope of this exercise.

## TDD exercise

Implement `ShoppingCart` using test-driven development (TDD). The starter class is
intentionally unfinished: `add` returns the cart and `total` returns zero.

Tests are defined in `src/test/java/com/camping/ShoppingCartTest.java`. The first
four tests are enabled; initially, only the empty-cart test passes. Make the enabled
tests pass, then remove `@Disabled` from the remaining tests one at a time, working
through them in file order.

## Rental catalog

The shop offers ten products across five categories:

| Category | Product code | Product | Daily price | Included in combo |
|---|---|---|---|---|
| Tent | TENT_2P | Two-person tent | 350 | Yes |
| Tent | TENT_1P | One-person tent | 300 | Yes |
| Sleeping bag | BAG_WARM | Warm sleeping bag | 180 | Yes |
| Sleeping bag | BAG_LIGHT | Lightweight sleeping bag | 150 | Yes |
| Sleeping mat | MAT_INSULATED | Insulated sleeping mat | 120 | Yes |
| Sleeping mat | MAT_BASIC | Basic sleeping mat | 100 | Yes |
| Backpack | BACKPACK_LARGE | Large backpack | 250 | No |
| Backpack | BACKPACK_SMALL | Small backpack | 200 | No |
| Headlamp | HEADLAMP_PRO | Powerful headlamp | 80 | No |
| Headlamp | HEADLAMP_BASIC | Basic headlamp | 60 | No |

Prices are in whole monetary units; no decimal money is needed.

## Camping combo

Any **one tent + one sleeping bag + one sleeping mat costs 500**, instead of the
sum of their individual rental prices.

- The offer repeats: two tents, two sleeping bags, and two sleeping mats form two
  combos.
- Form as many complete combos as possible. Charge leftover equipment at its
  individual daily price.
- Include the **most expensive** eligible products from each category in the
  combos, so the customer gets the lowest total price.
- Backpacks and headlamps are always charged at their individual daily prices.
  They never replace a combo product or affect how many combos can be formed.
- The order in which products are added does not affect the total.

For example, `TENT_2P + BAG_WARM + MAT_INSULATED` costs 500. Adding
`BACKPACK_LARGE + HEADLAMP_PRO` brings the total to 830. Adding `TENT_1P` as well
brings it to 1130: the more expensive tent stays in the combo.

## Shopping cart API

- `ShoppingCart.add(String productCode)` adds one unit of a catalog product and
  returns the same cart, so calls can be chained. Adding the same code again
  increases its quantity by one.
- Adding an unknown product code throws `IllegalArgumentException`.
- `ShoppingCart.total()` returns the current rental price, including all applicable
  combos. An empty cart costs zero.
- Calculating the total does not change the cart. Products can still be added after
  the total has been calculated.

```java
ShoppingCart cart = new ShoppingCart()
    .add("TENT_2P")
    .add("BAG_WARM")
    .add("MAT_INSULATED")
    .add("BACKPACK_LARGE");

int total = cart.total();
```

Once implemented, this example returns 750.

## Running the tests

Use JDK 21 and the Gradle wrapper.

On macOS or Linux:

```sh
./gradlew test
```

On Windows PowerShell:

```powershell
.\gradlew.bat test
```
