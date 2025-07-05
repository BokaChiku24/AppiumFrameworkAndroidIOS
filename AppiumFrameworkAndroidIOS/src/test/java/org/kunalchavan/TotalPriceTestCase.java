package org.kunalchavan;

import java.time.Duration;
import java.util.List;

import org.kunalchavan.pageObjects.android.FormPage;
import org.kunalchavan.pageObjects.android.ProductCatalogue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TotalPriceTestCase extends BaseTest {

	@Test
	public void totalPrice() {
		String country = "Argentina";
		FormPage formPage = new FormPage(driver);
		formPage.countryDropdown(country);
		formPage.setName("Kunal Chavan");
		formPage.setGender("Female");
		formPage.submitForm();
		
		ProductCatalogue productCatalouge = new ProductCatalogue(driver);
		productCatalouge.addItemToCartByIndex(0);
		productCatalouge.addItemToCartByIndex(0);
		productCatalouge.goToTheCartPage();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(
				driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));

		List<WebElement> productPrices = driver.findElements(
				By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']"));
		double priceTotal = 0;
		for (int i = 0; i < productPrices.size(); i++) {
			priceTotal = priceTotal + Double.parseDouble(productPrices.get(i).getText().replace("$", ""));
		}

		double productFinal = Double.parseDouble(driver
				.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText().replace("$", ""));
		Assert.assertEquals(productFinal, priceTotal);
	}

}