package org.kunalchavan.androidTest;

import org.kunalchavan.pageObjects.android.CartPage;
import org.kunalchavan.pageObjects.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TotalPriceTestCase extends AndroidBaseTest {

	@BeforeMethod
	public void preSetup() {
		// Set the application screen to home page
		activityStart("com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity");
	}

	@Test()
	public void totalPrice() {
		String country = "Argentina";
		formPage.countryDropdown(country);
		formPage.setName("Kunal Chavan");
		formPage.setGender("Female");
		ProductCatalogue productCatalouge = formPage.submitForm();
		productCatalouge.addItemToCartByIndex(0);
		productCatalouge.addItemToCartByIndex(0);
		CartPage cartPage = productCatalouge.goToTheCartPage();
		Assert.assertEquals(cartPage.getFinalPrice(), cartPage.getPrice());
		cartPage.checkboxCheck();
		cartPage.termButton();
		String alertHeader = cartPage.alertTitle();
		String alertMessage = cartPage.alertMessage();
		Assert.assertEquals(alertHeader, "Terms Of Conditions");
		Assert.assertEquals(alertMessage,
				"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.");
		cartPage.submitOrder();
	}

	@Test
	public void toastMessage() {
		String country = "Argentina";
		formPage.countryDropdown(country);
		formPage.setGender("Female");
		String message = formPage.getToastMessage();
		Assert.assertEquals(message, "Please enter your name");
	}

}