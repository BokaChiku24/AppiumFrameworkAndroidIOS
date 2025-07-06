package org.kunalchavan.androidTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
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
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest {

	AppiumDriverLocalService service;
	UiAutomator2Options options;
	public AndroidDriver driver;
	public FormPage formPage;
	Activity activityName;
	
	@BeforeClass
	public void setup() {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		options = new UiAutomator2Options();
		options.setChromedriverExecutable("/Users/kunalchavan/Documents/chromedriver");
		options.setDeviceName("Pixel 8");
		options.setApp(System.getProperty("user.dir") + "/src/main/resources/General-Store.apk");
		try {
			driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			formPage = new FormPage(driver);
		} catch (MalformedURLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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