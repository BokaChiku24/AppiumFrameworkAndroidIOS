package org.kunalchavan.pageObjects.ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.IOSActions;

//GrandParent(AppiumUtils) -> IOSActions -> AlertPage
public class AlertPage extends IOSActions {
	IOSDriver driver;
	
	public AlertPage(IOSDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`label =='Text Entry'`]")
	private WebElement alertTextEntry;
	//driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label =='Text Entry'`]")).click(); // Faster
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeCell")
	private WebElement enterText;
	//driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell")).sendKeys("Kunal Chavan");
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == 'OK'`]")
	private WebElement textEntryOkButton;
	//driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == 'OK'`]")).click();
	
	@iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm' ")
	private WebElement confirmAlertPopup;
	//driver.findElement(AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'Confirm' ")).click();
	
	@iOSXCUITFindBy(accessibility = "A message should be a short, complete sentence.")
	private WebElement confirmAlertMessage;
	//driver.findElement(AppiumBy.accessibilityId("A message should be a short, complete sentence.")).getText();
	
	@iOSXCUITFindBy(iOSNsPredicate = "name == 'Confirm'")
	private WebElement confirmAlertButton;
	//driver.findElement(AppiumBy.iOSNsPredicateString("name == 'Confirm'")).click();

	public void textEntryAlert(String value) {
		alertTextEntry.click();
		enterText.sendKeys(value);
		textEntryOkButton.click();
	}
	
	public void confirmAlert() {
		confirmAlertPopup.click();
		waitAttributeContainsByAccessibilityId("A message should be a short, complete sentence.", "name",
				"A message should be a short, complete sentence.");
	}
	
	public String ConfirmAlertMessage() {
		return confirmAlertMessage.getText();
	}
	
	public void confirmAlertAccept() {
		confirmAlertButton.click();
	}
	
	/*
	 * iOSNsPredicateString: 
	 * 
	   driver.findElement(AppiumBy.iOSNsPredicateString("name == 'Confirm / Cancel'")).click();
	   
	   Regular Expression iOSNsPredicateString:
	
	driver.findElement(
			AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value ENDSWITH[c] 'Cancel' "))
			.click();
	*/
	

}
