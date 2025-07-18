package org.kunalchavan.pageobjectmodel.android;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

//GrandParent(AppiumUtils) -> AndroidActions -> FormPage
public class FormPage extends AndroidActions {
	
	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver; // Get Life To The Driver
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this); // To construct driver.findElement(By.id("android:id/text1"))
	}
	

	@AndroidFindBy(id="android:id/text1")
	private WebElement countrySelection;
	//driver.findElement(By.id("android:id/text1")).click();
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Kunal Chavan");

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
	private WebElement genderFemale;
	//driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
	private WebElement genderMale;
	//driver.findElement(By.xpath("//android.widget.RadioButton[@text='Male']")).click();
	
	@AndroidFindBy(className = "android.widget.Button")
	private WebElement shopButton;
	//driver.findElement(By.className("android.widget.Button")).click();
	
	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private WebElement toastMessage;
	
	//driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name")
	
	
	public void countryDropdown(String country) {
		countrySelection.click();
		scrollToText(country);
		// Dynamic xpath should not be use in page factory if there more dynamic xpath
		driver.findElement(By.xpath("//android.widget.TextView[@text=\"" + country + "\"]")).click();
		
	}

	public void setName(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();

	}
	
	public void setGender(String gender) {
		if(gender.contains("Female"))
			genderFemale.click();
		else
			genderMale.click();
	}
	
	public String getToastMessage() {
		shopButton.click();
		return toastMessage.getAttribute("name");
	}
	
	public ProductCatalogue submitForm() {
		shopButton.click();
		return new ProductCatalogue(driver);
	}
	
	public void setActivity() {
		((JavascriptExecutor) driver).executeScript("mobile: startActivity",
				ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
	}
}