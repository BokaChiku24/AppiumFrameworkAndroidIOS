package org.kunalchavan.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class ProductCatalogue extends AndroidActions {
	AndroidDriver driver;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> cartItem;
	//driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement addCartButton;
	//driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();


	public ProductCatalogue(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public void addItemToCartByIndex(int index) {
		cartItem.get(index).click();
	}
	
	public CartPage goToTheCartPage() {
		addCartButton.click();
		return new CartPage(driver);
	}
	
}
