package org.kunalchavan.testUtils;

import java.time.Duration;

import org.kunalchavan.pageObjects.android.FormPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import utils.AppiumUtils;

public class AndroidBaseTest extends AppiumUtils{

	AppiumDriverLocalService service;
	UiAutomator2Options options;
	public AndroidDriver driver;
	public FormPage formPage;
	Activity activityName;
	
	@BeforeClass
	public void setup() {
		service = startAppiumServer();
		options = new UiAutomator2Options();
		options.setChromedriverExecutable("/Users/kunalchavan/Documents/chromedriver");
		options.setDeviceName("Pixel 8");
		options.setApp(System.getProperty("user.dir") + "/src/main/resources/General-Store.apk");
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}

	public void activityStart(String activity) {
		// activityName = new Activity(packageName, activity);
		// driver.startActivity(activity); The method startActivity(Activity) from the type StartsActivity is deprecated
		((JavascriptExecutor) driver).executeScript("mobile: startActivity",
				ImmutableMap.of("intent",activity));

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}