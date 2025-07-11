package org.kunalchavan.androidtest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.kunalchavan.pageobjectmodel.android.CartPage;
import org.kunalchavan.pageobjectmodel.android.ProductCatalogue;
import org.kunalchavan.testutils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TotalPriceTestCase extends AndroidBaseTest {

	@BeforeMethod(alwaysRun = true)
	public void preSetup() {
		// Set the application screen to home page
		activityStart("com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity");
		// MainActivity, SplashActivity
	}

	@Test(dataProvider = "totalPrice", groups = { "Smoke" })
	public void totalPrice(HashMap<String, String> input) {
		String country = input.get("Country");
		formPage.countryDropdown(country);
		formPage.setName(input.get("Name"));
		formPage.setGender(input.get("Gender"));
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

	@Test(dataProvider = "validaton")
	public void toastMessage(String countryName, String gender) {
		String country = countryName;
		formPage.countryDropdown(country);
		formPage.setGender(gender);
		String message = formPage.getToastMessage();
		Assert.assertEquals(message, "Please enter your name");
	}

	@DataProvider(name = "totalPrice")
	public Object[][] getDataTotalPrice() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "/src/test/resources/testData/eCommerce.json");
		// return new Object[][] {{"Argentina","Kunal Chavan","Male"}};
		return new Object[][] { { data.get(0) } };
	}

	@DataProvider(name = "validaton")
	public Object[][] getDataValidation() {
		return new Object[][] { { "Argentina", "Male" }, { "Brazil", "Female" } };
	}

}