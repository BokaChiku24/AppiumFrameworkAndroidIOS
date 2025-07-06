package org.kunalchavan.pageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.IOSActions;

//GrandParent(AppiumUtils) -> IOSActions -> HomePage
public class HomePage extends IOSActions {

	IOSDriver driver;

	public HomePage(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(accessibility = "Alert Views")
	private WebElement alertView;
	// driver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
	
	public AlertPage goToAlert() {
		alertView.click();
		return new AlertPage(driver);
	}

}
