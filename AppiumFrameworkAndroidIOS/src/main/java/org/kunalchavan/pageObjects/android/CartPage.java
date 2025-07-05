package org.kunalchavan.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class CartPage extends AndroidActions {

	AndroidDriver driver;
	double priceTotal = 0;
	double productFinal = 0;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']")
	private List<WebElement> productPriceList;
	
	/*
    List<WebElement> productPrices = driver.findElements(
	By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']"));
	*/
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	//driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public double getPrice() {
		addition(productPriceList);
		return priceTotal;
		
	}
	
	public double getFinalPrice() {
		return productFinal = Double.parseDouble(totalAmount.getText().replace("$", ""));
	}
	
	public void addition(List<WebElement> list) {
		for (int i = 0; i < list.size(); i++) {
			priceTotal = priceTotal + Double.parseDouble(list.get(i).getText().replace("$", ""));
		}
	}

}
