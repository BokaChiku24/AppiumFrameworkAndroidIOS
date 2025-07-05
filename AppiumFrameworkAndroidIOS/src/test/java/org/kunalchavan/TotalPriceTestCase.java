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
		cartPage.checkboxCheck();
		cartPage.termButton();
		String alertHeader = cartPage.alertTitle();
		String alertMessage = cartPage.alertMessage();
		Assert.assertEquals(alertHeader, "Terms Of Conditions");
		Assert.assertEquals(alertMessage,
				"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
		cartPage.cartSubmit();
	}

}