package org.kunalchavan;

import org.kunalchavan.pageObjects.android.CartPage;
import org.kunalchavan.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TotalPriceTestCase extends BaseTest {

	@Test
	public void totalPrice() {
		String country = "Argentina";
		formPage.countryDropdown(country);
		formPage.setName("Kunal Chavan");
		formPage.setGender("Female");
		ProductCatalogue productCatalouge = formPage.submitForm();
		productCatalouge.addItemToCartByIndex(0);
		productCatalouge.addItemToCartByIndex(0);
		CartPage cartPage = productCatalouge.goToTheCartPage();
		waitAttributeContainsById("com.androidsample.generalstore:id/toolbar_title", "text", "Cart");
		Assert.assertEquals(cartPage.getFinalPrice(), cartPage.getPrice());
	}

}