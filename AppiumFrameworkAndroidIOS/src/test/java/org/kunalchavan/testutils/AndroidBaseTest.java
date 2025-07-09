package org.kunalchavan.testutils;

import java.time.Duration;

import org.kunalchavan.pageobjectmodel.android.FormPage;
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
	
	@BeforeClass(alwaysRun = true)
	public void setup() {
		prop = properties("/src/main/resources/globalData.properties");
		String ipAddress = System.getProperty("ipAddress")!= null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		service = startAppiumServer(ipAddress,Integer.parseInt(prop.getProperty("port")));
		options = new UiAutomator2Options();
		options.setChromedriverExecutable(prop.getProperty("ChromedriverExecutable"));
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		options.setApp(System.getProperty("user.dir") + prop.getProperty("androidAPK"));
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitlyWait"))));
		formPage = new FormPage(driver);
	}

	public void activityStart(String activity) {
		// activityName = new Activity(packageName, activity);
		// driver.startActivity(activity); The method startActivity(Activity) from the type StartsActivity is deprecated
		((JavascriptExecutor) driver).executeScript("mobile: startActivity",
				ImmutableMap.of("intent",activity));

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}