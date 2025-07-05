package org.kunalchavan;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.kunalchavan.pageObjects.android.FormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	AppiumDriverLocalService service;
	UiAutomator2Options options;
	public AndroidDriver driver;
	public FormPage formPage;
	WebDriverWait wait;

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

	public void waitAttributeContainsById(String id, String attribute, String value) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id(id)), attribute, value));
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}