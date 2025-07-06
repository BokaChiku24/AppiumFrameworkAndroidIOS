package utils;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

// Common for Android & IOS
public class AppiumUtils {
	public AppiumDriver driver;
	public double priceTotal = 0;
	public double productFinal = 0;
	
	public AppiumUtils(AppiumDriver driver) {
		this.driver = driver;

	}
	
	public void addition(List<WebElement> list) {
		for (int i = 0; i < list.size(); i++) {
			priceTotal = priceTotal + Double.parseDouble(list.get(i).getText().replace("$", ""));
		}
	}
}
