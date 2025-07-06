package org.kunalchavan.iosTest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.kunalchavan.pageObjects.ios.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class IOSBaseTest {

	AppiumDriverLocalService service;
	XCUITestOptions options;
	IOSDriver driver;
	HomePage page;
	WebDriverWait wait;

	@BeforeClass
	public void setup() {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();
		options = new XCUITestOptions();
		options.setDeviceName("iPhone 16 Pro Max");
		//options.setApp("/Users/kunalchavan/Library/Developer/Xcode/DerivedData/UIKitCatalog-gquhtmfnxjxpifgdmjdtvjcqmaxn/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
		options.setApp("/Users/kunalchavan/Desktop/UIKitCatalog.app");
		options.setPlatformName("iOS");
		options.setPlatformVersion("18.5");
		
		// Appium installs WebDriver Agent in the iOS Apps (Simulator)
		
		options.setWdaLaunchTimeout(Duration.ofSeconds(30));
		try {
			driver = new IOSDriver(new URI("http://127.0.0.1:4723").toURL(),options);
		} catch (MalformedURLException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		page = new HomePage(driver);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
